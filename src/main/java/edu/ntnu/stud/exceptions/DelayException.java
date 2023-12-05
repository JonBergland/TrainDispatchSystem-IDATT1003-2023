package edu.ntnu.stud.exceptions;

/**
 * Exception class for errors that occur in the assigning or changing of delay.
 * <p>
 * This exception is thrown when there is an issue during the updating of the LocalTime
 * variable {@code delay} in the TrainDeparture-object. It extends the standard
 * {@code Exception} class.
 * </p>
 *
 * @see Exception
 */
public class DelayException extends Exception {
  /**
   * Constructs a DelayException with a specified error message.
   * <p>
   * This constructor creates an instance of the exception with an errormessage
   * providing details on why the exception is thrown
   * </p>
   *
   * @param errorMessage      A String containing the details on why the exception
   *                          is thrown
   */
  public DelayException(String errorMessage) {
    super(errorMessage);
  }

  /**
   * Constructs a DelayException with predetermined error message.
   * <p>
   * This constructor creates an instance of the exception with an predetermined
   * errormessage providing general details on why the exception is thrown
   * </p>
   */
  public DelayException() {
    super("The delay was not accepted");
  }
}

