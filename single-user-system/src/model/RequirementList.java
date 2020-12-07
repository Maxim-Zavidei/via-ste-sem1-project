package model;

import java.util.ArrayList;

public class RequirementList
{
  private ArrayList<Requirement> requirementList;

  public RequirementList()
  {
    requirementList = new ArrayList<>();
  }

  public int getNumberOfRequirements()
  {
    return requirementList.size();
  }

  public ArrayList<Requirement> getAllRequirements()
  {
    return requirementList;
  }

  public ArrayList<Requirement> getAllRequirementsBeforeDeadline(
      MyDate deadline)
  {
    ArrayList<Requirement> requirements = new ArrayList<>();

    for (int i = 0; i < requirementList.size(); i++)
    {
      if (requirementList.get(i).getDeadline().isBefore(deadline))
      {
        requirements.add(requirementList.get(i));
      }
    }

    return requirements;
  }

  public ArrayList<Requirement> getAllDisapprovedRequirements()
  {
    ArrayList<Requirement> requirements = new ArrayList<>();

    for (int i = 0; i < requirementList.size(); i++)
    {
      if (!requirementList.get(i).isApproved())
      {
        requirements.add(requirementList.get(i));
      }

    }
    return requirements;
  }

  public ArrayList<Requirement> getAllApprovedRequirements()
  {
    ArrayList<Requirement> requirements = new ArrayList<>();

    for (int i = 0; i < requirementList.size(); i++)
    {
      if (requirementList.get(i).isApproved())
      {
        requirements.add(requirementList.get(i));
      }

    }
    return requirements;
  }

  public ArrayList<Requirement> getAllRequirementsWithStatus(String status)
  {
    ArrayList<Requirement> requirements = new ArrayList<>();

    for (int i = 0; i < requirementList.size(); i++)
    {
      if (requirementList.get(i).getStatus().equals(status))
      {
        requirements.add(requirementList.get(i));
      }

    }
    return requirements;

  }

  public ArrayList<Requirement> getAllRequirementsByPriority(String priority)
  {
    ArrayList<Requirement> requirements = new ArrayList<>();

    for (int i = 0; i < requirementList.size(); i++)
    {
      if (requirementList.get(i).getPriorityGroup().equals(priority))
      {
        requirements.add(requirementList.get(i));
      }

    }
    return requirements;

  }

  public Requirement getRequirementByIndex(int index)
  {
    return requirementList.get(index);
  }

//should figure it out
  public Requirement getRequirementByID(String ID)
  {
    for (int i = 0; i < requirementList.size(); i++)
    {
      if (requirementList.get(i).getID().equals(ID))
      {
        return requirementList.get(i);
      }

    }

  }

//  public boolean addRequirement(String ID, String title, String description, MyDate deadline, String priorityGroup)
//  {
//
//  }

//  public boolean addRequirement(String ID, String title, MyDate deadline, String priorityGroup)
//   {
//
//    }

//  public boolean  removeRequirement(String ID)
//  {
//  }

//  public boolean  removeRequirement(Requirement requirement)
//  {
//
//  }

public boolean equals(Object obj)
{
  if (!(obj instanceof RequirementList))
  {
    return false;
  }
  RequirementList other = (RequirementList)obj;

  for (int i = 0; i < requirementList.size(); i++)
  {
    if (!requirementList.get(i).equals(other.requirementList.get(i)))
    {
      return false;
    }
  }
  return true;

}

}
