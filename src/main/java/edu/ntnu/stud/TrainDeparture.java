package edu.ntnu.stud;

import java.time.LocalTime;

import static java.lang.Math.abs;

/**
 * Dette er entitetsklassen for en tog-avgang
 */
public final class TrainDeparture {

  /**
   * Objektsvariabler
   * Lager en variabel til hver av atributtene til objektet
   */

  private final LocalTime originalDepartureTime;
  private final String line;
  private final int trainNumber;
  private final String destination;
  private int track;
  private LocalTime delay;

  /**
   * @param OriginalDepartureTime      Original time for departure
   * @param line                       The name of the line
   * @param trainNumber                A number that is unique to each Train
   * @param destination                The trains destination
   * @param track                      The track that the train arrives on
   * delay                             Eventual delay, sat to 0 on initializing
   */
  public TrainDeparture(LocalTime OriginalDepartureTime, String line, int trainNumber, String destination, int track) {
    //tester om tiden er innenfor riktig rekkevidde i input-klassen
    this.originalDepartureTime = OriginalDepartureTime;
    if (line.isEmpty()) { //setter inn dummy-verdi hvis strengen er tom
      this.line = "NULL";
    } else {
      this.line = line;
    }
    /**
     * Endre til at alle thrower feilmeldinger
     */

    this.trainNumber = abs(trainNumber); //tar absolutt verdien av konstruktør verdien

    if (destination.isEmpty()) { //setter inn dummy-verdi hvis strengen er tom
      this.destination = "NULL";
    } else {
      this.destination = destination;
    }

    //bruker Math.max til å sjekke om input er større enn -1. Hvis ikke så blir track -1
    this.track = Math.max(track, -1);

    this.delay = LocalTime.of(0, 0); //initialize the delay at 0 hours and 0 minutes
  }


  /**
   * Objekstmetoder
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  public int getTrack() {
    return track;
  }

  public LocalTime getDelay() {
    return delay;
  }

  public LocalTime getOriginalDepartureTime() {
    return originalDepartureTime;
  }

  public String getDestination() {
    return destination;
  }

  public String getLine() {
    return line;
  }

  public LocalTime getDepartureTime() {
    LocalTime departureTime = this.originalDepartureTime.plusHours(this.delay.getHour());
    departureTime = departureTime.plusMinutes(this.delay.getMinute());
    return departureTime;
  }

  public String toStrin() {
    String output = getOriginalDepartureTime() + " " + getLine() + " " + getTrainNumber() + " " + getDestination();
    if (getTrack() > -1) {
      output += " Spor: " + getTrack();
    }
    if (getDelay().isAfter(LocalTime.of(0, 0))) {
      output += " Forsinkelse: " + getDelay();
    }
    return output;
  }

  public void setDelay(LocalTime delay) {
    this.delay = this.delay.plusHours(delay.getHour());
    this.delay = this.delay.plusMinutes(delay.getMinute());
  }

  public void setTrack(int track) {
    this.track = track;
  }

}
