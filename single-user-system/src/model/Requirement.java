package model;

import java.util.ArrayList;

public class Requirement
{
  private String ID;
  private String title;
  private String description;
  private String status;
  private String priorityGroup;
  private boolean isApproved;
  private MyDate deadline;
  private ArrayList<Task> assignedTask;

  public Requirement(String ID, String title, String description,MyDate deadline, String priorityGroup)
  {
     setTitle(title);
     setID(ID);
     setDescription(description);
     setDeadline(deadline);
     setPriorityGroup(priorityGroup);
     isApproved = false;
     assignedTask = new ArrayList<>();
  }
  public Requirement(String ID, String title,MyDate deadline, String priorityGroup)
  {
    setTitle(title);
    setID(ID);
    setDeadline(deadline);
    setPriorityGroup(priorityGroup);
    description = null;
    isApproved = false;
    assignedTask = new ArrayList<>();

  }

  public String getID()
  {
    return ID;
  }

  public void setID(String ID)
  {
    this.ID = ID;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getPriorityGroup()
  {
    return priorityGroup;
  }

  public void setPriorityGroup(String priorityGroup)
  {
    this.priorityGroup = priorityGroup;
  }

  public MyDate getDeadline()
  {
    return deadline;
  }

  public void setDeadline(MyDate deadline)
  {
    this.deadline = deadline;
  }

  public int getEstimatedWorkHours()
  {
    int estimateH = 0;

    for(int i = 0; i < assignedTask.size(); i++)
    {
      estimateH += assignedTask.get(i).getEstimatedWorkHours();
    }

    return estimateH;
  }

  public int getTotalWorkedHours()
  {
    int estimateT = 0;

    for(int i = 0; i < assignedTask.size(); i++)
    {
      estimateT += assignedTask.get(i).getTotalWorkedHours();
    }

    return estimateT;
  }

  public boolean isApproved()
  {
    return isApproved;
  }

  public int getNumberOfAssignedTask()
  {
    return assignedTask.size();
  }

  public ArrayList<Task> getAllAssignedTasks()
  {
    return assignedTask;
  }

//  public ArrayList<Member> getAllMembersWorkingOnRequirement()
//  {
//
//  }

  public boolean assignTask(Task task)
  {
    for (int i = 0; i < assignedTask.size(); i++)
    {
      if (assignedTask.get(i).equals(task))
      {
        return false;
      }
    }
    assignedTask.add(task);
    return true;
  }

  public boolean unassignTask(Task task)
  {
    for (int i = 0; i < assignedTask.size(); i++)
    {
      if (assignedTask.get(i).equals(task))
      {
        assignedTask.remove(task);
        return true;
      }
    }
   return false;
  }

   public boolean unassignFromEveryTask()
   {

     for (int i = 0; i < assignedTask.size(); i++)
     {
       assignedTask.remove(i);
     }
      return true;
   }

  public void approvedOrDisapprove(boolean isApproved)
  {
    this.isApproved = isApproved;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Requirement))
    {
      return false;
    }
    Requirement other = (Requirement)obj;

    for (int i = 0; i < assignedTask.size(); i++)
    {
      if (!(assignedTask.get(i).getID().equals(other.assignedTask.get(i).getID())))
      {
        return false;
      }
    }

    return ID.equals(other.ID) && title.equals(other.title) && description.equals(other.description)
        && status.equals(other.status) && priorityGroup.equals(other.priorityGroup) && isApproved == other.isApproved
        && deadline.equals(other.deadline);
  }
}

