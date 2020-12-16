package model;

import java.util.ArrayList;

public interface ProjectManagementModel {

  // ------------------------------ Getters for Projects ------------------------------
  ArrayList<Project> getAllProjects();
  ArrayList<Project> getProjectsWithStatusOver(float status);
  ArrayList<Project> getProjectsBeforeDeadline(MyDate deadline);
  Project getProjectById(String id);

  // ------------------------------ Getters for Requirements ------------------------------
  ArrayList<Requirement> getAllRequirements();
  ArrayList<Requirement> getAllRequirementsWithPriority(String priorityGroup);
  ArrayList<Requirement> getRequirementsLinkedTo(Project project);
  ArrayList<Requirement> getRequirementsLinkedTo(Task task);
  ArrayList<Requirement> getRequirementsLinkedTo(Member member);
  ArrayList<Requirement> getRequirementsWithStatusOver(float status);
  ArrayList<Requirement> getRequirementsBeforeDeadline(MyDate deadline);
  Requirement getRequirementById(String id);

  // ------------------------------ Getters for Tasks ------------------------------
  ArrayList<Task> getAllTasks();
  ArrayList<Task> getTasksLinkedTo(Project project);
  ArrayList<Task> getTasksLinkedTo(Requirement requirement);
  ArrayList<Task> getTasksLinkedTo(Member member);
  ArrayList<Task> getTasksByStatus(String status);
  ArrayList<Task> getTasksBeforeDeadline(MyDate deadline);
  Task getTaskById(String id);

  // ------------------------------ Getters for Members ------------------------------
  ArrayList<Member> getAllMembers();
  ArrayList<Member> getMembersLinkedTo(Project project);
  ArrayList<Member> getMembersLinkedTo(Requirement requirement);
  ArrayList<Member> getMembersLinkedTo(Task task);
  Member getMemberByFullName(String firstName, String lastName);
  Member getMemberByEmail(String email);

  // ------------------------------ Other Methods ------------------------------
  Project addProject(String title, String description, int day, int month, int year);
  Project addProject(String title, int day, int month, int year);
  void removeProject(Project project);
  void removeProject(String id);
  Member addMember(String firstName, String lastName, MyDate birthday,String email);
  Member addMember(String firstName, String lastName, MyDate birthday,String email, long telephoneNumber);
  void removeMember(String firstName, String lastName);
}