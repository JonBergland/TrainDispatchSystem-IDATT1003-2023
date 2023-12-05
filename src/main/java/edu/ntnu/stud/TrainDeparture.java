package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.DelayException;
import edu.ntnu.stud.exceptions.TrackException;
import edu.ntnu.stud.exceptions.TrainDepartureConstructorException;
import edu.ntnu.stud.verification.Verification;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a train-departure with information such as departure time, line name,
 * destination, track and eventual delay
 * <p>
 *   This class encapsulates details about a train-departure, providing methods to retrieve
 *   information and perform operations related to the train-departure, including mutable
 *   for track and delay
 * </p>
 *
 * @see Verification
 * @see TrainDepartureConstructorException
 * @see TrackException
 */
public final class TrainDeparture {
  private LocalTime originalDepartureTime;
  private String line;
  private String destination;
  private int track;
  private LocalTime delay;

  /**
   * Constructs a TrainDeparture object with the specified attributes.
   * <p>
   * This method constructs a TrainDeparture object with a specific departure time,
   * the name of the train line, the train-departure's destination and the track
   * the train arrives at. It validates the parameters to ensure they meet requirements
   * using the Verification class. If a validation fails, a
   * {@code TrainDepartureConstructorException} is thrown. Also initializes the {@code delay}
   * variable as a LocalTime of 0 hours and 0 minutes.
   * </p>
   *
   * @param originalDepartureTime Original time for departure
   * @param line                  The name of the line
   * @param destination           The train's destination
   * @param track                 The track that the train arrives at
   * @throws TrainDepartureConstructorException If any parameters fail to meet the requirements
   *                                            set, an exception is thrown. The exception message
   *                                            provides details about which parameter didn't meet
   *                                            the requirement and why.
   * @see Verification#requireStringOnFormatHHmm(String, String)
   * @see Verification#requireNonNullOrBlank(String, String, String)
   * @see Verification#requireNonZeroNonLessThanMinus1Integer(int, String, String)
   */
  public TrainDeparture(String originalDepartureTime, String line, String destination,
                        int track) throws TrainDepartureConstructorException {
    try {
      setOriginalDepartureTime(originalDepartureTime);
      setLine(line);
      setDestination(destination);
      setTrack(track);
    } catch (Exception e) {
      throw new TrainDepartureConstructorException(
          e.getMessage());
    }

    this.delay = LocalTime.of(0, 0); //initialize the delay at 0 hours and 0 minutes
  }

  /**
   * Gets the original departure time of the train.
   * <p>
   * This method returns the LocalTime representing the original departure
   * time of the train-departure. This variable is set during the creation
   * of the TrainDeparture-object and can't be changed.
   * </p>
   *
   * @return A LocalTime representing the original departure time to
   *         the train-departure
   */
  public LocalTime getOriginalDepartureTime() {
    return originalDepartureTime;
  }

  /**
   * Gets the name of the train-departure's line.
   * <p>
   * This method returns a String representing the name of the train-departure's
   * line. This variable is set during the creation of the TrainDeparture-object
   * and can't be changed.
   * </p>
   *
   * @return A String representing the name of the train-departure's line
   */
  public String getLine() {
    return line;
  }

  /**
   * Gets the destination for the train-departure.
   * <p>
   * This method returns a String representing the train-departure's
   * destination. This variable is set during the creation of the
   * TrainDeparture-object and can't be changed.
   * </p>
   *
   * @return A String representing the train-departure's destination
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Gets the train-departure's designated track.
   * <p>
   * This method returns an Integer representing the train-departure's
   * track. This variable is set during the creation of the
   * TrainDeparture-object and can't be changed.
   * </p>
   *
   * @return An Integer representing the train-departure's track
   */
  public int getTrack() {
    return track;
  }

  /**
   * Gets the delay for the train departure.
   * <p>
   * This method returns the LocalTime representing the train-departure's
   * delay. This variable is set during the creation of the
   * TrainDeparture-object and can't be changed.
   * </p>
   *
   * @return A LocalTime representing the train-departure's delay
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * Gets the departure time (original + delay) for the train departure.
   *
   * @return departureTime
   */
  public LocalTime getDepartureTime() {
    LocalTime departureTime = this.originalDepartureTime.plusHours(this.delay.getHour());
    departureTime = departureTime.plusMinutes(this.delay.getMinute());
    return departureTime;
  }

  public TrainDeparture getDeepCopy() {
    try {
      TrainDeparture deepTrainDeparture = new TrainDeparture(
          originalDepartureTime.toString(), line, destination, track);
      deepTrainDeparture.setDelay(delay.toString());
      return deepTrainDeparture;
    } catch (TrainDepartureConstructorException | DelayException e) {
      return null;
    }
  }


  private void setOriginalDepartureTime(String originalDepartureTime)
      throws IllegalArgumentException {
    Verification.requireStringOnFormatHHmm(originalDepartureTime,
        "Departure time was not formatted properly");
    this.originalDepartureTime = LocalTime.parse(LocalTime.now().format(
        DateTimeFormatter.ofPattern(originalDepartureTime)));
  }

  private void setLine(String line)
      throws IllegalArgumentException {
    Verification.requireNonNullOrBlank(line, "Line was null",
        "Line was empty");
    this.line = line;
  }

  private void setDestination(String destination)
      throws IllegalArgumentException {
      Verification.requireNonNullOrBlank(destination, "Destination was null",
          "Destination was empty");
      this.destination = destination;
  }

  /**
   * Sets a track for the train departure using the integer parameter.
   *
   * @param track                   sets track as the integer parameter
   */
  public boolean setTrack(int track) {
    try {
      Verification.requireNonZeroNonLessThanMinus1Integer(track);
      this.track = track;
    } catch (IllegalArgumentException e) {
      this.track = -1;
    }
    return true;
  }

  /**
   * Sets a delay for the train departure using the LocalTime parameter.
   *
   * @param delay                   sets delay as the LocalTime parameter
   */
  public void setDelay(String delay) throws DelayException {
    try {
      Verification.requireStringOnFormatHHmm(delay,
          "The delay was not formatted properly");
      this.delay = LocalTime.parse(LocalTime.now().format(
          DateTimeFormatter.ofPattern(delay)));
    } catch (IllegalArgumentException e) {
      throw new DelayException(e.getMessage());
    }
  }

  /**
   * Turn all the information in the TrainDeparture object to a String.
   *
   * @return String
   */
  public String toString(int trainNumber) {
    String output = String.format("%" + -6 + "s", this.originalDepartureTime) + "|"
        + String.format("%" + 1 + "s", "") + String.format("%" + -4 + "s", this.line) + "|"
        + String.format("%" + 1 + "s", "") + String.format("%" + -4 + "s", trainNumber) + "|"
        + String.format("%" + 1 + "s", "") + String.format("%" + -16 + "s", this.destination) + "|";

    String track = String.format("%" + 6 + "s", " ") + "|";
    if (getTrack() > -1) {
      track = String.format("%" + 4 + "s", "") + String.format("%" + -2 + "s", this.track) + "|";
    }
    output += track;
    String delay = " ";
    if (getDelay().isAfter(LocalTime.of(0, 0))) {
      delay += this.delay;
    }
    output += String.format("%" + 12 + "s", delay);
    return output;
  }
}
