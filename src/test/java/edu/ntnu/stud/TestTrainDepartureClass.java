package edu.ntnu.stud;

//import org.junit.BeforeClass;
import org.junit.jupiter.api.*;


import java.time.LocalTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("TrainDeparture-entity-class")
public class TestTrainDepartureClass {
  LocalTime originalDepartureTime;
  String line;
  int trainNumber;
  String destination;
  int track;
  TrainDeparture trainDeparture;

  @BeforeEach
  void setup() {
    originalDepartureTime = LocalTime.of(8, 30);
    line = "L3";
    trainNumber = 204;
    destination = "Oslo";
    track = 3;
    trainDeparture = new TrainDeparture(originalDepartureTime, line,
        destination, track);
  }

  @Nested
  @DisplayName("TrainDeparture-Constructor")
  class trainDepartureConstructor {
    @Test
    void line_when_empty() {
      line = "";
      assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(originalDepartureTime,
          line, destination, track));
    }

    /*
    @Test
    void trainNumber_when_negative() {
      trainNumber = -201;
      trainDeparture = new TrainDeparture(originalDepartureTime,
          line, destination, track);
      assertEquals(trainDeparture.getTrainNumber(), abs(trainNumber));
    }*/

    @Test
    void destination_when_empty() {
      destination = "";
      assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(originalDepartureTime,
          line, destination, track));
    }

    @Test
    void track_when_less_than_minusOne() {
      track = -4;
      trainDeparture = new TrainDeparture(originalDepartureTime,
          line, destination, track);
      assertEquals(Math.max(track, -1), trainDeparture.getTrack());
    }
  }

  @Nested
  @DisplayName("get() methods")
  class trainDepartureGetMethods {
    @Test
    void test_getOriginalDepartureTime() {
      assertEquals(originalDepartureTime, trainDeparture.getOriginalDepartureTime());
    }

    @Test
    void test_getLine() {
      assertEquals(line, trainDeparture.getLine());
    }
    /*
    @Test
    void test_getTrainNumber() {
      assertEquals(trainDeparture.getTrainNumber(), trainNumber);
    }*/

    @Test
    void test_getDestination() {
      assertEquals(destination, trainDeparture.getDestination());
    }

    @Test
    void test_getTrack() {
      assertEquals(track, trainDeparture.getTrack());
    }

    @Test
    void test_getDelay() {
      assertEquals(LocalTime.of(0, 0), trainDeparture.getDelay());
    }

    @Test
    void test_getDepartureTime() {
      assertEquals(originalDepartureTime, trainDeparture.getDepartureTime());
    }
  }

  @Nested
  @DisplayName("set() methods")
  class trainDepartureSetMethods {
    @Test
    void test_setTrack() {
      track = 1;
      trainDeparture.setTrack(track);
      assertEquals(track, trainDeparture.getTrack());
    }

    @Test
    void test_setDelay() {
      LocalTime delay = LocalTime.of(1, 0);
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

    LocalTime delay = LocalTime.of(1, 0);


    @Test
    void testToStringWithoutTrackAndDelay() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 6 + "s", " ") + "|"
          + String.format("%" + 12 + "s", " ");
      track = -1;
      trainDeparture.setTrack(-1);
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }

    @Test
    void testToStringWithoutDelay() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 4 + "s", "") + String.format("%" + -2 + "s", track) + "|"
          + String.format("%" + 12 + "s", " ");
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }

    @Test
    void testToStringWithoutTrack() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 6 + "s", " ") + "|"
          + String.format("%" + 12 + "s", delay);
      trainDeparture.setDelay(delay);
      trainDeparture.setTrack(-1);
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }

    @Test
    void testToString() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 4 + "s", "") + String.format("%" + -2 + "s", track) + "|"
          + String.format("%" + 12 + "s", delay);
      trainDeparture.setDelay(delay);
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }
  }
}
