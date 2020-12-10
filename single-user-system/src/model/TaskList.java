package model;

import java.util.ArrayList;

public class TaskList
{
    ArrayList<Task> taskList;

    public TaskList()
    {
        taskList = new ArrayList<Task>();
    }

    public int getNumberOfTasks()
    {
        return taskList.size();
    }

    public ArrayList<Task> getTaskBeforeDeadLine(MyDate date){
        ArrayList<Task> listReturn = new ArrayList<>();

        for(Task temp : taskList){
            if(temp.getDeadline().isBefore(date)){
                listReturn.add(temp);
            }
        }
        return listReturn;
    }

    public ArrayList<Task> getAllTasksWithFGivenStatus(String status)
    {
        ArrayList<Task> returnList = new ArrayList<>();
        for(Task task : taskList)
        {
            if(task.getStatus().equals(status)){
                returnList.add(task);
            }
        }
        return returnList;
    }

    public Task getTaskByIndex(int ind)
    {
        return taskList.get(ind);
    }

    public Task getTaskById(String id){
        for(Task task : taskList)
        {
            if(task.getId().equals(id))
            {
                return task;
            }
        }
        return null;
    }

    public boolean addTask(Task task){
        for(Task temp : taskList){
            if(task.equals(temp))
            {
                return false;
            }
        }
        taskList.add(task);

        return true;
    }

    public boolean removeTask(String id){
        for(Task task: taskList)
        {
            if(task.getId().equals(id))
            {
                taskList.remove(task);
                return true;
            }
        }
        return false;
    }

    public boolean removeTask(Task task)
    {
        for(Task temp : taskList)
        {
            if(temp.equals(task))
            {
                taskList.remove(task);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Task> getAllTask()
    {
        return taskList;
    }

}
