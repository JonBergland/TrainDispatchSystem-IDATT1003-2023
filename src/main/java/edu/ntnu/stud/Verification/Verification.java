package edu.ntnu.stud.Verification;

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
  public static void requireNonZeroNonLessThanMinus1Integer(int checkInteger) {
    requireNonZeroNonLessThanMinus1Integer(checkInteger, "Integer is 0",
        "Integer is less than -1");
  }

}
