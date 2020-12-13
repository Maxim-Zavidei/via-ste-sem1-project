package model;

import java.util.ArrayList;

/**
 *  A class to create, store and process requirements.
 */
public class Requirement {

  private String id;
  private String title;
  private String description;
  private String status;
  private String priorityGroup;
  private boolean isApproved;
  private MyDate deadline;
  private ArrayList<Task> assignedTask;

  /**
   * Constructor with requirement type values
   * @param id  unique identifier
   * @param title the name of the requirement
   * @param description a description of the requirement
   * @param deadline the date that the requirement is supposed to be done
   * @param priorityGroup the importance of the requirement
   */
  public Requirement(String id, String title, String description,
      MyDate deadline, String priorityGroup)
  {
    setTitle(title);
    setId(id);
    setDescription(description);
    setDeadline(deadline);
    setPriorityGroup(priorityGroup);
    isApproved = false;
    assignedTask = new ArrayList<>();
  }

  /**
   * Constructor with requirement type values, description is initialised to null
   * @param id  unique identifier
   * @param title the name of the requirement
   * @param deadline the date that the requirement is supposed to be done
   * @param priorityGroup the importance of the requirement
   */
  public Requirement(String id, String title, MyDate deadline,
      String priorityGroup)
  {
    setTitle(title);
    setId(id);
    setDeadline(deadline);
    setPriorityGroup(priorityGroup);
    description = null;
    isApproved = false;
    assignedTask = new ArrayList<>();

  }

  /**
   * Getter for the id instance variable
   * @return value of id
   */
  public String getId()
  {
    return id;
  }

  /**
   * Setter for the Id instance variable
   * @param id
   */
  public void setId(String id)
  {
    this.id = id;
  }

  /**
   * Getter for the title instance variable
   * @return title
   */
  public String getTitle()
  {
    return title;
  }

  /**
   * Setter for title instance variable
   * @param title
   */
  public void setTitle(String title)
  {
    this.title = title;
  }

  /**
   * Getter for description instance variable
   * @return description
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Setter for description instance variable
   * @param description
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  /**
   * Getter for status instance variable
   * @return status
   */
  public String getStatus()
  {
    return status;
  }

  /**
   * Setter for status instance variable
   * @param status
   */
  public void setStatus(String status)
  {
    this.status = status;
  }

  /**
   * Getter for priorityGroup instance variable
   * @return priorityGroup
   */
  public String getPriorityGroup()
  {
    return priorityGroup;
  }

  /**
   * Setter for priorityGroup
   * @param priorityGroup
   */
  public void setPriorityGroup(String priorityGroup)
  {
    this.priorityGroup = priorityGroup;
  }

  /**
   * Getter for deadline instance variable
   * @return deadline
   */
  public MyDate getDeadline()
  {
    return deadline;
  }

  /**
   * Setter for deadline instance variable
   * @param deadline
   */
  public void setDeadline(MyDate deadline)
  {
    this.deadline = deadline;
  }

  /**
   * method for getting the estimated worked hours on the tasks
   * @return number of estimated worked hours
   */
  public int getEstimatedWorkHours()
  {
    int estimateH = 0;

    for (int i = 0; i < assignedTask.size(); i++)
    {
      estimateH += assignedTask.get(i).getEstimatedWorkHours();
    }

    return estimateH;
  }

  /**
   * method for getting the total worked hours on the tasks
   * @return number of total worked hours
   */
  public int getTotalWorkedHours()
  {
    int estimateT = 0;

    for (int i = 0; i < assignedTask.size(); i++)
    {
      estimateT += assignedTask.get(i).getTotalWorkedHours();
    }

    return estimateT;
  }

  /**
   * Getter for isApproved instance variable
   * @return isApproved
   */
  public boolean isApproved()
  {
    return isApproved;
  }

  /**
   * Method to get the number of assigned tasks
   * @return number of tasks assigned
   */
  public int getNumberOfAssignedTask()
  {
    return assignedTask.size();
  }

  /**
   * Getter fo assignedTask instance variable
   * @return an array list of tasks
   */
  public ArrayList<Task> getAllAssignedTasks()
  {
    return assignedTask;
  }

  /**
   * Getting all the member that are working on the requirement
   * @return an array list of all the members working
   */
  public ArrayList<Member> getAllMembersWorkingOnRequirement()
  {
    ArrayList<Member> members = new ArrayList<>();

    for (int i = 0; i < assignedTask.size(); i++)
    {
      members.addAll(assignedTask.get(i).getAllAssignedMembers());

    } return members;

  }

  /**
   * Method to assign a task to the assignedTask list
   * @param task
   * @return true, if the task was added successfully
   * @throws NoSuchFieldException triggers if the task already exists in  the assignedTask list
   */
  public boolean assignTask(Task task) throws NoSuchFieldException
  {
    for (int i = 0; i < assignedTask.size(); i++)
    {
      if (assignedTask.get(i).equals(task))
      {
        throw new NoSuchFieldException("Already exists in assignedTask");
      }
    }
    assignedTask.add(task);
    return true;
  }

  /**
   * Method to unassign a task from the assignedTask list
   * @param task
   * @return true, if the task was removed successfully
   * @throws NoSuchFieldException triggers if the task already exists in  the assignedTask list
   */
  public boolean unassignTask(Task task) throws NoSuchFieldException
  {
    for (int i = 0; i < assignedTask.size(); i++)
    {
      if (assignedTask.get(i).equals(task))
      {
        assignedTask.remove(task);
        return true;
      }
    }
    throw new NoSuchFieldException("No such task in the assignedTask");
  }

  /**
   *Method to clear the assignedTask list
   * @return true
   */
  public boolean unassignFromEveryTask()
  {

    for (int i = 0; i < assignedTask.size(); i++)
    {
      assignedTask.remove(i);
    }
    return true;
  }

  /**
   * Method to compare objects of type Requirement
   * @param obj
   * @return true in case the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Requirement))
    {
      return false;
    }
    Requirement other = (Requirement) obj;

    for (int i = 0; i < assignedTask.size(); i++)
    {
      if (!(assignedTask.get(i).getId()
          .equals(other.assignedTask.get(i).getId())))
      {
        return false;
      }
    }

    return id.equals(other.id) && title.equals(other.title) && description
        .equals(other.description) && status.equals(other.status)
        && priorityGroup.equals(other.priorityGroup)
        && isApproved == other.isApproved && deadline.equals(other.deadline);
  }
}

