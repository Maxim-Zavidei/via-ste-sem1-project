package model;

import java.util.ArrayList;

/**
 * A class to store and process members.
 */
public class Member {

  private String firstName;
  private String lastName;
  private String email;
  private long telephoneNumber;
  private ArrayList<Task> assignedTasks;
  private MyDate birthday;

  // ------------------------------ Constructors ------------------------------

  /**
   * Constructor with extended number of defined values.
   * @param firstName Member's first name.
   * @param lastName Member's last name.
   * @param birthday Member's birthday.
   * @param email Member's email.
   * @param telephoneNumber Member's telephone number.
   */
  public Member(String firstName, String lastName, MyDate birthday, String email, long telephoneNumber) {
    setFirstName(firstName);
    setLastName(lastName);
    this.birthday = birthday.copy();
    setEmail(email);
    setTelephoneNumber(telephoneNumber);
    assignedTasks = new ArrayList<>();
  }

  /**
   * Constructor with minimal number of defined values.
   * @param firstName Member's first name.
   * @param lastName Member's last name.
   * @param birthday Member's birthday.
   * @param email Member's email.
   */
  public Member(String firstName, String lastName, MyDate birthday, String email) {
    this(firstName, lastName, birthday, email, 0);
  }

  // ------------------------------ Setters ------------------------------

  /**
   * Setter for firstName instance variable.
   * @param firstName The first name of the member.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Setter for lastName instance variable.
   * @param lastName The last name of the member.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Setter for email instance variable.
   * @param email The email of the member.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Setter for telephoneNumber instance variable.
   * @param telephoneNumber The telephone number of the member.
   */
  public void setTelephoneNumber(long telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  // ------------------------------ Getters for Instance Variables ------------------------------

  /**
   * Getter for firstName instance variable.
   * @return Member's first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Getter for lastName instance variable.
   * @return Member's last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Getting the full name of the member.
   * @return Member's full name.
   */
  public String getFullName() {
    return firstName + " " + lastName;
  }

  /**
   * Getter for email instance variable.
   * @return Member's email name.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Getter for telephoneNumber instance variable.
   * @return Member's telephone number.
   */
  public long getTelephoneNumber() {
    return telephoneNumber;
  }

  /**
   * Getter for birthday instance variable.
   * @return Member's birthday.
   */
  public MyDate getBirthday() {
    return birthday;
  }

  // ------------------------------ Getters for Assigned Tasks ------------------------------

  /**
   * Getter for all assigned tasks to this member.
   * @return All the assigned tasks to this member.
   */
  public ArrayList<Task> getAllAssignedTasks() {
    return assignedTasks;
  }

  // ------------------------------ Other Methods ------------------------------

  /**
   * Assigns task to this member.
   * @param taskToAssign The task object to be assigned.
   */
  public void assignTask(Task taskToAssign) {
    for (Task task : assignedTasks) if (task.getId().equals(taskToAssign.getId())) return;
    assignedTasks.add(taskToAssign);
    taskToAssign.assignMember(this);
  }

  /**
   * Unassigns member argument from this task.
   * @param taskToUnassign The task needed to be unassigned.
   */
  public void unassignFromTask(Task taskToUnassign) {
    for (int i = 0; i < assignedTasks.size(); i++) if (assignedTasks.get(i).getId().equals(taskToUnassign.getId())) {
      assignedTasks.remove(i);
      taskToUnassign.unassignMember(this);
      return;
    }
  }

  /**
   * Unassigns every task argument from this member.
   */
  public void unassignFromEveryTask() {
    for (Task task : assignedTasks) {
      unassignFromTask(task);
    }
  }

  /**
   * Method to compare 2 member objects.
   * @param obj Object to be compared,
   * @return True if are equal, false otherwise.
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof Member)) return false;
    Member other = (Member) obj;

    for(int i = 0; i < assignedTasks.size(); i++)
    {
      if (!assignedTasks.get(i).equals(other.assignedTasks.get(i)))
      {
        return false;
      }
    }
    return firstName.equals(other.firstName) && lastName.equals(other.lastName) && email.equals(other.email)
        && birthday.equals(other.birthday);
  }
}
