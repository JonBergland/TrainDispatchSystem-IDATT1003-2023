package edu.ntnu.stud;

import org.junit.BeforeClass;
import org.junit.jupiter.api.*;


import java.time.LocalTime;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestClient {
  LocalTime originalDepartureTime;
  String line;
  int trainNumber;
  String destination;
  int track;
  TrainDeparture trainDeparture;

  @Nested
  @DisplayName("TrainDeparture-entity-class")
  class trainDeparture {

    @BeforeEach
    public void setup() {
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
    class traindepartureConstructor {
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
        assertEquals(trainDeparture.getTrack(), Math.max(track, -1));
      }
    }

    @Nested
    @DisplayName("get() methods")
    class trainDepartureGetMethods {
      @Test
      void test_getOriginalDepartureTime() {
        assertEquals(trainDeparture.getOriginalDepartureTime(), originalDepartureTime);
      }

      @Test
      void test_getLine() {
        assertEquals(trainDeparture.getLine(), line);
      }
    /*
    @Test
    void test_getTrainNumber() {
      assertEquals(trainDeparture.getTrainNumber(), trainNumber);
    }*/

      @Test
      void test_getDestination() {
        assertEquals(trainDeparture.getDestination(), destination);
      }

      @Test
      void test_getTrack() {
        assertEquals(trainDeparture.getTrack(), track);
      }

      @Test
      void test_getDelay() {
        assertEquals(trainDeparture.getDelay(), LocalTime.of(0, 0));
      }

      @Test
      void test_getDepartureTime() {
        assertEquals(trainDeparture.getDepartureTime(), originalDepartureTime);
      }
    }

    @Nested
    @DisplayName("set() methods")
    class trainDepartureSetMethods {
      @Test
      void test_setTrack() {
        track = 1;
        trainDeparture.setTrack(track);
        assertEquals(trainDeparture.getTrack(), track);
      }

      @Test
      void test_setDelay() {
        LocalTime delay = LocalTime.of(1, 0);
        trainDeparture.setDelay(delay);
        assertEquals(trainDeparture.getDelay(), delay);
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
      void testToStringWithoutTrack_and_delay() {
        String normalOutput = toStringSetup();
        normalOutput += String.format("%" + 6 + "s", " ") + "|"
            + String.format("%" + 12 + "s", " ");
        track = -1;
        trainDeparture.setTrack(-1);
        assertEquals(trainDeparture.toString(trainNumber), normalOutput);
      }

      @Test
      void test_toString_without_delay() {
        String normalOutput = toStringSetup();
        normalOutput += String.format("%" + 4 + "s", "") + String.format("%" + -2 + "s", track) + "|"
            + String.format("%" + 12 + "s", " ");
        assertEquals(trainDeparture.toString(trainNumber), normalOutput);
      }

      @Test
      void test_toString_without_track() {
        String normalOutput = toStringSetup();
        normalOutput += String.format("%" + 6 + "s", " ") + "|"
            + String.format("%" + 12 + "s", delay);
        trainDeparture.setDelay(delay);
        trainDeparture.setTrack(-1);
        assertEquals(trainDeparture.toString(trainNumber), normalOutput);
      }

      @Test
      void test_toString() {
        String normalOutput = toStringSetup();
        normalOutput += String.format("%" + 4 + "s", "") + String.format("%" + -2 + "s", track) + "|"
            + String.format("%" + 12 + "s", delay);
        trainDeparture.setDelay(delay);
        assertEquals(trainDeparture.toString(trainNumber), normalOutput);
      }
    }
  }

  @Nested
  @DisplayName("Table_TrainDeparture_Registerclass")
  class table {

  }
}