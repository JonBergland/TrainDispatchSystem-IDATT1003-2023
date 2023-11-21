package edu.ntnu.stud;

//import org.junit.BeforeClass;
import org.junit.jupiter.api.*;


import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@DisplayName("TrainDeparture-entity-class")
public class TestTrainDeparture {
  LocalTime originalDepartureTime;
  String line;
  int trainNumber;
  String destination;
  int track;
  LocalTime delay;
  TrainDeparture trainDeparture;

  @BeforeEach
  void setup() {
    originalDepartureTime = LocalTime.of(8, 30);
    line = "L3";
    trainNumber = 204;
    destination = "Oslo";
    track = 3;
    delay = LocalTime.of(1,0);

    trainDeparture = new TrainDeparture(originalDepartureTime, line,
        destination, track);
  }

  @Nested
  @DisplayName("TrainDeparture-Constructor")
  class trainDepartureConstructor {

    @Test
    @DisplayName("Test of constructor")
    void testConstructor() {
      assertDoesNotThrow(() -> new TrainDeparture(originalDepartureTime,
          line, destination, track));
    }

    @Test
    @DisplayName("Test of constructor when line empty")
    void testLineWhenEmpty() {
      line = "";
      assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(originalDepartureTime,
          line, destination, track));
    }

    @Test
    @DisplayName("Test of constructor when destination is empty")
    void destination_when_empty() {
      destination = "";
      assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(originalDepartureTime,
          line, destination, track));
    }

    @Test
    @DisplayName("Test of constructor when track is 0 or less")
    void trackWhen0OrLess() {
      track = -4;
      assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(originalDepartureTime,
          line, destination, track));
    }
  }

  @Nested
  @DisplayName("get() methods")
  class trainDepartureGetMethods {
    @Test
    @DisplayName("Test of get OriginalDepartureTime")
    void test_getOriginalDepartureTime() {
      assertEquals(originalDepartureTime, trainDeparture.getOriginalDepartureTime());
    }

    @Test
    @DisplayName("Test of get line")
    void test_getLine() {
      assertEquals(line, trainDeparture.getLine());
    }

    @Test
    @DisplayName("Test of get destination")
    void test_getDestination() {
      assertEquals(destination, trainDeparture.getDestination());
    }

    @Test
    @DisplayName("Test of get track")
    void test_getTrack() {
      assertEquals(track, trainDeparture.getTrack());
    }

    @Test
    @DisplayName("Test of get delay")
    void test_getDelay() {
      assertEquals(LocalTime.of(0, 0), trainDeparture.getDelay());
    }

    @Test
    @DisplayName("Test of get departure time")
    void test_getDepartureTime() {
      assertEquals(originalDepartureTime, trainDeparture.getDepartureTime());
    }
  }

  @Nested
  @DisplayName("set() methods")
  class trainDepartureSetMethods {
    @Test
    @DisplayName("Test of set track")
    void test_setTrack() {
      track = 1;
      trainDeparture.setTrack(track);
      assertEquals(track, trainDeparture.getTrack());
    }

    @Test
    @DisplayName("Test of set delay")
    void test_setDelay() {
      trainDeparture.setDelay(delay);
      assertEquals(delay, trainDeparture.getDelay());
    }
  }

  @Nested
  @DisplayName("toString()")
  class trainDepartureToString {
    private String toStringSetup() {
      return String.format("%" + -6 + "s", originalDepartureTime) + "|"
          + String.format("%" + 1 + "s", "") + String.format("%" + -4 + "s", line) + "|"
          + String.format("%" + 1 + "s", "") + String.format("%" + -4 + "s", trainNumber) + "|"
          + String.format("%" + 1 + "s", "") + String.format("%" + -16 + "s", destination) + "|";
    }

    @Test
    @DisplayName("Test of ToString without track and delay")
    void testToStringWithoutTrackAndDelay() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 6 + "s", " ") + "|"
          + String.format("%" + 12 + "s", " ");
      track = -1;
      trainDeparture.setTrack(-1);
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }

    @Test
    @DisplayName("Test of ToString without delay")
    void testToStringWithoutDelay() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 4 + "s", "") + String.format("%" + -2 + "s", track) + "|"
          + String.format("%" + 12 + "s", " ");
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }

    @Test
    @DisplayName("Test of ToString without track")
    void testToStringWithoutTrack() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 6 + "s", " ") + "|"
          + String.format("%" + 12 + "s", delay);
      trainDeparture.setDelay(delay);
      trainDeparture.setTrack(-1);
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }

    @Test
    @DisplayName("Test of ToString")
    void testToString() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 4 + "s", "") + String.format("%" + -2 + "s", track) + "|"
          + String.format("%" + 12 + "s", delay);
      trainDeparture.setDelay(delay);
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }
  }
}
