package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.ClockException;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a clock with LocalTime functionality.
 * <p>
 * The {@code Clock} class provides methods for initializing, retrieving,
 * and updating the current time represented by a LocalTime object.
 * It includes functionality to set a new time while ensuring it is valid
 * and not earlier than the current time.
 * </p>
 *
 * @see LocalTime
 * @see DateTimeFormatter
 * @see ClockException
 */
public class Clock {
  private LocalTime clock;

  /**
   * Initialize the Clock object with a LocalTime of 0.
   */
  public Clock() { //the clock-variable gets initialized
    this.clock = LocalTime.of(0, 0);
  }

  /**
   * Gets the LocalTime representing the current time.
   *
   * @return The LocalTime object representing the current time.
   */
  public LocalTime getClock() {
    return clock;
  }

  /**
   * Sets the clock to a new time specified by the given string.
   *
   * @param newTime         The time to which the clock is set, provided as a string.
   * @throws ClockException If the format of the new time is invalid or if the new time
   *                        is before the current time, a ClockException is thrown.
   */
  public void setClock(String newTime) throws ClockException {
    LocalTime time;
    try {
      time =
          LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern(newTime)));
    } catch (DateTimeException e) {
      throw new ClockException("The format on the new time was not valid");
    }
    if (time.isBefore(clock)) {
      throw new ClockException("The new time is before the old time");
    } else {
      this.clock = time;
    }
  }
}
