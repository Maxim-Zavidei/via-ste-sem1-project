
import model.*;
import mediator.ProjectManagementPersistence;

public class Main {
  public static void main(String[] args) {

    // This code is for testing purposes can be safely deleted.
    ProjectManagementModelManager projectManager = new ProjectManagementModelManager();

    Project testProject1 = projectManager.addProject("Test Project 1", "This is a test project 1.", 18, 12, 2020);
    Requirement testR1 = testProject1.addRequirement("TestR1", "This is a test requirement 1 from project 1.", 17, 12, 2020, "High");
    Requirement testR2 = testProject1.addRequirement("TestR2", "This is a test requirement 2.", 17, 12, 2020, "High");
    Task testT1 = testProject1.addTask("Test Task", "This is a test task 1.", 20, 17, 12, 2020);
    Task testT2 = testProject1.addTask("Test Task", "This is a test task 2.", 22, 17, 12, 2020);

    testR1.assignTask(testT1);
    testR2.assignTask(testT1);

    Project testProject2 = projectManager.addProject("Test Project 2", "This is a test project 2.", 18, 12, 2020);
    Requirement testR1p2 = testProject2.addRequirement("TestR1", "This is a test requirement 1.", 17, 12, 2020, "High");
    Requirement testR2p2 = testProject2.addRequirement("TestR2", "This is a test requirement 2.", 17, 12, 2020, "High");
    Task testT1p2 = testProject2.addTask("Test Task", "This is a test task 1.", 20, 17, 12, 2020);
    Task testT2p2 = testProject2.addTask("Test Task", "This is a test task 2.", 22, 17, 12, 2020);

    testT1p2.assignRequirement(testR1p2);
    testT2p2.assignRequirement(testR2p2);

    Member member1 = projectManager.addMember("Member1FirstName", "Member1LastName", new MyDate(5, 10, 2020), "member1@gmail.com", 111111);
    Member member2 = projectManager.addMember("Member2FirstName", "Member2LastName", new MyDate(5, 10, 2020), "member2@gmail.com", 111111);

    testT1.assignMember(member1);
    testT2p2.assignMember(member2);

    testProject1.setScrumMaster(member2);
    testProject2.setProductOwner(member1);

    ProjectManagementPersistence.save(projectManager);

    //ProjectManagementModelManager projectManager = ProjectManagementPersistence.load();

    for (Project project : projectManager.getAllProjects()) {
      System.out.println(project.getTitle());
      System.out.println(project.getDescription());
      System.out.println(project.getDeadline());
      System.out.println(project.getAllMembers());
      System.out.println(project.getAllRequirements());
      System.out.println(project.getAllTasks());
      if (project.getScrumMaster() != null) System.out.println(project.getScrumMaster().getFullName());
      if (project.getProductOwner() != null) System.out.println(project.getProductOwner().getFullName());
      System.out.println(project.getRequirementByIndex(1).getAllAssignedTasks());
      System.out.println(project.getTaskByIndex(1).getAllAssignedRequirements());
    }
  }
}
