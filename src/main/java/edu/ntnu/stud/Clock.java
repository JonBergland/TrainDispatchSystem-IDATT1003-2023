package edu.ntnu.stud;

import edu.ntnu.stud.Exceptions.ClockException;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
  public void setClock(String newTime) throws ClockException { //en setmetode som setter parameteren lik klokkeslettet
    LocalTime time = LocalTime.of(0,0);
    try {
      time =
          LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern(newTime)));
    } catch (DateTimeException e) {
      throw new ClockException("The time format was not valid");
    }
    if (time.isBefore(clock)){
      throw new ClockException("The new time is before the old time");
    }
    else {
      this.clock = time;
    }
  }
}
