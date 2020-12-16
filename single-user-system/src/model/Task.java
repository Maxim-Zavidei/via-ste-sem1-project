package model;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * A class to create, store and process tasks.
 */
public class Task implements Serializable {

  private String id;
  private String title;
  private String description;
  private String status;
  private float estimatedWorkHours;
  private float totalWorkedHours;
  private MyDate deadline;
  private ArrayList<Requirement> assignedRequirements;
  private ArrayList<Member> assignedMembers;

  // ------------------------------ Constructors ------------------------------

  /**
   * Constructor with extended number of defined values.
   * @param id Id value of the task.
   * @param title Title for the task
   * @param description Description of the project
   * @param estimatedWorkHours Value between [1; +inf] representing the number of expected work hours that need to be spent on the task.
   * @param deadline MyDate object representing the deadline.
   * @throws IllegalArgumentException if the task's title is longer then 14 chars.
   * @throws IllegalArgumentException if the estimated work hours argument is invalid.
   * @throws IllegalArgumentException if deadline is in the past.
   */
  public Task(String id, String title, String description, float estimatedWorkHours, MyDate deadline) {
    this.id = id;
    setTitle(title);
    setDescription(description);
    setStatus("Started");
    setEstimatedWorkHours(estimatedWorkHours);
    setTotalWorkedHours(0);
    setDeadline(deadline);
    assignedRequirements = new ArrayList<>();
    assignedMembers = new ArrayList<>();
  }

  /**
   * Constructor with minimal number of defined values.
   * @param id Id value of the task.
   * @param title Title for the task
   * @param estimatedWorkHours Value between [1; +inf] representing the number of expected work hours that need to be spent on the task.
   * @param deadline MyDate object representing the deadline.
   * @throws IllegalArgumentException if the task's title is longer then 14 chars.
   * @throws IllegalArgumentException if the estimated work hours argument is invalid.
   * @throws IllegalArgumentException if deadline is in the past.
   */
  public Task(String id, String title, float estimatedWorkHours, MyDate deadline) {
    this(id, title, "", estimatedWorkHours, deadline);
  }

  // ------------------------------ Setters ------------------------------

  /**
   * Setter for the title instance variable.
   * @param title Title of the task.
   * @throws IllegalArgumentException if the task's title is longer then 14 chars.
   */
  public void setTitle(String title) {
    if (title.length() > 14) throw new IllegalArgumentException("The task title can not be longer then 14 characters.");
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
   * Setter for the status instance variable.
   * @param status A value of either ["Started", "Finished"] representing the status.
   * @throws IllegalArgumentException if the status argument is invalid.
   */
  public void setStatus(String status) {
    if (!(status.equals("Started") || status.equals("Completed"))) throw new IllegalArgumentException("Attempt to set and invalid status to task.");
    this.status = status;
  }

  /**
   * Setter for the estimatedWorkHours instance variable.
   * @param estimatedWorkHours Value between [1; +inf] representing the number of expected work hours that need to be spent on the task.
   * @throws IllegalArgumentException if the estimated work hours argument is invalid.
   */
  public void setEstimatedWorkHours(float estimatedWorkHours) {
    if (estimatedWorkHours < 1) throw new IllegalArgumentException("Estimated work time can not be less then or equal to 0.");
    this.estimatedWorkHours = estimatedWorkHours;
  }

  /**
   * Setter for the totalWorkedHours instance variable.
   * @param totalWorkedHours Value between [0; +inf] representing the number of worked hours on the task.
   * @throws IllegalArgumentException if the total worked hours argument is invalid.
   */
  public void setTotalWorkedHours(int totalWorkedHours) {
    if (estimatedWorkHours < 0) throw new IllegalArgumentException("Estimated work time can not be less then 0.");
    this.totalWorkedHours = totalWorkedHours;
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

  // ------------------------------ Getters for Instance Variables ------------------------------

  /**
   * Getter for id instance variable.
   * @return Value of task's id.
   */
  public String getId() {
    return id;
  }

  /**
   * Getter for title instance variable.
   * @return Value of task's title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Getter for description instance variable.
   * @return Value of task's description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Getter for status instance variable.
   * @return Value of task's status.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Getter for estimated work hours instance variable.
   * @return Value of task's estimated work hours.
   */
  public float getEstimatedWorkHours() {
    return estimatedWorkHours;
  }

  /**
   * Getter for total worked total instance variable.
   * @return Value of task's total worked total.
   */
  public float getTotalWorkedHours() {
    return totalWorkedHours;
  }

  /**
   * Getter for deadline instance variable.
   * @return Value of task's deadline.
   */
  public MyDate getDeadline() {
    return deadline;
  }

  // ------------------------------ Getters for Assigned Requirements ------------------------------

  /**
   * Getter for the number of requirements assigned to the task.
   * @return The number of requirements assigned to the task.
   */
  public int getNumberOfAssignedRequirements() {
    return assignedRequirements.size();
  }

  /**
   * Getter for all the requirements assigned to the task.
   * @return All the requirements assigned to the task or empty array list if no requirements are assigned.
   */
  public ArrayList<Requirement> getAllAssignedRequirements() {
    return assignedRequirements;
  }

  // ------------------------------ Getters for Assigned Members ------------------------------

  /**
   * Getter for the number of members assigned to the task.
   * @return The number of members assigned to the task.
   */
  public int getNumberOfAssignedMembers() {
    return assignedMembers.size();
  }

  /**
   * Getter for all the members assigned to the task.
   * @return All the members assigned to the task or empty array list if no members are assigned.
   */
  public ArrayList<Member> getAllAssignedMembers() {
    return assignedMembers;
  }

  // ------------------------------ Other Methods ------------------------------

  /**
   * Assigns requirement to this task.
   * @param requirementToAssign The requirement object to be assigned.
   * @throws UnsupportedOperationException if the requirement belongs to another project.
   */
  public void assignRequirement(Requirement requirementToAssign) {
    if (!requirementToAssign.getId().substring(0, 4).equals(getId().substring(0, 4))) throw new UnsupportedOperationException("Could not assign requirement because it belong to another project.");
    for (Requirement requirement : assignedRequirements) if (requirement.getId().equals(requirementToAssign.getId())) return;
    assignedRequirements.add(requirementToAssign);
    requirementToAssign.assignTask(this);
  }

  /**
   * Unassigns requirement argument from this task.
   * @param requirementToUnassign The requirement needed to be unassigned.
   */
  public void unassignRequirement(Requirement requirementToUnassign) {
    for (int i = 0; i < assignedRequirements.size(); i++) if (assignedRequirements.get(i).getId().equals(requirementToUnassign.getId())) {
      assignedRequirements.remove(i);
      requirementToUnassign.unassignTask(this);
      return;
    }
  }

  /**
   * Unassigns every requirement argument from this task.
   */
  public void unassignFromEveryRequirement() {
    for (Requirement requirement : assignedRequirements) {
      unassignRequirement(requirement);
    }
  }

  /**
   * Assigns member to this task.
   * @param memberToAssign The member object to be assigned.
   */
  public void assignMember(Member memberToAssign) {
    for (Member member : assignedMembers) if (member.equals(memberToAssign)) return;
    assignedMembers.add(memberToAssign);
    memberToAssign.assignTask(this);
  }

  /**
   * Unassigns member argument from this task.
   * @param memberToUnassign The member needed to be unassigned.
   */
  public void unassignMember(Member memberToUnassign) {
    for (int i = 0; i < assignedMembers.size(); i++) if (assignedMembers.get(i).equals(memberToUnassign)) {
      assignedMembers.remove(i);
      memberToUnassign.unassignFromTask(this);
      return;
    }
  }

  /**
   * Unassigns every member argument from this task.
   */
  public void unassignEveryMember() {
    for (Member member : assignedMembers) {
      unassignMember(member);
    }
  }

  /**
   * Adds work hours to the instance variable.
   * @param hours Number of hours to add.
   */
  public void addWorkedTime(float hours) {
    totalWorkedHours += hours;
  }
}