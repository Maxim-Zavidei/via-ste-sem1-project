package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A class to create and store and process projects and its elements.
 */
public class Project {

  private String id;
  private String title;
  private String description;
  private float estimatedWorkHours;
  private MyDate deadline;
  private Member projectCreator;
  private Member productOwner;
  private Member scrumMaster;
  private RequirementList requirementList;
  private TaskList taskList;

  // ------------------------------ Constructors ------------------------------

  /**
   * Constructor with extended number of defined values.
   * @param id Id value of the project.
   * @param title Title for the project.
   * @param description Description of the project.
   * @param estimatedWorkHours Value between [1; +inf] representing the number of expected work hours that need to be spent on the project.
   * @param day Value between [1; 31] representing deadline's day.
   * @param month Value between [1; 12] representing deadline's month.
   * @param year Value between [1; +inf] representing deadline's year.
   * @throws IllegalArgumentException if the estimated work hours argument is invalid.
   * @throws IllegalArgumentException if date arguments are invalid.
   * @throws IllegalArgumentException if deadline is in the past.
   */
  public Project(String id, String title, String description, float estimatedWorkHours, int day, int month, int year) {
    this.id = id;
    this.title = title;
    setDescription(description);
    setEstimatedWorkHours(estimatedWorkHours);
    setDeadline(day, month, year);
    projectCreator = null;
    productOwner = null;
    scrumMaster = null;
    requirementList = new RequirementList();
    taskList = new TaskList();
  }

  /**
   * Constructor with minimal number of defined values.
   * @param id Id value of the project.
   * @param title Title for the project.
   * @param estimatedWorkHours Value between [1; +inf] representing the number of expected work hours that need to be spent on the project.
   * @param day Value between [1; 31] representing deadline's day.
   * @param month Value between [1; 12] representing deadline's month.
   * @param year Value between [1; +inf] representing deadline's year.
   * @throws IllegalArgumentException if the estimated work hours argument is invalid.
   * @throws IllegalArgumentException if date arguments are invalid.
   * @throws IllegalArgumentException if deadline is in the past.
   */
  public Project(String id, String title, float estimatedWorkHours, int day, int month, int year) {
    this(id, title, "", estimatedWorkHours, day, month, year);
  }

  // ------------------------------ Setters ------------------------------

  /**
   * Setter for the description instance variable.
   * @param description Description of the project.
   * @return Whether description was set successfully.
   */
  public boolean setDescription(String description) {
    this.description = description;
    return true;
  }

  /**
   * Setter for the estimatedWorkHours instance variable.
   * @param estimatedWorkHours Value between [1; +inf] representing the number of expected work hours that need to be spent on the project.
   * @return Whether the estimated work hours were set successfully.
   * @throws IllegalArgumentException if the estimated work hours argument is invalid.
   */
  public boolean setEstimatedWorkHours(float estimatedWorkHours) {
    if (estimatedWorkHours < 1) throw new IllegalArgumentException("Estimated work time can not be less then or equal to 0.");
    this.estimatedWorkHours = estimatedWorkHours;
    return true;
  }

  /**
   * Setter for the deadline instance variable.
   * @param day Value between [1; 31] representing deadline's day.
   * @param month Value between [1; 12] representing deadline's month.
   * @param year Value between [1; +inf] representing deadline's year.
   * @return Whether deadline was set successfully.
   * @throws IllegalArgumentException if the arguments are invalid.
   * @throws IllegalArgumentException if deadline is in the past.
   */
  public boolean setDeadline(int day, int month, int year) {
    MyDate deadline = new MyDate(day, month, year);
    if (deadline.isBefore(new MyDate())) throw new IllegalArgumentException("The deadline must be set to a future date.");
    this.deadline = deadline;
    return true;
  }

  /**
   * Setter for the projectCreator instance variable.
   * @param projectCreator Object of type member representing the project creator.
   * @return Whether project creator was set successfully.
   */
  public boolean setProjectCreator(Member projectCreator) {
    this.projectCreator = projectCreator;
    return true;
  }

  /**
   * Setter for the productOwner instance variable.
   * @param productOwner Object of type member representing the project owner.
   * @return Whether product owner was set successfully.
   */
  public boolean setProductOwner(Member productOwner) {
    this.productOwner = productOwner;
    return true;
  }

  /**
   * Setter for the scrumMaster instance variable.
   * @param scrumMaster Object of type member representing the project scrum master.
   * @return Whether scrum master was set successfully.
   */
  public boolean setScrumMaster(Member scrumMaster) {
    this.scrumMaster = scrumMaster;
    return true;
  }

  // ------------------------------ Getters for Instance Variables ------------------------------

  /**
   * Getter for Id instance variable.
   * @return Value of project's id.
   */
  public String getId() {
    return id;
  }

  /**
   * Getter for title instance variable.
   * @return Value of project's title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Getter for description instance variable.
   * @return Value of project's description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Getter for estimatedWorkHours instance variable.
   * @return Value of project's estimated work hours.
   */
  public float getEstimatedWorkHours() {
    return estimatedWorkHours;
  }

  /**
   * Getter for deadline instance variable.
   * @return MyDate object representing project's deadline.
   */
  public MyDate getDeadline() {
    return deadline;
  }

  /**
   * Getter for project creator instance variable.
   * @return Member object representing the project creator.
   */
  public Member getProjectCreator() {
    return projectCreator;
  }

  /**
   * Getter for product owner instance variable.
   * @return Member object representing the product owner.
   */
  public Member getProductOwner() {
    return productOwner;
  }

  /**
   * Getter for scrum master instance variable.
   * @return Member object representing the scrum master.
   */
  public Member getScrumMaster() {
    return scrumMaster;
  }

  // ------------------------------ Getters for Requirements Linked to Project ------------------------------

  /**
   * Getter for the number of requirements linked to the project.
   * @return The number of requirements linked to the project.
   */
  public int getNumberOfRequirements() {
    return requirementList.getNumberOfRequirements();
  }

  /**
   * Getter for all the requirements linked to the project.
   * @return All the requirements linked to the project or null if no requirements are linked.
   */
  public ArrayList<Requirement> getAllRequirements() {
    return requirementList.getAllRequirements();
  }

  /**
   * Getter for all the requirements linked to the project before a specific deadline.
   * @param deadline A date that will be compared with each deadline of the linked requirements.
   * @return All linked requirements that have a deadline before the given argument date or null if no matching requirements were found.
   */
  public ArrayList<Requirement> getAllRequirementsBeforeDeadline(MyDate deadline) {
    return requirementList.getAllRequirementsBeforeDeadline(deadline);
  }

  /**
   * Getter for all the requirements linked to the project completed over a certain percentage.
   * @param status A percentage that will be compared with each status of the linked requirements.
   * @return All linked requirements that have a status greater or equal to the given percentage as argument or null if no matching requirements were found.
   * @throws IllegalArgumentException if the status argument is invalid.
   */
  public ArrayList<Requirement> getAllRequirementsWithStatusOver(float status) {
    return requirementList.getAllRequirementsWithStatusOver(status);
  }

  /**
   * Getter for all the requirements linked to the project that have a specific priority.
   * @param priority A value of either ["Critical", "High", "Low"] that will be compared with each priority group of the linked requirements.
   * @return All linked requirements that have a matching the argument or null if no matching requirements were found.
   */
  public ArrayList<Requirement> getAllRequirementsWithPriority(String priority) {
    return requirementList.getAllRequirementsWithPriority(priority);
  }

  /**
   * Getter for all the requirements linked to the project that are marked as approved.
   * @return All linked requirements that are approved or null if no approved requirements were found.
   */
  public ArrayList<Requirement> getAllApprovedRequirements() {
    return requirementList.getAllApprovedRequirements();
  }

  /**
   * Getter for all the requirements linked to the project that are marked as disapproved.
   * @return All linked requirements that are disapproved or null if no disapproved requirements were found.
   */
  public ArrayList<Requirement> getAllDisapprovedRequirements() {
    return requirementList.getAllDisapprovedRequirements();
  }

  /**
   * Getter for requirement linked to the project by index.
   * @param index The position in the list of the requirement to be returned.
   * @return The requirement at the position of the index.
   * @throws IllegalArgumentException if the index argument is invalid.
   * @throws ArrayIndexOutOfBoundsException if the index is out of bounds for the array.
   */
  public Requirement getRequirementByIndex(int index) {
    return requirementList.getRequirementByIndex(index);
  }

  /**
   * Getter for requirement linked to the project by id.
   * @param id The id of the requirement to be returned.
   * @return The requirement with the equal id.
   * @throws NoSuchElementException if a requirement with matching id could not be found.
   */
  public Requirement getRequirementById(String id) {
    return requirementList.getRequirementById(id);
  }

  // ------------------------------ Getters for Tasks Linked to Project ------------------------------

  /**
   * Getter for the number of tasks linked to the project.
   * @return The number of tasks linked to the project.
   */
  public int getNumberOfTasks() {
    return taskList.getNumberOfTasks();
  }

  /**
   * Getter for all the tasks linked to the project.
   * @return All the tasks linked to the project or null if no tasks are linked.
   */
  public ArrayList<Task> getAllTasks() {
    return taskList.getAllTasks();
  }

  /**
   * Getter for all the tasks linked to the project before a specific deadline.
   * @param deadline A date that will be compare with each deadline of the linked task.
   * @return All linked tasks that have a deadline before the given argument date.
   */
  public ArrayList<Task> getAllTaskBeforeDeadline(MyDate deadline) {
    return taskList.getAllTaskBeforeDeadLine(deadline);
  }

  /**
   * Getter for all the tasks linked to the project that have a matching status.
   * @param status A value of either ["Started", "Finished"] that will be compared with each status of the linked tasks.
   * @return All linked tasks that have a status matching the argument or null if no matching requirements were found.
   */
  public ArrayList<Task> getAllTaskWithStatus(String status) {
    return taskList.getAllTaskWithStatus(status);
  }

  /**
   * Getter for task linked to the project by index.
   * @param index The position in the list of the task to be returned.
   * @return The task at the position of the index.
   * @throws IllegalArgumentException if the index argument is invalid.
   * @throws ArrayIndexOutOfBoundsException if the index is out of bounds for the array.
   */
  public Task getTaskByIndex(int index) {
    return taskList.getTaskByIndex(index);
  }

  /**
   * Getter for task linked to the project by id.
   * @param id The id of the task to be returned.
   * @return The task with the equal id.
   * @throws NoSuchElementException if a task with matching id could not be found.
   */
  public Task getTaskById(String id) {
    return taskList.getTaskById(id);
  }

  // ------------------------------ Other Methods ------------------------------

  public boolean addTask(String id, String title, String description, int day, int month, int year) {

    Task task = new Task(id, title, description, new MyDate(day, month, year));

    if (taskList.addTask(task))
    {
      return true;
    }

    return false;
  }

  public boolean addRequirement(Requirement requirement) {
    for (Requirement temp : requirementList.getAllApprovedRequirements())
    {
      if (temp.equals(requirement))
      {
        return false;
      }
    }

    requirementList.addRequirement(requirement);
    return true;
  }

  public boolean removeTask(Task task) {
    if (taskList.removeTask(task))
    {
      return true;
    }
    return false;
  }

  public boolean removeTask(String id) {
    if (taskList.removeTask(id))
    {
      return true;
    }
    return false;
  }

  public boolean removeRequirement(Requirement requirement)
  {
    for (Requirement temp : requirementList.getAllRequirements())
    {
      if (temp.equals(requirement))
      {
        requirementList.removeRequirement(requirement);
        return true;
      }
    }
    return false;
  }

  public int getEstimatedTimeSpent()
  {
    int tempNum = 0;

    for (Task task : taskList.getAllTask())
    {
      tempNum += task.getEstimatedWorkHours();
    }
    return tempNum;
  }
}
