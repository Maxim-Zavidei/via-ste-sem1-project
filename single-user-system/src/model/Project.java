package model;

import java.util.ArrayList;

public class Project {

  private String id;
  private String title;
  private String description;
  private float status;
  private float estimatedWorkHours;
  private MyDate deadline;
  private MemberList memberList;
  private RequirementList requirementList;
  private TaskList taskList;
  private Member projectCreator;
  private Member productOwner;
  private Member scrumMaster;

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
    status = 0;
    setEstimatedWorkHours(estimatedWorkHours);
    setDeadline(day, month, year);
    memberList = new MemberList();
    requirementList = new RequirementList();
    taskList = new TaskList();
    projectCreator = null;
    productOwner = null;
    scrumMaster = null;
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

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }

  public float getStatus()
  {
    return status;
  }

  public void setStatus(float status)
  {
    this.status = status;
  }

  public ArrayList<Requirement> getRequirementList()
  {
    return requirementList.getAllRequirements();
  }

  public String getId()
  {
    return id;
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

  public ArrayList<Requirement> getAllRequirements()
  {
    return requirementList.getAllRequirements();
  }

  public ArrayList<Member> getMemberList()
  {
    return memberList.getAllMembers();
  }

  public ArrayList<Task> getTaskList()
  {
    return taskList.getAllTask();
  }

  public int getNumberOfRequirements()
  {
    return requirementList.getAllRequirements().size();
  }

  public int getNumberOfTasks()
  {
    return taskList.getNumberOfTasks();
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

  public ArrayList<Task> getAllTaskWithGivenStatus(String status)
  {
    return taskList.getAllTasksWithFGivenStatus(status);
  }

  public ArrayList<Task> getAllTaskBeforeDeadline(MyDate deadline)
  {
    return taskList.getTaskBeforeDeadLine(deadline);
  }

  public ArrayList<Requirement> getRequirementsWithGivenStatus(String status)
  {
    return requirementList.getAllRequirementsWithStatus(status);
  }

  public ArrayList<Requirement> getRequirementsBeforeDeadline(MyDate deadline)
  {
    return requirementList.getAllRequirementsBeforeDeadline(deadline);
  }

  public ArrayList<Requirement> getRequierementsByPriority(String priority)
  {
    return requirementList.getAllRequirementsByPriority(priority);
  }

  public ArrayList<Requirement> getApprovedRequirement()
  {
    return requirementList.getAllApprovedRequirements();
  }

  public ArrayList<Requirement> getDisapprovedRequirements()
  {
    return requirementList.getAllDisapprovedRequirements();
  }

  public Requirement getRequirementByIndex(int ind)
  {
    return requirementList.getRequirementByIndex(ind);
  }

  public Requirement getRequirementById(String id)
  {
    return requirementList.getRequirementById(id);
  }

  public Task getTaskByIndex(int ind)
  {
    return taskList.getTaskByIndex(ind);
  }

  public Task getTaskById(String id)
  {
    return taskList.getTaskById(id);
  }

  // these 3 next methods should call an InputMismatchException

  public void assignScrumMaster(Member scrumMaster)
  {
    this.scrumMaster = scrumMaster;
  }

  public void assignProductOwner(Member productOwner)
  {
    this.productOwner = productOwner;
  }

  public void assignProjectCreator(Member projectCreator)
  {
    this.projectCreator = projectCreator;
  }

  public Member getProjectCreator()
  {
    return projectCreator;
  }

  public Member getProductOwner()
  {
    return productOwner;
  }

  public Member getScrumMaster()
  {
    return scrumMaster;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Project))
    {
      return false;
    }

    Project other = (Project) obj;

    return this.id == other.id;
  }

}
