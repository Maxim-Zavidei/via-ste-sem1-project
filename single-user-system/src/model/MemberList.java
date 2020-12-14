package model;

import java.util.ArrayList;

/**
 * A class to store and process a number of members
 */
public class MemberList
{
  private ArrayList<Member> memberList;

  /**
   * Constructor to initialise memberList instance variable
   */
  public MemberList()
  {
    memberList = new ArrayList<>();
  }

  /**
   * Getter for memberList instance variable
   * @return memberList
   */
  public ArrayList<Member> getAllMembers()
  {
    return memberList;
  }

  /**
   * Method to get members with a specific birthday
   * @param birthday
   * @return members with that birthday
   */
  public ArrayList<Member> getMembersByBirthday(MyDate birthday)
  {
    ArrayList<Member> members = new ArrayList<>();

    for (int i = 0; i < memberList.size(); i++)
    {
      if (memberList.get(i).getBirthday().equals(birthday))
      {
        members.add(memberList.get(i));
      }
    }
    return members;
  }

  /**
   * Method to get a member by it's full name
   * @param firstName
   * @param lastName
   * @return member by that specific name
   */
    public Member getMemberByFullName(String firstName, String lastName)
    {
      for (int i = 0; i < memberList.size(); i++)
      {
        if (memberList.get(i).getFirstName().equals(firstName) && memberList.get(i).getLastName().equals(lastName))
        {
          return memberList.get(i);
        }
      }
      throw new IllegalStateException("No one matches in the list");
    }

  /**
   * Method to get a member by it's email
   * @param email
   * @return member by that specific email
   */
    public Member getMemberByEmail(String email)
      {
        for (int i = 0; i < memberList.size(); i++)
        {
          if (memberList.get(i).getEmail().equals(email))
          {
            return memberList.get(i);
          }
        }
        throw new IllegalStateException("No one matches in the list");

      }

  /**
   * Method that takes values for creating a new member object
   * and adding it to the memberList
   * @param firstName
   * @param lastName
   * @param birthday
   * @param email
   * @param telephoneNumber
   * @return true if member was added, false otherwise
   */
  public boolean hireMember(String firstName, String lastName, MyDate birthday,
      String email, long telephoneNumber)
  {
    Member member = new Member(firstName, lastName, birthday, email,
        telephoneNumber);

    for (int i = 0; i < memberList.size(); i++)
    {
      if (memberList.get(i).equals(member))
      {
        return false;
      }
    }
    memberList.add(member);
    return true;

  }

  /**
   * Method that takes values for creating a new member object,
   * excluding telephone number, and adding it to the memberList
   * @param firstName
   * @param lastName
   * @param birthday
   * @param email
   * @return true if member was added, false otherwise
   */
  public boolean hireMember(String firstName, String lastName, MyDate birthday,
      String email)
  {
    Member member = new Member(firstName, lastName, birthday, email);

    for (int i = 0; i < memberList.size(); i++)
    {
      if (memberList.get(i).equals(member))
      {
        return false;
      }
    }
    memberList.add(member);
    return true;

  }

  /**
   * Method to remove a member from memberList
   * @param member
   * @return true if removed, false otherwise
   */
  public boolean fireMember(Member member)
  {
    for (int i = 0; i < memberList.size(); i++)
    {
      if (memberList.get(i).equals(member))
      {
        memberList.remove(member);
        return true;
      }
    }
    return false;
  }

  /**
   * Method to remove a member from memberList by it's first name and last name
   * @param firstName
   * @param lastName
   * @return true if removed, false otherwise
   */
  public boolean fireMember(String firstName, String lastName)
  {
    for (int i = 0; i < memberList.size(); i++)
    {
      if (memberList.get(i).getFirstName().equals(firstName) && memberList
          .get(i).getLastName().equals(lastName))
      {
        memberList.remove(memberList.get(i));
        return true;
      }
    }
    return false;
  }
}
