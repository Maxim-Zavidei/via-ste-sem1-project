package model;

import java.util.ArrayList;

public class Project {

  private String id;
  private String title;
  private String description;
  private float status;
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
   * @param day The day of the project's deadline.
   * @param month The month of the project's deadline.
   * @param year The year of the project's deadline.
   * @throws IllegalArgumentException If date arguments are invalid.
   * @throws IllegalArgumentException If deadline is in the past.
   */
  public Project(String id, String title, String description, int day, int month, int year) {
    MyDate deadline = new MyDate(day, month, year);
    if (deadline.isBefore(new MyDate())) throw new IllegalArgumentException("The deadline must be set to a future date.");

    this.id = id;
    this.title = title;
    this.description = description;
    status = 0;
    this.deadline = deadline;
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
   * @param day The day of the project's deadline.
   * @param month The month of the project's deadline.
   * @param year The year of the project's deadline.
   * @throws IllegalArgumentException If date arguments are invalid.
   * @throws IllegalArgumentException If deadline is in the past.
   */
  public Project(String id, String title, int day, int month, int year) {
    this(id, title, "", day, month, year);
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

  public String getStatus()
  {
    return status;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public void setDate(MyDate date)
  {
    this.date = date;
  }

  public void setRole(String role)
  {
    this.role = role;
  }

  public MyDate getDate()
  {
    return date;
  }

  public String getRole()
  {
    return role;
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
