package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Input {
  Scanner in = new Scanner(System.in);

  /**
   * Checks if a given string can be parsed into an integer.
   * <p>
   * This method attempts to parse the specified string into an integer using {@code Integer.parseInt}.
   * If the parsing is successful, the method returns {@code true}; otherwise, it returns {@code false}.
   * </p>
   *
   * @param number    The string to be checked for integer validity.
   * @return {@code true} if the string can be parsed into an integer, {@code false} otherwise.
   * @see Integer#parseInt(String)
   */
  private boolean tryInt(String number) { //en metode for å sjekke om en streng er et tall (int)
    boolean isInt = true;
    try {
      Integer.parseInt(number); //sjekker om strengen kan konverteres til tall
    } catch (NumberFormatException e) {
      isInt = false;
     }
    return isInt;
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
   * @see #tryInt(String)
   */
  public int intInput(String print, int dummyValue) {
    System.out.println(print);
    String intInput = in.nextLine();
    int output = dummyValue;
    if (tryInt(intInput)) {
      output = Integer.parseInt(intInput);
    }
    return output;
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
  public String stringInput(String print) { //en metode for en streng-input fra bruker
    System.out.println(print); //skriver ut en egendefinert streng til bruker
    return in.nextLine(); //returnerer bruker-inputet
  }
}
