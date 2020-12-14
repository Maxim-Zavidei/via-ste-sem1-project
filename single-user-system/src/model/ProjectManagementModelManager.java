package model;

import java.util.ArrayList;

public class ProjectManagementModelManager implements ProjectManagementModel
{
  private ProjectList projectList;
  private MemberList memberList;

  public ProjectManagementModelManager()
  {
    projectList = new ProjectList();
    memberList = new MemberList();
  }
  public ArrayList<Project> getAllProjects()
  {
     return projectList.getAllProjects();
  }

  public ArrayList<Project> getProjectsWithStatusOver(float status)
  {
   return   projectList.getAllProjectsWithStatusOver(status);
  }

  public ArrayList<Project> getProjectsBeforeDeadline(MyDate deadline)
  {
    return projectList.getAllProjectsBeforeDeadline(deadline);
  }

  public Project getProjectById(String Id)
  {
    return projectList.getProjectById(Id);
  }

  public ArrayList<Requirement> getAllRequirements()
  {
    return projectList.getAllRequirements();
  }

  public ArrayList<Requirement> getAllRequirementsWithPriority(String priorityGroup)
  {
    return projectList.getAllRequirementsWithPriority(priorityGroup);
  }

  public ArrayList<Requirement> getRequirementsLinkedTo(Project project)
  {
    return project.getAllRequirements();
  }

  public ArrayList<Requirement> getRequirementsLinkedTo(Task task)
  {
    return task.getAllAssignedRequirements();
  }
  public ArrayList<Requirement> getRequirementsLinkedTo(Member member)
  {
    ArrayList<Requirement> requirements = new ArrayList<>();

    for (Task task : member.getAllAssignedTasks()) requirements.addAll(task.getAllAssignedRequirements());

    return requirements;
  }

  public ArrayList<Requirement> getRequirementsWithStatusOver(float status)
  {
    return projectList.getAllRequirementsWithStatusOver(status);
  }

  public ArrayList<Requirement> getRequirementsBeforeDeadline(MyDate deadline)
  {
    return projectList.getAllRequirementsBeforeDeadline(deadline);
  }

  public Requirement getRequirementById(String Id)
  {
    return projectList.getRequirementById(Id);
  }

  public ArrayList<Task> getAllTasks()
  {
    return projectList.getAllTasks();
  }

  public ArrayList<Task> getTasksLinkedTo(Project project)
  {
    return project.getAllTasks();
  }

  public ArrayList<Task> getTasksLinkedTo(Requirement requirement)
  {
    return requirement.getAllAssignedTasks();
  }

  public ArrayList<Task> getTasksLinkedTo(Member member)
  {
    return member.getAllAssignedTasks();
  }

  public ArrayList<Task> getTasksByStatus(String status)
  {
    return projectList.getAllTasksWithStatus(status);
  }

  public ArrayList<Task> getTasksBeforeDeadline(MyDate deadline)
  {
    return projectList.getAllTasksBeforeDeadline(deadline);
  }

  public Task getTaskById(String Id)
  {
    return projectList.getTaskById(Id);
  }

  public ArrayList<Member> getAllMembers()
  {
    return memberList.getAllMembers();
  }

  public ArrayList<Member> getMembersLinkedTo(Project project)
  {
    ArrayList<Member> members = new ArrayList<>();
    for (Task task : project.getAllTasks()) members.addAll(task.getAllAssignedMembers());
    if (project.getProductOwner() != null)
    {
      members.add(project.getProductOwner());

    }
    if(project.getProjectCreator() != null)
    {
      members.add(project.getProjectCreator());

    }
    if(project.getScrumMaster() != null)
    {
      members.add(project.getScrumMaster());

    }
    return members;
  }

  public ArrayList<Member> getMembersLinkedTo(Requirement requirement)
  {
    return requirement.getAllMembersWorkingOnRequirement();
  }

  public ArrayList<Member> getMembersLinkedTo(Task task)
  {
    return task.getAllAssignedMembers();
  }

  public Member getMemberByFullName(String firstName, String lastName)
  {
    return memberList.getMemberByFullName(firstName,lastName);
  }

  public Member getMemberByEmail(String email)
  {
    return memberList.getMemberByEmail(email);
  }

  public void createProject(String title, String description, int day, int month, int year)
  {
    projectList.addProject(title, description, day, month, year);
  }
  public void createProject(String title, int day, int month, int year)
  {
    projectList.addProject(title, day, month, year);
  }
  public void removeProject(Project project)
  {
    projectList.removeProject(project);
  }
  public void removeProject(String Id)
  {
    projectList.removeProject(Id);
  }
  public void addMember(String firstName, String lastName, MyDate birthday, String email)
  {
    memberList.hireMember(firstName, lastName, birthday, email);
  }
  public void addMember(String firstName, String lastName, MyDate birthday,String email, long telephoneNumber)
  {
    memberList.hireMember(firstName, lastName, birthday, email, telephoneNumber);
  }
  public void removeMember(String firstName, String lastName)
  {
    memberList.fireMember(firstName, lastName);
  }


























}
