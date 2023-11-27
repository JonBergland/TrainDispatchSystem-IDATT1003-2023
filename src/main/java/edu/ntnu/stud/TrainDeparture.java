package edu.ntnu.stud;

import edu.ntnu.stud.Exceptions.TrackException;
import edu.ntnu.stud.Exceptions.TrainDepartureConstructorException;
import edu.ntnu.stud.Verification.Verification;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Dette er entitetsklassen for en tog-avgang.
 */
public final class TrainDeparture {

  /**
   * Objektsvariabler.
   * Lager en variabel til hver av atributtene til objektet
   */

  private final LocalTime originalDepartureTime;
  private final String line;
  private final String destination;
  private int track;
  private LocalTime delay;

  /**
   * The TrainDeparture-class constructor.
   *
   * @param originalDepartureTime Original time for departure
   * @param line                  The name of the line
   * @param destination           The trains destination
   * @param track                 The track that the train arrives at
   */
  public TrainDeparture(String originalDepartureTime, String line, String destination,
                        int track) throws TrainDepartureConstructorException {
    if (line.isEmpty()) {
      throw new TrainDepartureConstructorException("Line is empty");
    }
    if (destination.isEmpty()) {
      throw new TrainDepartureConstructorException("Destinasjonen er ikke oppgitt");
    }
    try {
      this.originalDepartureTime =
          LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern(originalDepartureTime)));
      Verification.requireNonZeroNonLessThanMinus1Integer(track);
    } catch (DateTimeException | IllegalArgumentException e) {
      throw new TrainDepartureConstructorException("The original departuretime format was not valid");
    }
    this.line = line;
    this.destination = destination;
    this.track = track;
    this.delay = LocalTime.of(0, 0); //initialize the delay at 0 hours and 0 minutes
  }

  /**
   * Gets the original departure time of the train.
   *
   * @return originalDepartureTime
   */
  public LocalTime getOriginalDepartureTime() {
    return originalDepartureTime;
  }

  /**
   * Gets the line of the train.
   *
   * @return line
   */
  public String getLine() {
    return line;
  }

  /**
   * Gets the destination for the train departure.
   *
   * @return destination
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Gets the designated track for the train departure.
   *
   * @return track
   */
  public int getTrack() {
    return track;
  }

  /**
   * Gets the delay for the train departure.
   *
   * @return delay
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * Gets the departure time (orginial + delay) for the train departure.
   *
   * @return departureTime
   */
  public LocalTime getDepartureTime() {
    LocalTime departureTime = this.originalDepartureTime.plusHours(this.delay.getHour());
    departureTime = departureTime.plusMinutes(this.delay.getMinute());
    return departureTime;
  }

  /**
   * Sets a track for the train departure using the integer parameter.
   *
   * @param track                   sets track as the integer parameter
   */
  public boolean setTrack(int track) throws TrackException {
    boolean trackSet = false;
    try {
      Verification.requireNonZeroNonLessThanMinus1Integer(track);
      this.track = track;
      trackSet = true;
    } catch (IllegalArgumentException e) {
      throw new TrackException("Track was 0 or less than -1");
    }
    return trackSet;
  }

  /**
   * Sets a delay for the train departure using the LocalTime parameter.
   *
   * @param delay                   sets delay as the LocalTime parameter
   */
  public void setDelay(LocalTime delay) {
    this.delay = this.delay.plusHours(delay.getHour());
    this.delay = this.delay.plusMinutes(delay.getMinute());
  }

  /**
   * Turn all the information in the TrainDeparture object to a String.
   *
   * @return String
   */
  //@Override
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
