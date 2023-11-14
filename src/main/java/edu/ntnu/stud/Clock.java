package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * Dette er klassen for klokka
 */
public class Clock {
  /**
   * Objekts variabel
   */
  private LocalTime clock;

  /**
   * Initialize the Clock object with a LocalTime of 0
   */
  public Clock() { //når Clock blir initialisert settes den til 00:00
    this.clock = LocalTime.of(0, 0);
  }

  /**
   * @return LocalTime        Gets the current time
   */
  public LocalTime getClock() { //en get-metode for å returnere klokkeslettet
    return clock;
  }

  /**
   * @param time The time the new clock is set to be
   */
  public void setClock(LocalTime time) { //en setmetode som setter parameteren lik klokkeslettet
    if (time.isBefore(clock)){
      throw new IllegalArgumentException("Tiden satt inn er før den nåverende tiden");
    }
    else {
      this.clock = time;
    }
  }
}
