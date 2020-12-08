package model;

import java.awt.event.WindowStateListener;
import java.util.ArrayList;

public class Member
{
  private String firstName;
  private String lastName;
  private String email;
  private long telephoneNumber;
  private ArrayList<Task> assignedTasks;
  private MyDate birthday;

  public Member(String firstName, String lastName, MyDate birthday,
      String email, long telephoneNumber)
  {
    setFirstName(firstName);
    setLastName(lastName);
    this.birthday = birthday.copy();
    setEmail(email);
    setTelephoneNumber(telephoneNumber);
    assignedTasks = new ArrayList<>();
  }

  public Member(String firstName, String lastName, MyDate birthday,
      String email)
  {
    setFirstName(firstName);
    setLastName(lastName);
    this.birthday = birthday.copy();
    setEmail(email);
    telephoneNumber = 0;
    assignedTasks = new ArrayList<>();
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getFullName()
  {
    return firstName + " " + lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public long getTelephoneNumber()
  {
    return telephoneNumber;
  }

  public void setTelephoneNumber(long telephoneNumber)
  {
    this.telephoneNumber = telephoneNumber;
  }

  public MyDate getBirthday()
  {
    return birthday;
  }

  public ArrayList<Task> getAllAssignedTasks()
  {
    return assignedTasks;
  }

  public boolean isAssignedToTask(Task task)
  {

    for (int i = 0; i < assignedTasks.size(); i++)
    {
      if (assignedTasks.get(i).equals(task))
      {
        return true;
      }
    }

    return false;
  }

  public boolean assignTask(Task task)
  {
    int v = 0;

    for (int i = 0; i < assignedTasks.size(); i++)
    {
      if (assignedTasks.get(i).equals(task))
      {
        v++;
      }
    }
    if (v == 0)
    {
      assignedTasks.add(task);
      return true;
    }
    return false;
  }

  public boolean unassignFromTask(Task task)
  {
    if (isAssignedToTask(task))
    {
      assignedTasks.remove(task);
      return true;
    }
    return false;
  }

//  public boolean unassignFromEveryTask(Task task)
//  {
//
//  }

//  telephoneNumber, should revise
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Member))
    {
      return false;
    }
    Member other = (Member)obj;

    for(int i = 0; i < assignedTasks.size(); i++)
    {
      if (!assignedTasks.get(i).equals(other.assignedTasks.get(i)))
      {
        return false;
      }
    }
    return firstName.equals(other.firstName) && lastName.equals(other.lastName) && email.equals(other.email)
        && birthday.equals(other.birthday);
  }
}
