package edu.ntnu.stud.exceptions;

/**
 * Exception class for errors that occur in the assigning or changing of the track.
 * <p>
 * This exception is thrown when there is an issue during assigning or changing of the
 * integer variable {@code track} in the TrainDeparture-object. It extends the standard
 * {@code Exception} class.
 * </p>
 *
 * @see Exception
 */
public class TrackException extends Exception {
  /**
   * Constructs a TrackException with a specified error message.
   * <p>
   * This constructor creates an instance of the exception with an errormessage
   * providing details on why the exception is thrown
   * </p>
   *
   * @param errorMessage      A String containing the details on why the exception
   *                          is thrown
   */
  public TrackException(String errorMessage) {
    super(errorMessage);
  }

  /**
   * Constructs a TrackException with predetermined error message.
   * <p>
   * This constructor creates an instance of the exception with an predetermined
   * errormessage providing general details on why the exception is thrown
   * </p>
   */
  public TrackException() {
    super("The track was not accepted");
  }
}
