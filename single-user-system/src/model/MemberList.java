package model;

import java.util.ArrayList;

public class MemberList
{
  private ArrayList<Member> memberList;

  public MemberList()
  {
    memberList = new ArrayList<>();
  }

  public ArrayList<Member> getAllMembers()
  {
    return memberList;
  }

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
