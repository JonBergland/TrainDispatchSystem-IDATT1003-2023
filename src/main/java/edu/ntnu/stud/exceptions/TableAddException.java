package edu.ntnu.stud.exceptions;

/**
 * Exception class for errors that occur when assigning a new train number
 * and TrainDeparture-object to the HashMap in the Table-class
 * <p>
 * This exception is thrown during the validation of the train number parameter in
 * the Table-class. It extends the standard {@code Exception} class.
 * </p>
 *
 * @see Exception
 */
public class TableAddException extends Exception{
  /**
   * Constructs a TableAddException with a specified error message.
   * <p>
   * This constructor creates an instance of the exception with an errormessage
   * providing details on why the exception is thrown
   * </p>
   *
   * @param errorMessage      A String containing the details on why the exception
   *                          is thrown
   */
  public TableAddException(String errorMessage) {
    super(errorMessage);
  }

  /**
   * Constructs a TableAddException with predetermined error message.
   * <p>
   * This constructor creates an instance of the exception with an predetermined
   * errormessage providing general details on why the exception is thrown
   * </p>
   */
  public TableAddException() {
    super("Something wrong happened during the creation of the train-departure");
  }
}

