package edu.ntnu.stud.exceptions;

public class TrackException extends Exception {
  public TrackException(String errorMessage) {
    super(errorMessage);
  }

  public TrackException() {
    super("The track was not accepted");
  }
}
