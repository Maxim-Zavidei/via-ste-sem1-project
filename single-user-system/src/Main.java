
import model.Task;
import model.Requirement;
import model.Project;
import model.ProjectList;
import mediator.ProjectManagementPersistence;

public class Main {
  public static void main(String[] args) {
    ProjectList projectList = new ProjectList();

    Project testProject1 = projectList.addProject("Test Project 1", "This is a test project 1.", 18, 12, 2020);
    Requirement testR1 = testProject1.addRequirement("TestR1", "This is a test requirement 1.", 17, 12, 2020, "High");
    Requirement testR2 = testProject1.addRequirement("TestR2", "This is a test requirement 2.", 17, 12, 2020, "High");
    Task testT1 = testProject1.addTask("Test Task", "This is a test task 1.", 20, 17, 12, 2020);
    Task testT2 = testProject1.addTask("Test Task", "This is a test task 2.", 22, 17, 12, 2020);

    testR1.assignTask(testT1);
    testR2.assignTask(testT1);

    Project testProject2 = projectList.addProject("Test Project 2", "This is a test project 2.", 18, 12, 2020);
    Requirement testR1p2 = testProject2.addRequirement("TestR1", "This is a test requirement 1.", 17, 12, 2020, "High");
    Requirement testR2p2 = testProject2.addRequirement("TestR2", "This is a test requirement 2.", 17, 12, 2020, "High");
    Task testT1p2 = testProject2.addTask("Test Task", "This is a test task 1.", 20, 17, 12, 2020);
    Task testT2p2 = testProject2.addTask("Test Task", "This is a test task 2.", 22, 17, 12, 2020);

    testT1p2.assignRequirement(testR1p2);
    testT2p2.assignRequirement(testR1p2);

    ProjectManagementPersistence.save(projectList);
  }
}
