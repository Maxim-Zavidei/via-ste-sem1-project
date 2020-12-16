package mediator;

import model.*;
import java.io.*;

public class ProjectManagementPersistence {

  public static void save(ProjectManagementModelManager projectManager) {

    // Saves the project manager to a bin file.
    try {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("single-user-system/output/projects.bin"));
      out.writeObject(projectManager);
      out.close();
    } catch (IOException e) {
      throw new UnsupportedOperationException("Could not save the projects to the bin file. Make sure single-user-system/output/ path exists.");
    }

    ProjectList projectList = projectManager.getProjectList();

    // Saves all the project list to a xml file.
    try (PrintWriter out = new PrintWriter("single-user-system/output/projects.xml")) {

      out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
      out.println("<projectlist>");

      for (Project project : projectList.getAllProjects()) {
        out.println("  <project>");

        out.println("    <id>" + project.getId() + "</id>");
        out.println("    <title>" + project.getTitle() + "</title>");
        out.println("    <description>" + project.getDescription() + "</description>");
        out.println("    <deadline>" + project.getDeadline() + "</deadline>");

        if (project.getProjectCreator() != null) {
          out.println("    <projectcreator>");
          out.println("      <firstname>" + project.getProjectCreator().getFirstName() + "</firstname>");
          out.println("      <lastname>" + project.getProjectCreator().getLastName() + "</lastname>");
          out.println("      <email>" + project.getProjectCreator().getEmail() + "</email>");
          out.println("      <telephonenumber>" + project.getProjectCreator().getTelephoneNumber() + "</telephonenumber>");
          out.println("      <birthday>" + project.getProjectCreator().getBirthday() + "</birthday>");
          out.println("    </projectcreator>");
        }
        if (project.getProductOwner() != null) {
          out.println("    <productowner>");
          out.println("      <firstname>" + project.getProductOwner().getFirstName() + "</firstname>");
          out.println("      <lastname>" + project.getProductOwner().getLastName() + "</lastname>");
          out.println("      <email>" + project.getProductOwner().getEmail() + "</email>");
          out.println("      <telephonenumber>" + project.getProductOwner().getTelephoneNumber() + "</telephonenumber>");
          out.println("      <birthday>" + project.getProductOwner().getBirthday() + "</birthday>");
          out.println("    </productowner>");
        }
        if (project.getScrumMaster() != null) {
          out.println("    <scrummaster>");
          out.println("      <firstname>" + project.getScrumMaster().getFirstName() + "</firstname>");
          out.println("      <lastname>" + project.getScrumMaster().getLastName() + "</lastname>");
          out.println("      <email>" + project.getScrumMaster().getEmail() + "</email>");
          out.println("      <telephonenumber>" + project.getScrumMaster().getTelephoneNumber() + "</telephonenumber>");
          out.println("      <birthday>" + project.getScrumMaster().getBirthday() + "</birthday>");
          out.println("    </scrummaster>");
        }

        out.println("    <memberlist>");
        for (Member member : project.getAllMembers()) {
          out.println("      <member>");
          out.println("        <firstname>" + member.getFirstName() + "</firstname>");
          out.println("        <lastname>" + member.getLastName() + "</lastname>");
          out.println("        <email>" + member.getEmail() + "</email>");
          out.println("        <telephonenumber>" + member.getTelephoneNumber() + "</telephonenumber>");
          out.println("        <birthday>" + member.getBirthday() + "</birthday>");
          out.println("      </member>");
        }
        out.println("    </memberlist>");

        out.println("    <requirementlist>");

        for (Requirement requirement : project.getAllRequirements()) {
          out.println("      <requirement>");

          out.println("        <id>" + requirement.getId() + "</id>");
          out.println("        <title>" + requirement.getTitle() + "</title>");
          out.println("        <description>" + requirement.getDescription() + "</description>");
          out.println("        <deadline>" + requirement.getDeadline() + "</deadline>");
          out.println("        <prioritygroup>" + requirement.getPriorityGroup() + "</prioritygroup>");
          out.println("        <isapproved>" + requirement.isApproved() + "</isapproved>");

          out.println("        <assignedtasks>");
          for (Task task : requirement.getAllAssignedTasks()) {
            out.println("          <task>");
            out.println("            <id>" + task.getId() + "</id>");
            out.println("            <title>" + task.getTitle() + "</title>");
            out.println("            <description>" + task.getDescription() + "</description>");
            out.println("            <deadline>" + task.getDeadline() + "</deadline>");
            out.println("            <status>" + task.getStatus() + "</status>");
            out.println("            <estimatedworkhours>" + task.getEstimatedWorkHours() + "</estimatedworkhours>");
            out.println("            <totalworkedhours>" + task.getTotalWorkedHours() + "</totalworkedhours>");
            out.println("          </task>");
          }
          out.println("        </assignedtasks>");

          out.println("      </requirement>");
        }

        out.println("    </requirementlist>");
        out.println("    <tasklist>");

        for (Task task : project.getAllTasks()) {
          out.println("      <task>");
          out.println("        <id>" + task.getId() + "</id>");
          out.println("        <title>" + task.getTitle() + "</title>");
          out.println("        <description>" + task.getDescription() + "</description>");
          out.println("        <deadline>" + task.getDeadline() + "</deadline>");
          out.println("        <status>" + task.getStatus() + "</status>");
          out.println("        <estimatedworkhours>" + task.getEstimatedWorkHours() + "</estimatedworkhours>");
          out.println("        <totalworkedhours>" + task.getTotalWorkedHours() + "</totalworkedhours>");
          out.println("        <assignedrequirements>");
          for (Requirement requirement : task.getAllAssignedRequirements()) {
            out.println("          <requirement>");
            out.println("            <id>" + requirement.getId() + "</id>");
            out.println("            <title>" + requirement.getTitle() + "</title>");
            out.println("            <description>" + requirement.getDescription() + "</description>");
            out.println("            <deadline>" + requirement.getDeadline() + "</deadline>");
            out.println("            <prioritygroup>" + requirement.getPriorityGroup() + "</prioritygroup>");
            out.println("            <isapproved>" + requirement.isApproved() + "</isapproved>");
            out.println("          </requirement>");
          }
          out.println("        </assignedrequirements>");
          out.println("        <assignedmembers>");
          for (Member member : task.getAllAssignedMembers()) {
            out.println("          <member>");
            out.println("            <firstname>" + member.getFirstName() + "</firstname>");
            out.println("            <lastname>" + member.getLastName() + "</lastname>");
            out.println("            <email>" + member.getEmail() + "</email>");
            out.println("            <telephonenumber>" + member.getTelephoneNumber() + "</telephonenumber>");
            out.println("            <birthday>" + member.getBirthday() + "</birthday>");
            out.println("          </member>");
          }
          out.println("        </assignedmembers>");
          out.println("      </task>");
        }
        out.println("    </tasklist>");

        out.println("  </project>");
      }

      out.println("</projectlist>");
    } catch (FileNotFoundException e) {
      throw new UnsupportedOperationException("Could not save the projects to the xml file. Make sure single-user-system/output/ path exists.");
    }
  }

  public static ProjectManagementModelManager load() {
    // Loads the project maanger from the bin file.
    ProjectManagementModelManager toReturn;
    try {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream("single-user-system/output/projects.bin"));
      toReturn = (ProjectManagementModelManager) in.readObject();
      in.close();
    } catch (IOException | ClassNotFoundException e) {
      throw new UnsupportedOperationException("Could not read the projects from the bin file. Make sure single-user-system/output/projects.bin file exists.");
    }
    return toReturn == null ? new ProjectManagementModelManager() : toReturn;
  }
}