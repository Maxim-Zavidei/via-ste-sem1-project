package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A class to process and store a number of requirements
 */
public class RequirementList {

  private ArrayList<Requirement> requirementList;

  /**
   * Constructor to initialise the instance variables.
   */
  public RequirementList() {
    requirementList = new ArrayList<>();
  }

  /**
   * Getter for the number of requirements
   * @return number of requirements
   */
  public int getNumberOfRequirements()
  {
    return requirementList.size();
  }

  /**
   * Getter for requirementList
   * @return requirementList
   */
  public ArrayList<Requirement> getAllRequirements()
  {
    return requirementList;
  }

  /**
   * Getting all the requirements before a specific deadline
   * @param deadline
   * @return requirements that are before the deadline
   */
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

  /**
   * Getting the requirements that are not approved
   * @return requirements that are not approved
   */
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
  /**
   * Getting the requirements that are approved
   * @return requirements that are approved
   */
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

  /**
   * Getting the requirements with a specific status
   * @param status
   * @return all requirements with that status
   */
  public ArrayList<Requirement> getAllRequirementsWithStatusOver(float status)
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

  /**
   * Getting the requirements with a specific priority
   * @param priority
   * @return all requirements with that priority
   */
  public ArrayList<Requirement> getAllRequirementsWithPriority(String priority)
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

  /**
   * Get the requirement by index
   * @param index
   * @return the requirement at that index
   * @throws IndexOutOfBoundsException may trigger if the requirementList does not have that index;
   */
  public Requirement getRequirementByIndex(int index) throws IndexOutOfBoundsException
  {
    return requirementList.get(index);
  }

  /**
   * Getting the requirements by an id
   * @param id
   * @return the requirements with that id
   * @throws NoSuchElementException triggers if there are no requirements by that id
   */
  public Requirement getRequirementById(String id)
  {
    for (int i = 0; i < requirementList.size(); i++)
    {
      if (requirementList.get(i).getId().equals(id))
      {
        return requirementList.get(i);
      }

    }
     throw new NoSuchElementException("No requirement by this id");
  }

  /**
   * Adding a new requirement to the requirementList, by accepting the same parameters as a requirement constructor
   * @param ID
   * @param title
   * @param description
   * @param deadline
   * @param priorityGroup
   * @return true if the requirement was added, false if it is already in the requirementList
   */
  public boolean addRequirement(String ID, String title, String description, MyDate deadline, String priorityGroup)
  {
    Requirement requirement = new Requirement(ID, title, description, deadline, priorityGroup);

      if (!requirementList.contains(requirement))
      {
        requirementList.add(requirement);
        return true;
      }

     return false;
  }
  /**
   * Adding a new requirement to the requirementList, by accepting the same parameters as a requirement constructor,
   * without the description parameter only
   * @param ID
   * @param title
   * @param deadline
   * @param priorityGroup
   * @return true if the requirement was added, false if it is already in the requirementList
   */
  public boolean addRequirement(String ID, String title, MyDate deadline, String priorityGroup)
   {
     Requirement requirement = new Requirement(ID, title, deadline, priorityGroup);


       if (!requirementList.contains(requirement))
       {
         requirementList.add(requirement);
         return true;
       }

     return false;

    }

  /**
   * Adding a new requirement with to the requirementList
   * @param requirement
   * @return true if the requirement was added, false if it is already in the requirementList
   */
  public boolean addRequirement(Requirement requirement)
  {

      if (!requirementList.contains(requirement))
      {
        requirementList.add(requirement);
        return true;
      }

    return false;

  }

  /**
   * Remove a requirement from the requirementList by id
   * @param id
   * @return true if the requirement was removed,  false if there was no such requirement to remove in the first place
   */
  public boolean  removeRequirement(String id)
  {
    for (int i = 0; i < requirementList.size(); i++)
    {
      if (requirementList.get(i).getId().equals(id))
      {
        requirementList.remove(i);
        return true;
      }
    }
    return false;
  }

  /**
   * Remove a requirement from the requirementList
   * @param requirement
   * @return true if the requirement was removed,  false if there was no such requirement to remove in the first place
   */
  public boolean  removeRequirement(Requirement requirement)
  {
    for (int i = 0; i < requirementList.size(); i++)
    {
      if (requirementList.contains(requirement))
      {
        requirementList.remove(requirement);
        return true;
      }
    }
    return false;
  }

  /**
   * Compares 2 objects of type if they are equal
   * @param obj
   * @return true if equal, false otherwise
   */
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
