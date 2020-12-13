package model;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

public class TaskList {

  ArrayList<Task> taskList;

  // ------------------------------ Constructors ------------------------------

  public TaskList() {
    taskList = new ArrayList<>();
  }

  // ------------------------------ Getters ------------------------------

  public int getNumberOfTasks() {
    return taskList.size();
  }

  public ArrayList<Task> getAllTasks() {
    return taskList;
  }

  public ArrayList<Task> getAllTasksBeforeDeadLine(MyDate deadline) {
    ArrayList<Task> toReturnTasks = new ArrayList<>();
    for (Task task : taskList) if (task.getDeadline().isBefore(deadline)) toReturnTasks.add(task);
    return toReturnTasks;
  }

  public ArrayList<Task> getAllTasksWithStatus(String status) {
    ArrayList<Task> toReturnTasks = new ArrayList<>();
    for (Task task : taskList) if (task.getStatus().equals(status)) toReturnTasks.add(task);
    return toReturnTasks;
  }

  public Task getTaskByIndex(int index) {
    if (index < 0) throw new IllegalArgumentException("Attempt to access array list with negative index.");
    return taskList.get(index);
  }

  public Task getTaskById(String id) {
    for (Task task : taskList) if (task.getId().equals(id)) {
        return task;
    }
    throw new NoSuchElementException("Could not find a task with given id.");
  }

  // ------------------------------ Other Methods ------------------------------

  /**
   * Checks whether a task with a given id already exists.
   * @param id Id to check for.
   * @return Whether a task matching the id already exists.
   */
  public boolean isIdTaken(String id) {
    for (Task task : taskList) if (task.getId().equals(id)) {
      return true;
    }
    return false;
  }

  /**
   * Generates a unique id that does not match any of the existing ids of all tasks.
   * @return The unique id.
   */
  private String generateId(String projectId) {
    Random randomizer = new Random();
    StringBuilder generatedId;
    char randomChar;

    do {
      generatedId = new StringBuilder(projectId + "T");
      for (int i = 0; i < 31; i++) {
        do randomChar = (char) (randomizer.nextInt(75) + 48); while (!(
            '0' <= randomChar && randomChar <= '9' ||
                'A' <= randomChar && randomChar <= 'Z' && randomChar != 'P' && randomChar != 'R' && randomChar != 'T' ||
                'a' <= randomChar && randomChar <= 'z'
        ));
        generatedId.append(randomChar);
      }
    } while (isIdTaken(generatedId.toString()));
    return generatedId.toString();
  }

  // To comment
  public void addTask(String projectId, String title, String description, float estimatedWorkHours, MyDate deadline) {
    taskList.add(new Task(generateId(projectId), title, description, estimatedWorkHours, deadline));
  }

  // To comment
  public void addTask(String projectId, String title, float estimatedWorkHours, MyDate deadline) {
    taskList.add(new Task(generateId(projectId), title, estimatedWorkHours, deadline));
  }

  // To comment
  public void removeTask(String id) {
    taskList.remove(getTaskById(id));
  }

  // To comment
  public void removeTask(Task task) {
    if (task == null) throw new IllegalArgumentException("Task argument is null.");
    removeTask(task.getId());
  }

  // To comment
  public float getTotalWorkedHours() {
    float totalWorkedHours = 0;
    for (Task task : taskList) totalWorkedHours += task.getTotalWorkedHours();
    return totalWorkedHours;
  }
}
