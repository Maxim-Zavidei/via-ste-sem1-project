package model;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.io.Serializable;

/**
 * A class to store and process task objects.
 */
public class TaskList implements Serializable {

  ArrayList<Task> taskList;

  // ------------------------------ Constructors ------------------------------

  /**
   * Constructor to initialise all instance variables.
   */
  public TaskList() {
    taskList = new ArrayList<>();
  }

  // ------------------------------ Getters ------------------------------

  /**
   * Getter for the number of tasks linked to the project.
   * @return The number of tasks linked to the project.
   */
  public int getNumberOfTasks() {
    return taskList.size();
  }

  /**
   * Getter for all the tasks linked to the project.
   * @return All the tasks linked to the project or empty array list if no tasks are linked.
   */
  public ArrayList<Task> getAllTasks() {
    return taskList;
  }

  /**
   * Getter for all the tasks linked to the project before a specific deadline.
   * @param deadline A date that will be compare with each deadline of the linked task.
   * @return All linked tasks that have a deadline before the given argument date or empty array list if no matching tasks were found or no tasks are linked.
   */
  public ArrayList<Task> getAllTasksBeforeDeadLine(MyDate deadline) {
    ArrayList<Task> toReturnTasks = new ArrayList<>();
    for (Task task : taskList) if (task.getDeadline().isBefore(deadline)) toReturnTasks.add(task);
    return toReturnTasks;
  }

  /**
   * Getter for all the tasks linked to the project that have a matching status.
   * @param status A value of either ["Started", "Finished"] that will be compared with each status of the linked tasks.
   * @return All linked tasks that have a status matching the argument or empty array list if no matching tasks were found or no tasks are linked.
   * @throws IllegalArgumentException if the status argument is invalid.
   */
  public ArrayList<Task> getAllTasksWithStatus(String status) {
    if (!(status.equals("Completed") || status.equals("Finished"))) throw new IllegalArgumentException("Invalid argument status.");
    ArrayList<Task> toReturnTasks = new ArrayList<>();
    for (Task task : taskList) if (task.getStatus().equals(status)) toReturnTasks.add(task);
    return toReturnTasks;
  }

  /**
   * Getter for task linked to the project by index.
   * @param index Value between [0; taskList.size() - 1] representing the position of the task in the list of the tasks to be returned.
   * @return The task at the position of the index.
   * @throws IllegalArgumentException if the index argument is invalid.
   * @throws ArrayIndexOutOfBoundsException if the index is out of bounds for the array list.
   */
  public Task getTaskByIndex(int index) {
    if (index < 0 || index > taskList.size() - 1) throw new IllegalArgumentException("Attempt to access array list with invalid index.");
    return taskList.get(index);
  }

  /**
   * Getter for task linked to the project by id.
   * @param id The id of the task to be returned.
   * @return The task with the equal id.
   * @throws NoSuchElementException if a task with matching id could not be found.
   */
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
      for (int i = 0; i < 3; i++) {
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

  /**
   * Add method with extended number of defined values which creates and links a new task.
   * @param projectId Id value of the project.
   * @param title Title for the task.
   * @param description Description of the project.
   * @param estimatedWorkHours Value between [1; +inf] representing the number of expected work hours that need to be spent on the task.
   * @param deadline MyDate object representing the deadline.
   * @return The newly created task.
   * @throws IllegalArgumentException if the task's title is longer then 14 chars.
   * @throws IllegalArgumentException if the estimated work hours argument is invalid.
   * @throws IllegalArgumentException if deadline is in the past.
   */
  public Task addTask(String projectId, String title, String description, float estimatedWorkHours, MyDate deadline) {
    Task toReturn = new Task(generateId(projectId), title, description, estimatedWorkHours, deadline);
    taskList.add(toReturn);
    return toReturn;
  }

  /**
   * Add method with extended number of defined values which creates and links a new task.
   * @param projectId Id value of the project.
   * @param title Title for the task.
   * @param estimatedWorkHours Value between [1; +inf] representing the number of expected work hours that need to be spent on the task.
   * @param deadline MyDate object representing the deadline.
   * @return The newly created task.
   * @throws IllegalArgumentException if the task's title is longer then 14 chars.
   * @throws IllegalArgumentException if the estimated work hours argument is invalid.
   * @throws IllegalArgumentException if deadline is in the past.
   */
  public Task addTask(String projectId, String title, float estimatedWorkHours, MyDate deadline) {
    Task toReturn = new Task(generateId(projectId), title, estimatedWorkHours, deadline);
    taskList.add(toReturn);
    return toReturn;
  }

  /**
   * Removes any task that matches the argument id.
   * @param id The id of the task to be removed.
   * @throws NoSuchElementException if a task with matching id could not be found.
   * @throws UnsupportedOperationException if the task is assigned to any requirements.
   * @throws UnsupportedOperationException if the task is assigned to any members.
   */
  public void removeTask(String id) {
    Task task = getTaskById(id);
    if (task.getNumberOfAssignedRequirements() != 0) throw new UnsupportedOperationException("Could not remove task because it is assigned to some requirements.");
    if (task.getNumberOfAssignedMembers() != 0) throw new UnsupportedOperationException("Could not remove task because it is assigned to some members.");
    taskList.remove(task);
  }

  /**
   * Removes any task that matches the argument's task id.
   * @param task The task object of which id will be looked for to remove.
   * @throws IllegalArgumentException if the task argument is null.
   * @throws NoSuchElementException if a task with matching id could not be found.
   * @throws UnsupportedOperationException if the task is assigned to any requirements.
   * @throws UnsupportedOperationException if the task is assigned to any members.
   */
  public void removeTask(Task task) {
    if (task == null) throw new IllegalArgumentException("Task argument is null.");
    removeTask(task.getId());
  }

  /**
   * Calculates the so far total worked hours on the project.
   * @return Total so far worked hours on the project.
   */
  public float getTotalWorkedHours() {
    float totalWorkedHours = 0;
    for (Task task : taskList) totalWorkedHours += task.getTotalWorkedHours();
    return totalWorkedHours;
  }
}
