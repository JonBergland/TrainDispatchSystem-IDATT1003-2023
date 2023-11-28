package edu.ntnu.stud.verification;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
public class Verification {
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

  public static void requireNonZeroNonLessThanMinus1Integer(int checkInteger)
      throws IllegalArgumentException{
    requireNonZeroNonLessThanMinus1Integer(checkInteger, "Integer is 0",
        "Integer is less than -1");
  }

  public static void requireNonNullOrBlank(String checkString, String nullPointerMessage,
                                           String blankMessage)
      throws NullPointerException, IllegalArgumentException {
    if (Objects.requireNonNull(checkString, nullPointerMessage).isBlank()) {
      throw new IllegalArgumentException(blankMessage);
    }
  }

  public static void requireNonNullOrBlank(String checkString)
      throws NullPointerException, IllegalArgumentException {
    requireNonNullOrBlank(checkString, "String is null", "String is blank");
  }

  public static void requireStringOnFormatHHmm(String checkString, String formatMessage)
      throws DateTimeException {
    try {
      LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern(checkString)));
    } catch (DateTimeException e) {
      throw new IllegalArgumentException(formatMessage);
    }

  }

}
