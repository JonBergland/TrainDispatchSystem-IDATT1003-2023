package edu.ntnu.stud;

import static java.lang.Math.abs;

import java.time.LocalTime;


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
  //private final int trainNumber;
  private final String destination;
  private int track;
  private LocalTime delay;

  /**
   * The TrainDeparture-class constructor.
   *
   * @param originalDepartureTime Original time for departure
   * @param line                  The name of the line
   * //@param trainNumber           A number that is unique to each Train
   * @param destination           The trains destination
   * @param track                 The track that the train arrives at
   */
  public TrainDeparture(LocalTime originalDepartureTime, String line, String destination, int track) {
    //tester om tiden er innenfor riktig rekkevidde i input-klassen
    this.originalDepartureTime = originalDepartureTime;
    if (line.isEmpty()) { //setter inn dummy-verdi hvis strengen er tom
      throw new IllegalArgumentException("Linjen er ikke oppgitt");
    } else {
      this.line = line;
    }
    //this.trainNumber = abs(trainNumber); //tar absolutt verdien av konstruktør verdien

    if (destination.isEmpty()) { //setter inn dummy-verdi hvis strengen er tom
      throw new IllegalArgumentException("Destinasjonen er ikke oppgitt");
    } else {
      this.destination = destination;
    }

    //bruker Math.max til å sjekke om input er større enn -1. Hvis ikke så blir track -1
    this.track = Math.max(track, -1);

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
   * Gets the train number for the train departure.
   *
   * @return trainNumber
   */
  //public int getTrainNumber() {
    //return trainNumber;
  //}

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

  /**
   * Sets a track for the train departure using the integer parameter.
   *
   * @param track                   sets track as the integer parameter
   */
  public void setTrack(int track) {
    this.track = track;
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
}
