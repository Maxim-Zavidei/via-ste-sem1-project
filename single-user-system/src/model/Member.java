package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A class to store and process the members
 */
public class Member {

  private String firstName;
  private String lastName;
  private String email;
  private long telephoneNumber;
  private ArrayList<Task> assignedTasks;
  private MyDate birthday;

  /**
   * Constructor with member type values
   * @param firstName first name of the member
   * @param lastName last name of the member
   * @param birthday birthday of the member
   * @param email email of the member
   * @param telephoneNumber telephone number of the member
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
   * Constructor with member type values, telephone number is initialised as 0
   * @param firstName first name of the member
   * @param lastName last name of the member
   * @param birthday birthday of the member
   * @param email email of the member
   */
  public Member(String firstName, String lastName, MyDate birthday, String email) {
    setFirstName(firstName);
    setLastName(lastName);
    this.birthday = birthday.copy();
    setEmail(email);
    telephoneNumber = 0;
    assignedTasks = new ArrayList<>();
  }

  /**
   * Getter for firstName instance variable
   * @return firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Setter for firstName instance variable
   * @param firstName
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Getter for lastName instance variable
   * @return lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Getting the full name of the member
   * @return full name
   */
  public String getFullName() {
    return firstName + " " + lastName;
  }

  /**
   * Setter for lastName instance variable
   * @param lastName
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Getter for email instance variable
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Setter for email instance variable
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Getter for telephoneNumber instance variable
   * @return telephoneNumber
   */
  public long getTelephoneNumber() {
    return telephoneNumber;
  }

  /**
   * Setter for telephoneNumber instance variable
   * @param telephoneNumber
   */
  public void setTelephoneNumber(long telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  /**
   * Getter for birthday instance variable
   * @return birthday
   */
  public MyDate getBirthday() {
    return birthday;
  }

  /**
   * Getter for assignedTasks instance variable
   * @return assignedTasks
   */
  public ArrayList<Task> getAllAssignedTasks() {
    return assignedTasks;
  }

  /**
   * Method to check if a task is already assigned
   * @param task
   * @return true if assigned, false otherwise
   */
  public boolean isAssignedToTask(Task task) {

    for (int i = 0; i < assignedTasks.size(); i++)
    {
      if (assignedTasks.get(i).equals(task))
      {
        return true;
      }
    }

    return false;
  }

  /**
   * Assigns task to this member.
   * @param taskToAssign The task object to be assigned.
   * @throws UnsupportedOperationException if the task is already assigned to the member.
   */
  public void assignTask(Task taskToAssign) {
    for (Task task : assignedTasks) if (task.getId().equals(taskToAssign.getId())) throw new UnsupportedOperationException("This task is already assigned to the member.");
    taskToAssign.assignMember(this);
    assignedTasks.add(taskToAssign);
  }

  /**
   * Unassigns member argument from this task.
   * @param task The task needed to be unassigned.
   * @throws NoSuchElementException if the task is not linked to the member.
   */
  public void unassignFromTask(Task task) {
    for (int i = 0; i < assignedTasks.size(); i++) if (assignedTasks.get(i).equals(task.getId())) {
      assignedTasks.get(i).unassignMember(this);
      assignedTasks.remove(i);
      return;
    }
    throw new NoSuchElementException("This task is not a assigned to the member.");
  }

  /**
   * Unassigns every task argument from this member.
   */
  public void unassignFromEveryTask() {
    for (int i = 0; i < assignedTasks.size(); i++) assignedTasks.get(i).unassignMember(this);
    assignedTasks.clear();
  }

  /**
   * Method to compare 2 member objects
   * @param obj
   * @return true if are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Member))
    {
      return false;
    }
    Member other = (Member)obj;

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
