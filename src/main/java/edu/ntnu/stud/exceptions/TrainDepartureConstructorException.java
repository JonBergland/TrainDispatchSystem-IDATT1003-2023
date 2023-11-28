package edu.ntnu.stud.exceptions;

/**
 * Exception class for errors that occur in the creation of a TrainDeparture-object.
 * <p>
 * This exception is thrown when there is an issue during validation of input parameters
 * during the creation of a TrainDeparture-object. It extends the standard {@code Exception}
 * class.
 * </p>
 *
 * @see Exception
 */
public class TrainDepartureConstructorException extends Exception {
  /**
   * Constructs a TrainDepartureConstructorException with a specified error message.
   * <p>
   * This constructor creates an instance of the exception with an errormessage
   * providing details on why the exception is thrown
   * </p>
   *
   * @param errorMessage      A String containing the details on why the exception
   *                          is thrown
   */
  public TrainDepartureConstructorException(String errorMessage) {
    super(errorMessage);
  }
}
