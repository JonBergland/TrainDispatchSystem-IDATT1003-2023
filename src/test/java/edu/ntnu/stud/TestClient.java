package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;


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

  @BeforeEach
  public void setup() {
    originalDepartureTime = LocalTime.of(8, 30);
    line = "L3";
    trainNumber = 204;
    destination = "Oslo";
    track = 3;
    trainDeparture = new TrainDeparture(originalDepartureTime, line, trainNumber,
        destination, track);
  }

  @Nested
  @DisplayName("TrainDeparture-Constructor")
  class traindepartureConstructor {
    @Test
    void line_when_empty() {
      line = "";
      assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(originalDepartureTime,
          line, trainNumber, destination, track));
    }

    @Test
    void trainNumber_when_negative() {
      trainNumber = -201;
      trainDeparture = new TrainDeparture(originalDepartureTime,
          line, trainNumber, destination, track);
      assertEquals(trainDeparture.getTrainNumber(), abs(trainNumber));
    }

    @Test
    void destination_when_empty() {
      destination = "";
      assertThrows(IllegalArgumentException.class, () -> new TrainDeparture(originalDepartureTime,
          line, trainNumber, destination, track));
    }

    @Test
    void track_when_less_than_minusOne() {
      track = -4;
      trainDeparture = new TrainDeparture(originalDepartureTime,
          line, trainNumber, destination, track);
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

    @Test
    void test_getTrainNumber() {
      assertEquals(trainDeparture.getTrainNumber(), trainNumber);
    }

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
    LocalTime delay = LocalTime.of(1, 0);

    @Test
    void test_toString_without_track_and_delay() {
      String normalOutput = originalDepartureTime + " " + line + " " + trainNumber + " " + destination;
      track = -1;
      trainDeparture.setTrack(-1);
      assertEquals(trainDeparture.toString(), normalOutput);
    }

    @Test
    void test_toString_without_delay() {
      String normalOutput = originalDepartureTime + " " + line + " " + trainNumber + " " + destination
          + " Spor: " + track;
      assertEquals(trainDeparture.toString(), normalOutput);
    }

    @Test
    void test_toString_without_track() {
      String normalOutput = originalDepartureTime + " " + line + " " + trainNumber + " " + destination
          + " Forsinkelse: " + delay;
      trainDeparture.setDelay(delay);
      trainDeparture.setTrack(-1);
      assertEquals(trainDeparture.toString(), normalOutput);
    }

    @Test
    void test_toString() {
      String normalOutput = originalDepartureTime + " " + line + " " + trainNumber + " " + destination
          + " Spor: " + track + " Forsinkelse: " + delay;
      trainDeparture.setDelay(delay);
      assertEquals(trainDeparture.toString(), normalOutput);
    }
  }
}