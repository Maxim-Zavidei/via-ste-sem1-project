package model;

import java.util.ArrayList;

public class Project
{

  private String id;
  private String title;
  private String description;
  private String status;
  private MyDate date;
  private String role;
  private MemberList memberList;
  private RequirementList requirementList;
  private TaskList taskList;
  private Member projectCreator;
  private Member productOwner;
  private Member scrumMaster;

  public Project(String id, String description, String title, int day,
      int month, int year, String status)
  {
    this.id = id;
    this.description = description;
    date = new MyDate(day, month, year);
    this.title = title;
    this.status = status;
  }

  public Project(String title, String description, int day, int month, int year,
      String role)
  {
    this.title = title;
    this.description = description;
    date = new MyDate(day, month, year);
    this.role = role;
  }

  public boolean addTask(String id, String title, String description, int day,
      int month, int year)
  {
    Task task = new Task(id, title, description, new MyDate(day, month, year));

    if (taskList.addTask(task))
    {
      return true;
    }

    return false;
  }

  public boolean removeTask(Task task)
  {
    if (taskList.removeTask(task))
    {
      return true;
    }
    return false;
  }

  public boolean removeTask(String id)
  {
    if (taskList.removeTask(id))
    {
      return true;
    }
    return false;
  }

  public boolean addRequirement(Requirement requirement)
  {
    for (Requirement temp : requirementList)
    {
      if (temp.equals(requirement))
      {
        return false;
      }
    }
    requirementList.add(requirement);
    return true;
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
    return requirementList;
  }

  public String getId()
  {
    return id;
  }

  public boolean removeRequirement(Requirement requirement)
  {
    for (Requirement temp : requirementList)
    {
      if (temp.equals(requirement))
      {
        requirementList.remove(requirement);
        return true;
      }
    }
    return false;
  }

  public ArrayList<Requirement> getAllRequirements()
  {
    return requirementList;
  }

  public ArrayList<Member> getMemberList()
  {
    return memberList;
  }

  public ArrayList<Task> getTaskList()
  {
    return taskList.getAllTask();
  }

  public int getNumberOfRequirements()
  {
    return requirementList.size();
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
    return requirementList.getRequirementsWithGivenStatus(status);
  }

  public ArrayList<Requirement> getRequirementsBeforeDeadline(MyDate deadline)
  {
    return requirementList.getRequirementsWithGivernStatus(deadline);
  }

  public ArrayList<Requirement> getRequierementsByPriority(String priority)
  {
    return requirementList.getRequirementsByPriority(priority);
  }

  public ArrayList<Requirement> getApprovedRequirement()
  {
    return requirementList.getApprovedRequirements();
  }

  public ArrayList<Requirement> getDisapprovedRequirements()
  {
    return requirementList.getDisapprovedRequirements();
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
