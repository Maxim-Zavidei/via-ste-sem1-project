package model;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Random;
import java.util.NoSuchElementException;
import java.io.Serializable;

/**
 * A class store and process project objects.
 */
public class ProjectList implements Serializable {

  ArrayList<Project> projectList;

  // ------------------------------ Constructors ------------------------------

  /**
   * Constructor to initialise all instance variables.
   */
  public ProjectList() {
    projectList = new ArrayList<>();
  }

  // ------------------------------ Getters for Projects ------------------------------

  /**
   * Getter for the number of projects created so far.
   * @return The number of projects created so far.
   */
  public int getNumberOfProjects() {
    return projectList.size();
  }

  /**
   * Getter for all the projects created so far.
   * @return All the projects created so far or empty array list if no projects exist.
   */
  public ArrayList<Project> getAllProjects() {
    return projectList;
  }

  /**
   * Getter for all projects before a specific deadline.
   * @param deadline A date that will be compare with each project's deadline.
   * @return All projects that have a deadline before the given argument date or empty array list if no matching projects were found or no projects exist.
   */
  public ArrayList<Project> getAllProjectsBeforeDeadline(MyDate deadline) {
    ArrayList<Project> toReturnProjects = new ArrayList<>();
    for (Project project : projectList) if (project.getDeadline().isBefore(deadline)) toReturnProjects.add(project);
    return toReturnProjects;
  }

  /**
   * Getter for all the projects completed over a certain percentage.
   * @param status A percentage that will be compared with status of each project.
   * @return All projects that have a status greater or equal to the given percentage as argument or empty array list if no matching projects were found or no projects exist.
   * @throws IllegalArgumentException if the status argument is invalid.
   */
  public ArrayList<Project> getAllProjectsWithStatusOver(float status) {
    if (status < 0 || 1 < status) throw new IllegalArgumentException("Status argument must be between [0; 1].");
    ArrayList<Project> toReturnProjects = new ArrayList<>();
    int projectTasksNumber;
    float projectStatus;

    for (Project project : projectList) {
      projectTasksNumber = project.getNumberOfTasks();
      projectStatus = projectTasksNumber == 0 ? 0 : (float) project.getAllTasksWithStatus("Completed").size() / projectTasksNumber;
      if (projectStatus >= status) toReturnProjects.add(project);
    }
    return toReturnProjects;
  }

  /**
   * Getter for any project by index.
   * @param index Value between [0; projectList.size() - 1] representing the position of the project in the list of the projects to be returned.
   * @return The project at the position of the index.
   * @throws IllegalArgumentException if the index argument is invalid.
   * @throws ArrayIndexOutOfBoundsException if the index is out of bounds for the array list.
   */
  public Project getProjectByIndex(int index) {
    if (index < 0 || index > projectList.size() - 1) throw new IllegalArgumentException("Attempt to access array list with invalid index.");
    return projectList.get(index);
  }

  /**
   * Getter for any project by id.
   * @param id The id of the project to be returned.
   * @return The project with the equal id.
   * @throws NoSuchElementException if a project with matching id could not be found.
   */
  public Project getProjectById(String id) {
    for (Project project : projectList) if (project.getId().equals(id)) {
      return project;
    }
    throw new NoSuchElementException("Could not find a project with given id.");
  }

  // ------------------------------ Getters for Requirements ------------------------------

  /**
   * Getter for the number of requirements created so far.
   * @return The number of requirements created so far.
   */
  public int getTotalNumberOfRequirements() {
    int totalNumberOfRequirements = 0;
    for (Project project : projectList) totalNumberOfRequirements += project.getNumberOfRequirements();
    return totalNumberOfRequirements;
  }

  /**
   * Getter for all the requirements created so far.
   * @return All the requirements created so far or empty array list if no requirements exist.
   */
  public ArrayList<Requirement> getAllRequirements() {
    ArrayList<Requirement> toReturnRequirements = new ArrayList<>();
    for (Project project : projectList) toReturnRequirements.addAll(project.getAllRequirements());
    return toReturnRequirements;
  }

  /**
   * Getter for all requirements before a specific deadline.
   * @param deadline A date that will be compare with each requirement's deadline.
   * @return All requirements that have a deadline before the given argument date or empty array list if no matching requirements were found or no requirements exist.
   */
  public ArrayList<Requirement> getAllRequirementsBeforeDeadline(MyDate deadline) {
    ArrayList<Requirement> toReturnRequirements = new ArrayList<>();
    for (Project project : projectList) toReturnRequirements.addAll(project.getAllRequirementsBeforeDeadline(deadline));
    return toReturnRequirements;
  }

  /**
   * Getter for all the requirements completed over a certain percentage.
   * @param status A percentage that will be compared with each status of all requirements.
   * @return All requirements that have a status greater or equal to the given percentage as argument or empty array list if no matching requirements were found or no requirements exist.
   * @throws IllegalArgumentException if the status argument is invalid.
   */
  public ArrayList<Requirement> getAllRequirementsWithStatusOver(float status) {
    ArrayList<Requirement> toReturnRequirements = new ArrayList<>();
    for (Project project : projectList) toReturnRequirements.addAll(project.getAllRequirementsWithStatusOver(status));
    return toReturnRequirements;
  }

  /**
   * Getter for all requirements that have a specific priority.
   * @param priority A value of either ["Critical", "High", "Low"] that will be compared with each priority group of all requirements.
   * @return All requirements that have a matching the argument or empty array list if no matching requirements were found or no requirements exist.
   * @throws IllegalArgumentException if the priority argument is invalid.
   */
  public ArrayList<Requirement> getAllRequirementsWithPriority(String priority) {
    ArrayList<Requirement> toReturnRequirements = new ArrayList<>();
    for (Project project : projectList) toReturnRequirements.addAll(project.getAllRequirementsWithPriority(priority));
    return toReturnRequirements;
  }

  /**
   * Getter for all requirements that are marked as approved.
   * @return All requirements that are approved or empty array list if no approved requirements were found or no requirements exist.
   */
  public ArrayList<Requirement> getAllApprovedRequirements() {
    ArrayList<Requirement> toReturnRequirements = new ArrayList<>();
    for (Project project : projectList) toReturnRequirements.addAll(project.getAllApprovedRequirements());
    return toReturnRequirements;
  }

  /**
   * Getter for all requirements that are marked as disapproved.
   * @return All requirements that are disapproved or empty array list if no disapproved requirements were found or no requirements exist.
   */
  public ArrayList<Requirement> getAllDisapprovedRequirements() {
    ArrayList<Requirement> toReturnRequirements = new ArrayList<>();
    for (Project project : projectList) toReturnRequirements.addAll(project.getAllDisapprovedRequirements());
    return toReturnRequirements;
  }

  /**
   * Getter for any requirement by id.
   * @param id The id of the requirement to be returned.
   * @return The requirement with the equal id.
   * @throws NoSuchElementException if a requirement with matching id could not be found.
   */
  public Requirement getRequirementById(String id) {
    for (Requirement requirement : getAllRequirements()) if (requirement.getId().equals(id)) {
      return requirement;
    }
    throw new NoSuchElementException("Could not find a requirement with given id.");
  }

  // ------------------------------ Getters for Tasks ------------------------------

  /**
   * Getter for the number of tasks created so far.
   * @return The number of tasks created so far.
   */
  public int getTotalNumberOfTasks() {
    int totalNumberOfTasks = 0;
    for (Project project : projectList) totalNumberOfTasks += project.getNumberOfTasks();
    return totalNumberOfTasks;
  }

  /**
   * Getter for all the tasks created so far.
   * @return All the tasks created so far or empty array list if no tasks exist.
   */
  public ArrayList<Task> getAllTasks() {
    ArrayList<Task> toReturnTasks = new ArrayList<>();
    for (Project project : projectList) toReturnTasks.addAll(project.getAllTasks());
    return toReturnTasks;
  }

  /**
   * Getter for all tasks before a specific deadline.
   * @param deadline A date that will be compare with each task's deadline.
   * @return All tasks that have a deadline before the given argument date or empty array list if no matching tasks were found or no tasks exist.
   */
  public ArrayList<Task> getAllTasksBeforeDeadline(MyDate deadline) {
    ArrayList<Task> toReturnTasks = new ArrayList<>();
    for (Project project : projectList) toReturnTasks.addAll(project.getAllTasksBeforeDeadline(deadline));
    return toReturnTasks;
  }

  /**
   * Getter for all tasks that have a matching status.
   * @param status A value of either ["Started", "Finished"] that will be compared with each status of all tasks.
   * @return All tasks that have a status matching the argument or empty array list if no matching tasks were found or no tasks are exist.
   * @throws IllegalArgumentException if the status argument is invalid.
   */
  public ArrayList<Task> getAllTasksWithStatus(String status) {
    ArrayList<Task> toReturnTasks = new ArrayList<>();
    for (Project project : projectList) toReturnTasks.addAll(project.getAllTasksWithStatus(status));
    return toReturnTasks;
  }

  /**
   * Getter for any task linked by id.
   * @param id The id of the task to be returned.
   * @return The task with the equal id.
   * @throws NoSuchElementException if a task with matching id could not be found.
   */
  public Task getTaskById(String id) {
    for (Task task : getAllTasks()) if (task.getId().equals(id)) {
        return task;
    }
    throw new NoSuchElementException("Could not find a task with given id.");
  }

  // ------------------------------ Other Methods ------------------------------

  /**
   * Checks whether a project with a given id already exists.
   * @param id Id to check for.
   * @return Whether a project matching the id already exists.
   */
  public boolean isIdTaken(String id) {
    for (Project project : projectList) if (project.getId().equals(id)) {
      return true;
    }
    return false;
  }

  /**
   * Generates a unique id that does not match any of the existing ids of all projects.
   * @return The unique id.
   */
  private String generateId() {
    Random randomizer = new Random();
    StringBuilder generatedId;
    char randomChar;

    do {
      generatedId = new StringBuilder("P");
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
   * Add method with extended number of defined values which creates a new project.
   * @param title Title for the project.
   * @param description Description of the project.
   * @param day Value between [1; 31] representing deadline's day.
   * @param month Value between [1; 12] representing deadline's month.
   * @param year Value between [1; +inf] representing deadline's year.
   * @return The newly created project.
   * @throws IllegalArgumentException if a project with the same title already exists.
   * @throws IllegalArgumentException if the project's title is longer then 14 chars.
   * @throws IllegalArgumentException if the deadline arguments are invalid.
   * @throws IllegalArgumentException if deadline is in the past.
   */
  public Project addProject(String title, String description, int day, int month, int year) {
    //for (Project project : projectList) if (project.getTitle().equals(title)) throw new IllegalArgumentException("A project with this title already exists.");
    //if (title.length() > 14) throw new IllegalArgumentException("The project title can not be longer then 14 characters.");
    Project toReturn = new Project(generateId(), title, description, day, month, year);
    projectList.add(toReturn);
    return toReturn;
  }

  /**
   * Add method with minimal number of defined values which creates a new project.
   * @param title Title for the project.
   * @param day Value between [1; 31] representing deadline's day.
   * @param month Value between [1; 12] representing deadline's month.
   * @param year Value between [1; +inf] representing deadline's year.
   * @return The newly created project.
   * @throws IllegalArgumentException if a project with the same title already exists.
   * @throws IllegalArgumentException if the project's title is longer then 14 chars.
   * @throws IllegalArgumentException if the deadline arguments are invalid.
   * @throws IllegalArgumentException if deadline is in the past.
   */
  public Project addProject(String title, int day, int month, int year) {
    //for (Project project : projectList) if (project.getTitle().equals(title)) throw new IllegalArgumentException("A project with this title already exists.");
    //if (title.length() > 14) throw new IllegalArgumentException("The project title can not be longer then 14 characters.");
    Project toReturn = new Project(generateId(), title, day, month, year);
    projectList.add(toReturn);
    return toReturn;
  }

  /**
   * Removes the project.
   * @param project The project which should be removed.
   * @throws IllegalArgumentException if the project argument is null.
   * @throws UnsupportedOperationException if the project has any linked requirements.
   * @throws UnsupportedOperationException if the project has any linked tasks.
   */
  public void removeProject(Project project) {
    if (project == null) throw new IllegalArgumentException("Project argument is null.");
    if (project.getNumberOfRequirements() != 0) throw new UnsupportedOperationException("Could not remove project because it has linked requirements.");
    if (project.getNumberOfTasks() != 0) throw new UnsupportedOperationException("Could not remove project because it has linked tasks.");
    projectList.remove(project);
  }

  /**
   * Removes the project by id.
   * @param id The id of the project which should be removed.
   * @throws NoSuchElementException if a project with matching id could not be found.
   * @throws UnsupportedOperationException if the project has any linked requirements.
   * @throws UnsupportedOperationException if the project has any linked tasks.
   */
  public void removeProject(String id) {
    Project project = getProjectById(id);
    if (project.getNumberOfRequirements() != 0) throw new UnsupportedOperationException("Could not remove project because it has linked requirements.");
    if (project.getNumberOfTasks() != 0) throw new UnsupportedOperationException("Could not remove project because it has linked tasks.");
    projectList.remove(project);
  }
}