package edu.ntnu.stud.exceptions;

/**
 * Exception class for errors that occur in the updating of a Clock-object.
 * <p>
 * This exception is thrown when there is an issue during the updating of the LocalTime
 * variable in the clock-object. It extends the standard {@code Exception}
 * class.
 * </p>
 *
 * @see Exception
 */
public class ClockException extends Exception{
  /**
   * Constructs a ClockException with a specified error message.
   * <p>
   * This constructor creates an instance of the exception with an errormessage
   * providing details on why the exception is thrown
   * </p>
   *
   * @param errorMessage      A String containing the details on why the exception
   *                          is thrown
   */
  public ClockException(String errorMessage) {
    super(errorMessage);
  }
  /**
   * Constructs a ClockException with predetermined error message.
   * <p>
   * This constructor creates an instance of the exception with an predetermined
   * errormessage providing general details on why the exception is thrown
   * </p>
   */
  public ClockException() {
    super("The new time was not accepted");
  }
}
