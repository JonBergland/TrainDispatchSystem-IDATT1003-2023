package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.DelayException;
import edu.ntnu.stud.exceptions.TrackException;
import edu.ntnu.stud.exceptions.TrainDepartureConstructorException;
import edu.ntnu.stud.verification.Verification;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a train-departure with information such as departure time, line name,
 * destination, track and eventual delay.
 * <p>
 * This class encapsulates details about a train-departure, providing methods to retrieve
 * information and perform operations related to the train-departure, including mutable
 * for track and delay
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
   * the train arrives at. It validates the parameters by using the set-methods in the
   * TrainDeparture-class. If a validation fails, a
   * {@code TrainDepartureConstructorException} is thrown. Also initializes the
   * {@code delay} variable as a LocalTime of 0 hours and 0 minutes.
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
   * @see TrainDeparture#setOriginalDepartureTime(String)
   * @see TrainDeparture#setLine(String)
   * @see TrainDeparture#setDestination(String)
   * @see TrainDeparture#setTrack(int)
   */
  public TrainDeparture(String originalDepartureTime, String line, String destination,
                        int track) throws TrainDepartureConstructorException {
    try {
      setOriginalDepartureTime(originalDepartureTime);
      setLine(line);
      setDestination(destination);
      setTrack(track);
    } catch (Exception e) {
      throw new TrainDepartureConstructorException(e.getMessage());
    }

    this.delay = LocalTime.of(0, 0); //initialize the delay at 0 hours and 0 minutes
  }

  /**
   * Constructs a TrainDeparture object from an already existing object.
   *
   * @param oldTrainDeparture The existing TrainDeparture object that is copied
   */
  public TrainDeparture(TrainDeparture oldTrainDeparture)
      throws TrainDepartureConstructorException {
    try {
      this.originalDepartureTime = oldTrainDeparture.getOriginalDepartureTime();
      this.line = oldTrainDeparture.getLine();
      this.destination = oldTrainDeparture.getDestination();
      this.track = oldTrainDeparture.getTrack();
      this.delay = oldTrainDeparture.getDelay();
    } catch (NullPointerException e) {
      throw new TrainDepartureConstructorException(e.getMessage());
    }

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
    return this.originalDepartureTime.plusHours(this.delay.getHour())
        .plusMinutes(this.delay.getMinute());
  }

  /**
   * Sets an original departure time for the train departure using the String parameter.
   *
   * @param originalDepartureTime sets track as the integer parameter
   */
  private void setOriginalDepartureTime(String originalDepartureTime)
      throws IllegalArgumentException {
    Verification.requireStringOnFormatHHmm(originalDepartureTime,
        "Departure time was not formatted properly",
        "The departure time was null");
    this.originalDepartureTime = LocalTime.parse(LocalTime.now().format(
        DateTimeFormatter.ofPattern(originalDepartureTime)));
  }

  /**
   * Sets a line for the train departure using the String parameter.
   *
   * @param line sets track as the integer parameter
   */
  private void setLine(String line)
      throws IllegalArgumentException {
    Verification.requireNonNullOrBlank(line, "Line was null",
        "Line was empty");
    this.line = line;
  }

  /**
   * Sets a destination for the train departure using the String parameter.
   *
   * @param destination sets track as the integer parameter
   */
  private void setDestination(String destination)
      throws IllegalArgumentException {
    Verification.requireNonNullOrBlank(destination, "Destination was null",
        "Destination was empty");
    this.destination = destination;
  }

  /**
   * Sets a track for the train departure using the integer parameter.
   *
   * @param track sets the track as the integer parameter
   */
  public void setTrack(int track) throws TrackException {
    try {
      Verification.requireNonZeroNonLessThanMinus1Integer(track);
      this.track = track;
    } catch (IllegalArgumentException e) {
      throw new TrackException(e.getMessage());
    }
  }

  /**
   * Sets a delay for the train departure using the int parameter. The int parameter
   * represent the delay in minutes. The {@code delay} is reset and the new delay
   * gets added.
   *
   * @param delayInMinutes represents the delay set in minutes. Gets added to the
   *                       LocalTime variable {@code delay}
   */
  public void setDelay(int delayInMinutes) throws DelayException {
    try {
      Verification.requireNonLessThanZero(delayInMinutes,
          "The delay was not formatted properly");
      this.delay = LocalTime.of(0, 0).plusMinutes(delayInMinutes);
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
    String output = String.format("%-6s|%1s%-4s|%1s%-4s|%1s%-16s|",
        this.originalDepartureTime, "", this.line, "", trainNumber, "", this.destination);

    String track = String.format("%6s|", " ");
    if (getTrack() > -1) {
      track = String.format("%4s%-2s|", "", this.track);
    }
    output += track;
    String delay = " ";
    if (getDelay().isAfter(LocalTime.of(0, 0))) {
      delay += this.delay;
    }
    output += String.format("%12s", delay);
    return output;
  }
}
