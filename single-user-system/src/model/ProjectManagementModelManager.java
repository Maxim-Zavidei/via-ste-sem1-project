package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectManagementModelManager implements Serializable, ProjectManagementModel {

  private ProjectList projectList;
  private MemberList memberList;

  // ------------------------------ Constructors ------------------------------

  public ProjectManagementModelManager() {
    projectList = new ProjectList();
    memberList = new MemberList();
  }

  // ------------------------------ Getters for Instance Variables ------------------------------

  public ProjectList getProjectList() {
    return projectList;
  }

  // ------------------------------ Getters for Projects ------------------------------

  public ArrayList<Project> getAllProjects() {
    return projectList.getAllProjects();
  }

  public ArrayList<Project> getProjectsWithStatusOver(float status) {
    return projectList.getAllProjectsWithStatusOver(status);
  }

  public ArrayList<Project> getProjectsBeforeDeadline(MyDate deadline) {
    return projectList.getAllProjectsBeforeDeadline(deadline);
  }

  public Project getProjectById(String id) {
    return projectList.getProjectById(id);
  }

  // ------------------------------ Getters for Requirements ------------------------------

  public ArrayList<Requirement> getAllRequirements() {
    return projectList.getAllRequirements();
  }

  public ArrayList<Requirement> getAllRequirementsWithPriority(String priorityGroup) {
    return projectList.getAllRequirementsWithPriority(priorityGroup);
  }

  public ArrayList<Requirement> getRequirementsLinkedTo(Project project) {
    return project.getAllRequirements();
  }

  public ArrayList<Requirement> getRequirementsLinkedTo(Task task) {
    return task.getAllAssignedRequirements();
  }

  public ArrayList<Requirement> getRequirementsLinkedTo(Member member) {
    ArrayList<Requirement> requirementsToReturn = new ArrayList<>();
    for (Task task : member.getAllAssignedTasks()) requirementsToReturn.addAll(task.getAllAssignedRequirements());
    return requirementsToReturn;
  }

  public ArrayList<Requirement> getRequirementsWithStatusOver(float status) {
    return projectList.getAllRequirementsWithStatusOver(status);
  }

  public ArrayList<Requirement> getRequirementsBeforeDeadline(MyDate deadline) {
    return projectList.getAllRequirementsBeforeDeadline(deadline);
  }

  public Requirement getRequirementById(String id) {
    return projectList.getRequirementById(id);
  }

  // ------------------------------ Getters for Tasks ------------------------------

  public ArrayList<Task> getAllTasks() {
    return projectList.getAllTasks();
  }

  public ArrayList<Task> getTasksLinkedTo(Project project) {
    return project.getAllTasks();
  }

  public ArrayList<Task> getTasksLinkedTo(Requirement requirement) {
    return requirement.getAllAssignedTasks();
  }

  public ArrayList<Task> getTasksLinkedTo(Member member) {
    return member.getAllAssignedTasks();
  }

  public ArrayList<Task> getTasksByStatus(String status) {
    return projectList.getAllTasksWithStatus(status);
  }

  public ArrayList<Task> getTasksBeforeDeadline(MyDate deadline) {
    return projectList.getAllTasksBeforeDeadline(deadline);
  }

  public Task getTaskById(String id) {
    return projectList.getTaskById(id);
  }

  // ------------------------------ Getters for Members ------------------------------

  public ArrayList<Member> getAllMembers() {
    return memberList.getAllMembers();
  }

  public ArrayList<Member> getMembersLinkedTo(Project project) {
    ArrayList<Member> membersToReturn = new ArrayList<>();
    for (Task task : project.getAllTasks()) membersToReturn.addAll(task.getAllAssignedMembers());
    if (project.getProductOwner() != null) membersToReturn.add(project.getProductOwner());
    if(project.getProjectCreator() != null) membersToReturn.add(project.getProjectCreator());
    if(project.getScrumMaster() != null) membersToReturn.add(project.getScrumMaster());
    return membersToReturn;
  }

  public ArrayList<Member> getMembersLinkedTo(Requirement requirement) {
    return requirement.getAllMembersWorkingOnRequirement();
  }

  public ArrayList<Member> getMembersLinkedTo(Task task) {
    return task.getAllAssignedMembers();
  }

  public Member getMemberByFullName(String firstName, String lastName) {
    return memberList.getMemberByFullName(firstName,lastName);
  }

  public Member getMemberByEmail(String email) {
    return memberList.getMemberByEmail(email);
  }

  // ------------------------------ Other Methods ------------------------------

  public Project addProject(String title, String description, int day, int month, int year) {
    return projectList.addProject(title, description, day, month, year);
  }

  public Project addProject(String title, int day, int month, int year) {
    return projectList.addProject(title, day, month, year);
  }

  public void removeProject(Project project) {
    projectList.removeProject(project);
  }

  public void removeProject(String Id) {
    projectList.removeProject(Id);
  }

  public Member addMember(String firstName, String lastName, MyDate birthday, String email) {
    return memberList.hireMember(firstName, lastName, birthday, email);
  }

  public Member addMember(String firstName, String lastName, MyDate birthday,String email, long telephoneNumber) {
    return memberList.hireMember(firstName, lastName, birthday, email, telephoneNumber);
  }

  public void removeMember(String firstName, String lastName) {
    memberList.fireMember(firstName, lastName);
  }
}