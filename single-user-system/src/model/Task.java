package model;

import java.util.ArrayList;

public class Task {

  private String id;
  private String title;
  private String description;
  private String status;
  private int estimatedWorkHours;
  private int totalWorkedHours;
  private MyDate deadline;
  private ArrayList<Requirement> requirementList;
  private ArrayList<Member> memberList;

  public Task(String id, String title, String description, MyDate deadline) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.deadline = deadline;
    requirementList = new ArrayList<Requirement>();
    memberList = new ArrayList<Member>();
  }

  public Task(String id, String title, MyDate deadline) {
    this.id = id;
    this.title = title;
    this.deadline = deadline;
    requirementList = new ArrayList<Requirement>();
    memberList = new ArrayList<Member>();
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setEstimatedWorkHours(int estimatedWorkHours) {
    this.estimatedWorkHours = estimatedWorkHours;
  }

  public void setTotalWorkedHours(int totalWorkedHours) {
    this.totalWorkedHours = totalWorkedHours;
  }

  public void setDeadline(MyDate deadline) {
    this.deadline = deadline;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getStatus() {
    return status;
  }

  public int getEstimatedWorkHours() {
    return estimatedWorkHours;
  }

  public int getTotalWorkedHours() {
    return totalWorkedHours;
  }

  public MyDate getDeadline() {
    return deadline;
  }

  public int getNumberOfRequirements()
  {
    return requirementList.size();
  }

  public ArrayList<Requirement> getRequirements()
  {
    return requirementList;
  }

  public boolean assignRequirement(Requirement requirement)
      throws NullPointerException
  {
    for (Requirement requirementTemp : requirementList)
    {
      if (requirementTemp.equals(requirement))
      {
        return false;
      }
    }
    requirementList.add(requirement);
    return true;
  }

  public boolean unAssignRequirement(Requirement requirement)
      throws NullPointerException
  {
    for (Requirement requirement1 : requirement)
    {
      if (requirement1.equals(requirement))
      {
        requirementList.remove(requirement);
        return true;
      }
    }
    return false;
  }

  public boolean unAssignFromEveryReq()
  {

    if (requirementList.isEmpty())
    {
      return false;
    }
    requirementList.clear();
    return true;
  }

  public int getNumberOfMembers()
  {
    return memberList.size();
  }

  public ArrayList<Member> getMemberList() {
    return memberList;
  }

  public boolean assignMember(Member member) throws NullPointerException
  {

    for (Member memberTemp : memberList)
    {
      if (memberTemp.equals(member))
      {
        return false;
      }
    }
    memberList.add(member);
    return true;
  }

  public boolean unAssignMember(Member member)
  {
    for (Member memberTemp : memberList)
    {
      if (memberTemp.equals(member))
      {
        memberList.remove(member);
        return true;
      }
    }
    return false;
  }

  public boolean unAssignEveryMember()
  {
    if (memberList.isEmpty())
    {
      return false;
    }

    memberList.clear();
    return true;
  }

  public void addWorkedTime(int hrs)
  {
    totalWorkedHours += hrs;
  }

  public boolean equals(Object obj)
  {

    if (!(obj instanceof Task))
    {
      return false;
    }

    Task other = (Task) obj;
    return other.id.equals(id);
  }
}
