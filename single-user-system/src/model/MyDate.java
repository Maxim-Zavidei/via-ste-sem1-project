package model;

import java.time.LocalDate;

/**
 * A class to store and process date values.
 */
public class MyDate {

  private int day;
  private int month;
  private int year;

  // ------------------------------ Constructors ------------------------------

  /**
   * Constructor with defined date values.
   * @param day Value between [1; 31] representing the day.
   * @param month Value between [1; 12] representing the month.
   * @param year Value between [1; +inf] representing the year.
   * @throws IllegalArgumentException if any of the arguments are invalid.
   */
  public MyDate(int day, int month, int year) {
    set(day, month, year);
  }

  /**
   * Constructor with current os date values.
   */
  public MyDate() {
    LocalDate today = LocalDate.now();
    day = today.getDayOfMonth();
    month = today.getMonthValue();
    year = today.getYear();
  }

  // ------------------------------ Setters ------------------------------

  /**
   * Setter for all instance variables of the class.
   * @param day Value between [1; 31] representing the day.
   * @param month Value between [1; 12] representing the month.
   * @param year Value between [1; +inf] representing the year.
   * @throws IllegalArgumentException if any of the arguments are invalid.
   */
  public void set(int day, int month, int year) {
    if (year < 0) throw new IllegalArgumentException("Invalid year argument.");
    if (month < 1 || 12 < month) throw new IllegalArgumentException("Invalid month argument.");
    if (day < 1 || 31 < day) throw new IllegalArgumentException("Invalid day argument.");

    this.year = year;
    this.month = month;
    this.day = day;
  }

  // ------------------------------ Getters ------------------------------

  /**
   * Getter for day instance variable.
   * @return Value between [1; 31] representing the day.
   */
  public int getDay() {
    return day;
  }

  /**
   * Getter for month instance variable.
   * @return Value between [1; 12] representing the month.
   */
  public int getMonth() {
    return month;
  }

  /**
   * Getter for year instance variable.
   * @return Value between [1; +inf] representing the year.
   */
  public int getYear() {
    return year;
  }

  /**
   * Returns the english name for the month of the date.
   * @return A string with the month name.
   */
  public String getMonthName() {
    return new String[] {
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    }[month - 1];
  }

  // ------------------------------ Other Methods ------------------------------

  /**
   * Checks if the year is a leap year.
   * @return Whether the leap is year or not.
   */
  public boolean isLeapYear() {
    return (year & 3) == 0 && ((year % 25) != 0 || (year & 15) == 0);
  }

  /**
   * Calculates the number of days in the month.
   * @return Value between [28, 31] representing the number of days in month.
   */
  public int numberOfDaysInMonth() {
    return 31 - (month == 2 ? isLeapYear() ? 2 : 3 : (month - 1) % 7 % 2);
  }

  /**
   * Checks whether the date is before the argument date.
   * @param other A date to compare to.
   * @return Whether the date is before argument date.
   * @throws IllegalArgumentException if the date argument is null.
   */
  public boolean isBefore(MyDate other) {
    if (other == null) throw new IllegalArgumentException("Null date argument.");
    return 366 * (other.year - year) + 31 * (other.month - month) + other.day - day >= 0;
  }

  /**
   * Sets the date to the date of the next day.
   */
  public void stepForward() {
    day = day + 1 > numberOfDaysInMonth() ? month + 1 > 12 ? month = -year + ++year : -month + ++month : ++day;
  }

  /**
   * Sets the date to the date after next days argument.
   * @param days Value between [1, +inf] representing number of days to skip.
   * @throws IllegalArgumentException if the days argument is invalid.
   */
  public void stepForward(int days) {
    if (days < 1) throw new IllegalArgumentException("Invalid argument for days to skip.");
    while (days-- != -1) stepForward();
  }

  /**
   * Returns the english name for the day of the date.
   * @return A string with the day name.
   */
  public String dayOfWeek() {
    return new String[] {
        "Saturday",
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday"
    }[(day + (int) Math.round(2.6 * month - 0.2) + year + (year / 4) + (month / 400) - ((month / 100) * 2)) % 7];
  }

  /**
   * Copies the date object.
   * @return The copied date object.
   */
  public MyDate copy() {
    return new MyDate(day, month, year);
  }

  /**
   * Checks whether the date is equaled to the argument object.
   * @param obj Should be an object of type MyDate.
   * @return Whether the date is equaled to the argument object
   */
  @Override public boolean equals(Object obj) {
    if (!(obj instanceof MyDate)) return false;
    MyDate other = (MyDate) obj;
    return day == other.day && month == other.month && year == other.year;
  }

  /**
   * Returns a string with the the formatted date.
   * @return A string representing the date formatted as dd/mm/yyyy.
   */
  @Override public String toString() {
    return String.format("%02d/%02d/%02d", day, month, year);
  }
}