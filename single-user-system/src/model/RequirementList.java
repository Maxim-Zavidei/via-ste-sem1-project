package model;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.io.Serializable;

/**
 * A class to process and store a requirements.
 */
public class RequirementList implements Serializable {

  // ------------------------------ Constructors ------------------------------

  private ArrayList<Requirement> requirementList;

  /**
   * Constructor to initialise the instance variables.
   */
  public RequirementList() {
    requirementList = new ArrayList<>();
  }

  // ------------------------------ Getters ------------------------------

  /**
   * Getter for the number of requirements linked to the project.
   * @return The number of tasks requirements to the project.
   */
  public int getNumberOfRequirements() {
    return requirementList.size();
  }

  /**
   * Getter for all the requirements linked to the project.
   * @return All the requirements linked to the project or empty array list if no requirements are linked.
   */
  public ArrayList<Requirement> getAllRequirements() {
    return requirementList;
  }

  /**
   * Getter for all the requirements linked to the project before a specific deadline.
   * @param deadline A date that will be compare with each deadline of the linked requirement.
   * @return All linked requirements that have a deadline before the given argument date or empty array list if no matching requirements were found or no requirements are linked.
   */
  public ArrayList<Requirement> getAllRequirementsBeforeDeadLine(MyDate deadline) {
    ArrayList<Requirement> toReturnRequirements = new ArrayList<>();
    for (Requirement requirement : requirementList) if (requirement.getDeadline().isBefore(deadline)) toReturnRequirements.add(requirement);
    return toReturnRequirements;
  }

  /**
   * Getter for all the requirements linked to the project completed over a certain percentage.
   * @param status A percentage that will be compared with each status of the linked requirements.
   * @return All linked requirements that have a status greater or equal to the given percentage as argument or empty array list if no matching requirements were found or no requirements are linked.
   * @throws IllegalArgumentException if the status argument is invalid.
   */
  public ArrayList<Requirement> getAllRequirementsWithStatusOver(float status) {
    if (status < 0 || 1 < status) throw new IllegalArgumentException("Status argument must be between [0; 1].");
    ArrayList<Requirement> toReturnRequirements = new ArrayList<>();
    for (Requirement requirement : requirementList) if (requirement.getStatus() >= status) toReturnRequirements.add(requirement);
    return toReturnRequirements;
  }

  /**
   * Getter for all the requirements linked to the project that have a specific priority.
   * @param priority A value of either ["Critical", "High", "Low"] that will be compared with each priority group of the linked requirements.
   * @return All linked requirements that have a matching the argument or empty array list if no matching requirements were found or no requirements are linked.
   * @throws IllegalArgumentException if the priority argument is invalid.
   */
  public ArrayList<Requirement> getAllRequirementsWithPriority(String priority) {
    if (!(priority.equals("Low") || priority.equals("Critical") || priority.equals("High"))) throw new IllegalArgumentException("Priority group must be a value of either [\"Critical\", \"High\", \"Low\"].");
    ArrayList<Requirement> toReturnRequirements = new ArrayList<>();
    for (Requirement requirement : requirementList) if (requirement.getPriorityGroup().equals(priority)) toReturnRequirements.add(requirement);
    return toReturnRequirements;
  }

  /**
   * Getter for all the requirements linked to the project that are marked as approved.
   * @return All linked requirements that are approved or empty array list if no approved requirements were found or no requirements are linked.
   */
  public ArrayList<Requirement> getAllApprovedRequirements() {
    ArrayList<Requirement> toReturnRequirements = new ArrayList<>();
    for (Requirement requirement : requirementList) if (requirement.isApproved()) toReturnRequirements.add(requirement);
    return toReturnRequirements;
  }

  /**
   * Getter for all the requirements linked to the project that are marked as disapproved.
   * @return All linked requirements that are disapproved or empty array list if no disapproved requirements were found or no requirements are linked.
   */
  public ArrayList<Requirement> getAllDisapprovedRequirements() {
    ArrayList<Requirement> toReturnRequirements = new ArrayList<>();
    for (Requirement requirement : requirementList) if (!requirement.isApproved()) toReturnRequirements.add(requirement);
    return toReturnRequirements;
  }

  /**
   * Getter for requirement linked to the project by index.
   * @param index Value between [0; requirementList.size() - 1] representing the position of the requirement in the list of the requirements to be returned.
   * @return The requirement at the position of the index.
   * @throws IllegalArgumentException if the index argument is invalid.
   * @throws ArrayIndexOutOfBoundsException if the index is out of bounds for the array list.
   */
  public Requirement getRequirementByIndex(int index) {
    if (index < 0 || index < requirementList.size() - 1) throw new IllegalArgumentException("Attempt to access array list with invalid index.");
    return requirementList.get(index);
  }

  /**
   * Getter for requirement linked to the project by id.
   * @param id The id of the requirement to be returned.
   * @return The requirement with the equal id.
   * @throws NoSuchElementException if a requirement with matching id could not be found.
   */
  public Requirement getRequirementById(String id) {
    for (Requirement requirement : requirementList) if (requirement.getId().equals(id)) {
      return requirement;
    }
    throw new NoSuchElementException("Could not find a requirement with given id.");
  }

  /**
   * Checks whether a requirement with a given id already exists.
   * @param id Id to check for.
   * @return Whether a requirement matching the id already exists.
   */
  public boolean isIdTaken(String id) {
    for (Requirement requirement : requirementList) if (requirement.getId().equals(id)) {
      return true;
    }
    return false;
  }

  /**
   * Generates a unique id that does not match any of the existing ids of all tasks.
   * @return The unique id.
   */
  private String generateId(String projectId) {
    Random randomizer = new Random();
    StringBuilder generatedId;
    char randomChar;

    do {
      generatedId = new StringBuilder(projectId + "R");
      for (int i = 0; i < 3; i++) {
        do randomChar = (char) (randomizer.nextInt(75) + 48); while (!(
            '0' <= randomChar && randomChar <= '9' ||
                'A' <= randomChar && randomChar <= 'Z' && randomChar != 'P' && randomChar != 'R' && randomChar != 'T' ||
                'a' <= randomChar && randomChar <= 'z'
        ));
        generatedId.append(randomChar);
      }
    } while (isIdTaken(generatedId.toString()));
    return generatedId.toString();
  }

  /**
   * Add method with extended number of defined values which creates and links a new requirement to the project.
   * @param title Title of the requirement.
   * @param description Description for the requirement.
   * @param priorityGroup A value of either ["Critical", "High", "Low"] representing requirement's priority group.
   * @return The newly created requirement.
   * @throws IllegalArgumentException if there is another requirement with the same title in the project.
   * @throws IllegalArgumentException if the requirement's title is longer then 14 chars.
   * @throws IllegalArgumentException if deadline is in the past.
   * @throws IllegalArgumentException if the priority group argument is invalid.
   */
  public Requirement addRequirement(String projectId, String title, String description, MyDate deadline, String priorityGroup) {
    for (Requirement requirement : requirementList) if (requirement.getTitle().equals(title)) throw new IllegalArgumentException("A requirement with this title already exists in the project.");
    Requirement toReturn = new Requirement(generateId(projectId), title, description, deadline, priorityGroup);
    requirementList.add(toReturn);
    return toReturn;
  }

  /**
   * Add method with minimal number of defined values which creates and links a new requirement to the project.
   * @param title Title of the requirement.
   * @param deadline MyDate object representing the deadline.
   * @param priorityGroup A value of either ["Critical", "High", "Low"] representing requirement's priority group.
   * @return The newly created requirement.
   * @throws IllegalArgumentException if there is another requirement with the same title in the project.
   * @throws IllegalArgumentException if the requirement's title is longer then 14 chars.
   * @throws IllegalArgumentException if deadline is in the past.
   * @throws IllegalArgumentException if the priority group argument is invalid.
   */
  public Requirement addRequirement(String projectId, String title, MyDate deadline, String priorityGroup) {
    for (Requirement requirement : requirementList) if (requirement.getTitle().equals(title)) throw new IllegalArgumentException("A requirement with this title already exists in the project.");
    Requirement toReturn = new Requirement(generateId(projectId), title, deadline, priorityGroup);
    requirementList.add(toReturn);
    return toReturn;
  }

  /**
   * Removes any requirement that matches the argument id.
   * @param id The id of the requirement to be removed.
   * @throws NoSuchElementException if a requirement with matching id could not be found.
   * @throws UnsupportedOperationException if the requirement is assigned to any tasks.
   */
  public void removeRequirement(String id) {
    Requirement requirement = getRequirementById(id);
    if (requirement.getNumberOfAssignedTasks() != 0) throw new UnsupportedOperationException("Could not remove requirement because it is assigned to some tasks.");
    requirementList.remove(requirement);
  }

  /**
   * Removes any requirement that matches the argument's requirement id.
   * @param requirement The requirement object of which id will be looked for to remove.
   * @throws IllegalArgumentException if the requirement argument is null.
   * @throws NoSuchElementException if a requirement with matching id could not be found.
   * @throws UnsupportedOperationException if the requirement is assigned to any tasks.
   */
  public void removeRequirement(Requirement requirement) {
    if (requirement == null) throw new IllegalArgumentException("Requirement argument is null.");
    removeRequirement(requirement.getId());
  }
}