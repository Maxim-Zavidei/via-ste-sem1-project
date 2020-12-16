package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.Serializable;

/**
 * A class to create, store and process members.
 */
public class MemberList implements Serializable {

  private ArrayList<Member> memberList;

  // ------------------------------ Constructors ------------------------------

  /**
   * Constructor to initialise the instance variables.
   */
  public MemberList() {
    memberList = new ArrayList<>();
  }

  // ------------------------------ Getters for Members ------------------------------

  /**
   * Getter for all the members.
   * @return All the members or empty array list if no members are linked.
   */
  public ArrayList<Member> getAllMembers() {
    return memberList;
  }

  /**
   * Getter for all the members with a specific birthday.
   * @param birthday The birthday of the members to look for.
   * @return All the members with that birtday or empty array list if no members are linked.
   */
  public ArrayList<Member> getMembersByBirthday(MyDate birthday) {
    ArrayList<Member> membersToReturn = new ArrayList<>();
    for (Member member : memberList) if (member.getBirthday().equals(birthday)) membersToReturn.add(member);
    return membersToReturn;
  }

  /**
   * Getter for member by full name.
   * @param firstName The first name of the member to look for.
   * @param lastName The last name of the member to look for.
   * @return The member with equal full name.
   * @throws NoSuchElementException if a member with matching full name could not be found.
   */
    public Member getMemberByFullName(String firstName, String lastName) {
      for (Member member : memberList) if (member.getFirstName().equals(firstName) && member.getLastName().equals(lastName)) return member;
      throw new NoSuchElementException("Could not find any member with that name.");
    }

  /**
   * Getter for member by email.
   * @param email The email of the member to look for.
   * @return The member with equal email.
   * @throws NoSuchElementException if a member with matching email could not be found.
   */
  public Member getMemberByEmail(String email) {
    for (Member member : memberList) if (member.getEmail().equals(email)) return member;
    throw new NoSuchElementException("Could not find any member with that eamil.");
  }

  // ------------------------------ Other Methods ------------------------------

  /**
   * Add method with extended number of defined values which creates a new member.
   * @param firstName Member's first name.
   * @param lastName Member's last name.
   * @param birthday Member's birthday.
   * @param email Member's email.
   * @param telephoneNumber Member's telephone number.
   * @return The newly created member.
   * @throws IllegalArgumentException if the a member with the same full name already exists.
   * @throws IllegalArgumentException if the a member with the same email already exists.
   */
  public Member hireMember(String firstName, String lastName, MyDate birthday, String email, long telephoneNumber) {
    for (Member member : memberList) if (member.getFirstName().equals(firstName) && member.getLastName().equals(lastName) || member.getEmail().equals(email)) throw new IllegalArgumentException("A member with this full name or email is already registered.");
    Member memberToReturn = new Member(firstName, lastName, birthday, email, telephoneNumber);
    memberList.add(memberToReturn);
    return memberToReturn;
  }

  /**
   * Add method with minimal number of defined values which creates a new member.
   * @param firstName Member's first name.
   * @param lastName Member's last name.
   * @param birthday Member's birthday.
   * @param email Member's email.
   * @return The newly created member.
   * @throws IllegalArgumentException if the a member with the same full name already exists.
   * @throws IllegalArgumentException if the a member with the same email already exists.
   */
  public Member hireMember(String firstName, String lastName, MyDate birthday, String email) {
    for (Member member : memberList) if (member.getFirstName().equals(firstName) && member.getLastName().equals(lastName) || member.getEmail().equals(email)) throw new IllegalArgumentException("A member with this full name or email is already registered.");
    Member memberToReturn = new Member(firstName, lastName, birthday, email);
    memberList.add(memberToReturn);
    return memberToReturn;
  }

  /**
   * Removes any member with matcing full name.
   * @param firstName The first name of the member to be removed.
   * @param lastName The last name of the member to be removed.
   * @throws UnsupportedOperationException if the member has any assigned tasks.
   * @throws NoSuchElementException if a member with matching full name could not be found.
   */
  public void fireMember(String firstName, String lastName) {
    for (int i = 0; i < memberList.size(); i++) if (memberList.get(i).getFirstName().equals(firstName) && memberList.get(i).getLastName().equals(lastName)) {
      if (memberList.get(i).getAllAssignedTasks().size() != 0) throw new UnsupportedOperationException("Could not fire member because he has assigned tasks.");
      memberList.remove(i);
      return;
    }
    throw new NoSuchElementException("Could not find the member wtih this name.");
  }

  /**
   * Removes any member with matcing full name of the argument member.
   * @param member The member of whose name will be looked for.
   * @throws UnsupportedOperationException if the member has any assigned tasks.
   * @throws NoSuchElementException if a member with matching full name could not be found.
   */
  public void fireMember(Member member) {
    fireMember(member.getFirstName(), member.getLastName());
  }
}