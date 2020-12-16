package model;

import java.util.ArrayList;

/**
 *  A class to create, store and process requirements.
 */
public class Requirement {

  private String id;
  private String title;
  private String description;
  private MyDate deadline;
  private String priorityGroup;
  private boolean isApproved;
  private ArrayList<Task> assignedTasks;

  // ------------------------------ Constructors ------------------------------

  /**
   * Constructor with extended number of defined values.
   * @param id Id value of the requirement.
   * @param title Title for the requirement.
   * @param description Description of the requirement.
   * @param deadline MyDate object representing the deadline.
   * @param priorityGroup A value of either ["Critical", "High", "Low"] representing the importance of the requirement.
   * @throws IllegalArgumentException if the requirement's title is longer then 14 chars.
   * @throws IllegalArgumentException if the priority group argument is invalid.
   */
  public Requirement(String id, String title, String description, MyDate deadline, String priorityGroup) {
    this.id = id;
    setTitle(title);
    setDescription(description);
    setDeadline(deadline);
    setPriorityGroup(priorityGroup);
    isApproved = false;
    assignedTasks = new ArrayList<>();
  }

  /**
   * Constructor with minimal number of defined values.
   * @param id Id value of the requirement.
   * @param title Title for the requirement.
   * @param deadline MyDate object representing the deadline.
   * @param priorityGroup A value of either ["Critical", "High", "Low"] representing the importance of the requirement.
   * @throws IllegalArgumentException if the requirement's title is longer then 14 chars.
   * @throws IllegalArgumentException if the priority group argument is invalid.
   */
  public Requirement(String id, String title, MyDate deadline, String priorityGroup) {
    this(id, title, "", deadline, priorityGroup);
  }

  // ------------------------------ Setters ------------------------------

  /**
   * Setter for the title instance variable.
   * @param title Title of the task.
   * @throws IllegalArgumentException if the requirement's title is longer then 14 chars.
   */
  public void setTitle(String title) {
    if (title.length() > 14) throw new IllegalArgumentException("The requirement title can not be longer then 14 characters.");
    this.title = title;
  }

  /**
   * Setter for the description instance variable.
   * @param description Description of the task.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Setter for the deadline instance variable.
   * @param deadline MyDate object representing the deadline.
   * @throws IllegalArgumentException if deadline is in the past.
   */
  public void setDeadline(MyDate deadline) {
    if (deadline.isBefore(new MyDate())) throw new IllegalArgumentException("The deadline must be set to a future date.");
    this.deadline = deadline;
  }

  /**
   * Setter for the priority group instance variable.
   * @param priorityGroup A value of either ["Critical", "High", "Low"] representing the importance of the requirement.
   * @throws IllegalArgumentException if the priority group argument is invalid.
   */
  public void setPriorityGroup(String priorityGroup) {
    if (!(priorityGroup.equals("Low") || priorityGroup.equals("Critical") || priorityGroup.equals("High"))) throw new IllegalArgumentException("Attempt to set an invalid priority group to the requirement.");
    this.priorityGroup = priorityGroup;
  }

  /**
   * Setter for the is approved instance variable.
   * @param isApproved A value of true or false representing whether the requirements is approved.
   */
  public void setApprovedOrDisapproved(boolean isApproved) {
    this.isApproved = isApproved;
  }

  // ------------------------------ Getters for Instance Variables ------------------------------

  /**
   * Getter for id instance variable.
   * @return Value of requirement's id.
   */
  public String getId() {
    return id;
  }

  /**
   * Getter for title instance variable.
   * @return Value of requirement's title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Getter for description instance variable.
   * @return Value of requirement's description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Getter for deadline instance variable.
   * @return Value of requirement's deadline.
   */
  public MyDate getDeadline() {
    return deadline;
  }

  /**
   * Getter for priority group instance variable.
   * @return Value of requirement's priority group.
   */
  public String getPriorityGroup() {
    return priorityGroup;
  }

  /**
   * Getter for isApproved instance variable
   * @return Whether requirement is approved.
   */
  public boolean isApproved() {
    return isApproved;
  }

  // ------------------------------ Getters for Assigned Tasks ------------------------------

  /**
   * Getter for the number of tasks assigned to the requirement.
   * @return The number of tasks assigned to the requirement.
   */
  public int getNumberOfAssignedTasks() {
    return assignedTasks.size();
  }

  /**
   * Getter for all the tasks assigned to the requirement.
   * @return All the tasks assigned to the requirement or empty array list if no tasks are assigned.
   */
  public ArrayList<Task> getAllAssignedTasks() {
    return assignedTasks;
  }

  // ------------------------------ Getters for Assigned Members ------------------------------

  /**
   * Getter for the number of members assigned to the requirement.
   * @return The number of members assigned to the requirement.
   */
  public int getNumberOfAssignedMembers() {
    ArrayList<Member> membersToReturn = new ArrayList<>();
    for (Task task : assignedTasks) membersToReturn.addAll(task.getAllAssignedMembers());
    return membersToReturn.size();
  }

  /**
   * Getter for all the members assigned to the requirement.
   * @return All the members assigned to the requirement or empty array list if no members are assigned.
   */
  public ArrayList<Member> getAllMembersWorkingOnRequirement() {
    ArrayList<Member> membersToReturn = new ArrayList<>();
    for (Task task : assignedTasks) membersToReturn.addAll(task.getAllAssignedMembers());
    return membersToReturn;
  }

  // ------------------------------ Other Methods ------------------------------

  /**
   * Assigns task to this requirement.
   * @param taskToAssign The task object to be assigned.
   * @throws UnsupportedOperationException if the task belongs to another project.
   */
  public void assignTask(Task taskToAssign) {
    if (!taskToAssign.getId().substring(0, 4).equals(getId().substring(0, 4))) throw new UnsupportedOperationException("Could not assign task because it belong to another project.");
    for (Task task : assignedTasks) if (task.getId().equals(taskToAssign.getId())) return;
    assignedTasks.add(taskToAssign);
    taskToAssign.assignRequirement(this);
  }

  /**
   * Unassigns task argument from this requirement.
   * @param taskToUnassign The task needed to be unassigned.
   */
  public void unassignTask(Task taskToUnassign) {
    for (int i = 0; i < assignedTasks.size(); i++) if (assignedTasks.get(i).getId().equals(taskToUnassign.getId())) {
      assignedTasks.remove(i);
      taskToUnassign.unassignRequirement(this);
      return;
    }
  }

  /**
   * Unassigns every task argument from this requirement.
   */
  public void unassignFromEveryRequirement() {
    for (Task task : assignedTasks) {
      unassignTask(task);
    }
  }

  /**
   * Getter for estimated work hours on this requirement.
   * @return Sum of all assigned task's estimated work hours.
   */
  public float getEstimatedWorkHours() {
    float estimatedWorkHours = 0;
    for (Task task : assignedTasks) estimatedWorkHours += task.getEstimatedWorkHours();
    return estimatedWorkHours;
  }

  /**
   * Getter for total worked hours on this requirement.
   * @return Sum of all assigned task's worked hours.
   */
  public float getTotalWorkedHours() {
    float totalWorkedHours = 0;
    for (Task task : assignedTasks) totalWorkedHours += task.getEstimatedWorkHours();
    return totalWorkedHours;
  }

  /**
   * Getter for the status of the requirement.
   * @return The percentage of completed linked tasks.
   */
  public float getStatus() {
    int numberOfTasks = getNumberOfAssignedTasks();
    int numberOfCompletedTasks = 0;

    if (numberOfTasks == 0) return 0;
    for (Task task : assignedTasks) if (task.getStatus().equals("Completed")) numberOfCompletedTasks++;
    return (float) numberOfCompletedTasks / numberOfTasks;
  }
}