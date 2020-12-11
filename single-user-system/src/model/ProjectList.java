package model;


import javax.crypto.NullCipher;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProjectList {

    ArrayList<Project> projectList;

    public ProjectList()
    {
        projectList = new ArrayList<Project>();
    }

    public int getNumberOfProjects()
    {
        return projectList.size();
    }

    public int getNumberOfTask()
    {
        int taskNum = 0;
        for(Project temp : projectList)
        {
            taskNum += temp.getNumberOfTasks();
        }
        return taskNum;
    }

    public Project getProjectById(String id)
    {
        for(Project project : projectList)
        {
            if(project.getId()==id)
            {
                return project;
            }
        }
        throw new NoSuchElementException("Element was not found");
    }

    public Project getProjectByIndex(int ind)
    {
        return projectList.get(ind);
    }

    public ArrayList<Project> getAllProjects()
    {
        return projectList;
    }

    public ArrayList<Task> getAllTask()
    {
        ArrayList<Task> temp  = new ArrayList<>();

        for(Project project: projectList)
        {
            for(Task task : project.getAllTasks())
            {
                temp.add(task);
            }
        }
        return temp;
    }

    public ArrayList<Requirement> getAllRequirements()
    {
        ArrayList<Requirement> requirements = new ArrayList<>();

        for(Project project: projectList)
        {
            for(Requirement requirement : project.getAllRequirements())
            {
                requirements.add(requirement);
            }
        }
        return requirements;
    }

    public ArrayList<Project> getProjectsByStatus(String status)
    {
        ArrayList<Project> temp = new ArrayList<>();
        for(Project project : projectList)
        {
            if(project.getStatus().equals(status))
            {
                temp.add(project);
            }
        }
        return temp;
    }

    public ArrayList<Project> getProjectsBeforeDeadline(MyDate deadline)
    {
        ArrayList<Project> temp = new ArrayList<>();

        for(Project project : projectList)
        {
            if(project.getDate().isBefore(deadline))
            {
                temp.add(project);
            }
        }
        return temp;
    }

    public ArrayList<Requirement> getRequirementsByStatus(String status)
    {
        ArrayList<Requirement> temp = new ArrayList<>();

        for(Requirement requirement : getAllRequirements())
        {
            if(requirement.getStatus().equals(status))
            {
                temp.add(requirement);
            }
        }
        return temp;
    }

    public ArrayList<Requirement> getRequirementsBeforeDeadline(MyDate deadline)
    {
        ArrayList<Requirement> temp = new ArrayList<>();

        for(Requirement requirement : getAllRequirements())
        {
            //Same than in the previous method
            if(requirement.getDeadline().equals(deadline)){
                temp.add(requirement);
            }
        }
        return temp;
    }

    //these type of methods should throw a nullpointer exception

    public ArrayList<Requirement> getRequirementsByPriority(String priority)
    {
        ArrayList<Requirement> temp = new ArrayList<>();

        for(Requirement  requirement: getAllRequirements())
        {
            //Yet again same thing than in the previous 2 methods
            if(requirement.getPriority().equals(priority));
            {
                temp.add(requirement);
            }
        }
        return temp;
    }

    public ArrayList<Task> getTasksBeforeDeadline(MyDate deadline)
    {
        ArrayList<Task> temp = new ArrayList<>();

        for(Task task : getAllTask())
        {
            //Same than in the previous method
            if(task.getDeadline().equals(deadline))
            {
                temp.add(task);
            }
        }
        return temp;
    }

    public ArrayList<Task> getTasksByStatus(String status)
    {
        ArrayList<Task> temp = new ArrayList<>();

        for(Task task : getAllTask())
        {
            //If it doesnt work well put an if statement checking if the Arraylist
            // of requirements with a given status is empty or not and the
            // run the code
            if(task.getStatus().equals(status))
            {
                temp.add(task);
            }

        }
        return temp;
    }
    //We might need to include null pointer exceptions for these type of getters that might return a null object
    public Requirement getRequirementById(String id)
    {
        for(Requirement requirement : getAllRequirements())
        {
            if(requirement.getId().equals(id))
            {
                return requirement;
            }
        }
        throw new NoSuchElementException("The element was not found");
    }
    public Task getTaskById(String id)
    {
        for(Task task : getAllTask())
        {
            if(task.getId().equals(id))
            {
                return task;
            }
        }
        throw  new NoSuchElementException("The element was not found");


    }











}