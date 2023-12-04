package edu.ntnu.stud;

import java.util.Scanner;

public class Input {
  Scanner in = new Scanner(System.in);

  /**
   * Checks if a given string can be parsed into an integer.
   * <p>
   * This method attempts to parse the specified string into an integer using {@code Integer.parseInt}.
   * If the parsing is successful, the method returns the parsed number; otherwise, it returns a dummy value.
   * </p>
   *
   * @param number      The string to be checked for integer validity.
   * @param dummyValue  The value that gets returned if number can't be parsed into an integer
   * @return {@code number} if the string can be parsed into an integer, dummyValue otherwise.
   * @see Integer#parseInt(String)
   */
  public int tryInt(String number, int dummyValue) {
    try {
      return Integer.parseInt(number);
    } catch (NumberFormatException e) {
      return dummyValue;
     }
  }

  /**
   * Prompts the user for an integer input with a specified message.
   * <p>
   * This method displays the specified print message to the user, reads an integer input from the
   * user, and returns the entered value. If the user enters a non-integer or invalid input,
   * the method returns the specified dummyValue. The input validation is performed using the
   * {@code tryInt} method.
   * </p>
   *
   * @param print         The message to prompt the user for input.
   * @param dummyValue    The default value returned in case of invalid input.
   * @return An integer input provided by the user or the specified dummyValue if the input is invalid.
   * @see #tryInt(String, int)
   */
  public int intInput(String print, int dummyValue) {
    //System.out.println(print);
    String intInput = in.nextLine();
    return tryInt(intInput, dummyValue);
  }

  /**
   * Prompts the user for a string input with a specified message.
   * <p>
   * This method displays the specified print message to the user, waits for the user to input
   * a string, and returns the entered string. The entered string is obtained using the
   * {@code in.nextLine()} method.
   * </p>
   *
   * @param print   The message to prompt the user for a string input.
   * @return The string input provided by the user.
   */
  public String stringInput(String print) {
    System.out.println(print);
    return in.nextLine();
  }
}
