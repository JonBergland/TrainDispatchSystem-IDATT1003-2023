package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.ClockException;
import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testcases for the Clock-class.
 */
@DisplayName("Clock-class")
public class ClockTest {
  Clock clock;

  @BeforeEach
  void setupClock() {
    clock = new Clock();
  }

  /**
   * Testcases for the get-methods
   */
  @Nested
  @DisplayName("Test of get() method")
  class clockGetMethod {
    @Test
    @DisplayName("Test of getClock")
    void getClock() {
      assertEquals(LocalTime.of(0,0), clock.getClock());
    }
  }

  /**
   * Testcases for the set-method
   */
  @Nested
  @DisplayName("Test of set() method")
  class clockSetMethod {
    @Test
    @DisplayName("Test of setClock when new time is after old time")
    void setClockNewTimeAfterOldTime() throws ClockException {
      LocalTime newTime = LocalTime.of(1,0);
      clock.setClock(newTime.toString());
      assertEquals(newTime, clock.getClock());
    }

    @Test
    @DisplayName("Test of setClock when format is wrong")
    void setClockWrongFormat() {
      String wrongFormat = "1:00";
      assertThrows(ClockException.class, () -> clock.setClock(wrongFormat));
    }

    @Test
    @DisplayName("Test of setClock when new time is before old time")
    void setClockNewTimeBeforeOldTime() throws ClockException {
      clock.setClock("01:00");
      String beforeTime = "00:30";
      assertThrows(ClockException.class, () -> clock.setClock(beforeTime));
    }

    @Test
    @DisplayName("Test of setClock when new time is the same as old time")
    void setClockNewTimeSameAsOldTime() throws ClockException {
      clock.setClock("01:00");
      LocalTime newTime = LocalTime.of(1,0);
      clock.setClock(newTime.toString());
      assertEquals(newTime, clock.getClock());
    }
  }
}
