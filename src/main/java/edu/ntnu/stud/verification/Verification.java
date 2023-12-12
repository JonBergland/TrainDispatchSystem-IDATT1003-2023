package edu.ntnu.stud.verification;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Utility class for performing various verification checks on integers and strings.
 *
 * @see IllegalArgumentException Thrown when an argument does not meet the specified requirements.
 * @see NullPointerException     Thrown when a required object is found to be null.
 * @see DateTimeException        Thrown when there is an issue with date and time parsing.
 */
public class Verification {

  /**
   * Requires that the provided integer is non-zero and not less than -1.
   *
   * @param checkInteger              The integer to be checked.
   * @param zeroMessage               Message to be thrown if the integer is zero.
   * @param lessThanMinus1            Message to be thrown if the integer is less than -1.
   * @throws IllegalArgumentException If the integer is zero or less than -1.
   */
  public static void requireNonZeroNonLessThanMinus1Integer(int checkInteger, String zeroMessage,
                                                            String lessThanMinus1)
      throws IllegalArgumentException {
    if (checkInteger == 0) {
      throw new IllegalArgumentException(zeroMessage);
    }
    if (checkInteger < -1) {
      throw new IllegalArgumentException(lessThanMinus1);
    }
  }

  /**
   * Requires that the provided integer is non-zero and not less than -1.
   *
   * @param checkInteger              The integer to be checked.
   * @throws IllegalArgumentException If the integer is zero or less than -1.
   */
  public static void requireNonZeroNonLessThanMinus1Integer(int checkInteger)
      throws IllegalArgumentException {
    requireNonZeroNonLessThanMinus1Integer(checkInteger, "Integer is 0",
        "Integer is less than -1");
  }

  /**
   * Requires that the provided string is non-null and not blank.
   *
   * @param checkString               The string to be checked.
   * @param nullPointerMessage        Message to be thrown if the string is null.
   * @param blankMessage              Message to be thrown if the string is blank.
   * @throws NullPointerException     If the string is null.
   * @throws IllegalArgumentException If the string is blank.
   */
  public static void requireNonNullOrBlank(String checkString, String nullPointerMessage,
                                           String blankMessage)
      throws NullPointerException, IllegalArgumentException {
    if (Objects.requireNonNull(checkString, nullPointerMessage).isBlank()) {
      throw new IllegalArgumentException(blankMessage);
    }
  }

  /**
   * Requires that the provided string is non-null and not blank.
   *
   * @param checkString               The string to be checked.
   * @throws NullPointerException     If the string is null.
   * @throws IllegalArgumentException If the string is blank.
   */
  public static void requireNonNullOrBlank(String checkString)
      throws NullPointerException, IllegalArgumentException {
    requireNonNullOrBlank(checkString, "String is null", "String is blank");
  }

  /**
   * Requires that the provided string is in the format "HH:mm" and can be parsed
   * into an LocalTime.
   *
   * @param checkString               The string to be checked.
   * @param formatMessage             Message to be thrown if the string is not
   *                                  in the specified format.
   * @param nullPointerMessage        Message to be thrown if the string is null.
   * @throws DateTimeException        If there is an issue with date and time parsing.
   * @throws IllegalArgumentException If the string is not in the specified format or is null.
   */
  public static void requireStringOnFormatHHmm(String checkString, String formatMessage,
                                               String nullPointerMessage)
      throws DateTimeException {
    try {
      LocalTime.parse(checkString, DateTimeFormatter.ofPattern("HH:mm"));
    } catch (DateTimeException e) {
      throw new IllegalArgumentException(formatMessage);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(nullPointerMessage);
    }
  }

  /**
   * Requires that the provided string is in the format "HH:mm" and can be parsed
   * into an LocalTime.
   *
   * @param checkString               The string to be checked.
   * @throws DateTimeException        If there is an issue with date and time parsing.
   * @throws IllegalArgumentException If the string is not in the specified format or is null.
   */
  public static void requireStringOnFormatHHmm(String checkString)
      throws DateTimeException {
    requireStringOnFormatHHmm(checkString, "The String is not formatted properly",
        "The String is null");
  }

  /**
   * Requires that the provided integer is not zero or less.
   *
   * @param checkInteger              The integer to be checked.
   * @param zeroOrLessMessage         Message to be thrown if the integer is zero or less.
   * @throws IllegalArgumentException If the integer is zero or less.
   */
  public static void requireNonZeroOrLess(int checkInteger, String zeroOrLessMessage) {
    if (checkInteger <= 0) {
      throw new IllegalArgumentException(zeroOrLessMessage);
    }
  }

  /**
   * Requires that the provided integer is not zero or less.
   *
   * @param checkInteger              The integer to be checked.
   * @throws IllegalArgumentException If the integer is zero or less.
   */
  public static void requireNonZeroOrLess(int checkInteger) {
    requireNonZeroOrLess(checkInteger, "Integer is 0 or less");
  }

  /**
   * Requires that the provided integer is not less than zero.
   *
   * @param checkInteger              The integer to be checked.
   * @param zeroOrLessMessage         Message to be thrown if the integer is less than zero.
   * @throws IllegalArgumentException If the integer is less than zero.
   */
  public static void requireNonLessThanZero(int checkInteger, String zeroOrLessMessage) {
    if (checkInteger < 0) {
      throw new IllegalArgumentException(zeroOrLessMessage);
    }
  }

  /**
   * Requires that the provided integer is not less than zero.
   *
   * @param checkInteger              The integer to be checked.
   * @throws IllegalArgumentException If the integer is less than zero.
   */
  public static void requireNonLessThanZero(int checkInteger) {
    requireNonZeroOrLess(checkInteger, "Integer is less than zero");
  }
}
