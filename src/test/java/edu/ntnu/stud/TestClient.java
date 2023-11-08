package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.time.LocalTime;
import java.util.ArrayList;
import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestClient {
  LocalTime originalDepartureTime;
  String line;
  int trainNumber;
  String destination;
  int track;
  TrainDeparture trainDeparture;

  @BeforeEach
  public void beforeEach() {
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
      assert (trainDeparture.getTrainNumber() == abs(trainNumber));
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
      assert (trainDeparture.getTrack() == Math.max(track, -1));
    }
  }

  @Nested
  @DisplayName("get() methods")
  class trainDepartureGetMethods {
    @Test
    void test_getOriginalDepartureTime() {
      assert (trainDeparture.getOriginalDepartureTime().equals(originalDepartureTime));
    }

    @Test
    void test_getLine() {
      assert (trainDeparture.getLine().equals(line));
    }

    @Test
    void test_getTrainNumber() {
      assert (trainDeparture.getTrainNumber() == trainNumber);
    }

    @Test
    void test_getDestination() {
      assert (trainDeparture.getDestination().equals(destination));
    }

    @Test
    void test_getTrack() {
      assert (trainDeparture.getTrack() == track);
    }

    @Test
    void test_getDelay() {
      assert (trainDeparture.getDelay().equals(LocalTime.of(0, 0)));
    }

    @Test
    void test_getDepartureTime() {
      assert (trainDeparture.getDepartureTime().equals(originalDepartureTime));
    }
  }

  @Nested
  @DisplayName("set() methods")
  class trainDepartureSetMethods {
    @Test
    void test_setTrack() {
      track = 1;
      trainDeparture.setTrack(track);
      assert (trainDeparture.getTrack() == track);
    }

    @Test
    void test_setDelay() {
      LocalTime delay = LocalTime.of(1, 0);
      trainDeparture.setDelay(delay);
      assert (trainDeparture.getDelay().equals(delay));
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
      assert (trainDeparture.toString().equals(normalOutput));
    }

    @Test
    void test_toString_without_delay() {
      String normalOutput = originalDepartureTime + " " + line + " " + trainNumber + " " + destination
          + " Spor: " + track;
      assert (trainDeparture.toString().equals(normalOutput));
    }

    @Test
    void test_toString_without_track() {
      String normalOutput = originalDepartureTime + " " + line + " " + trainNumber + " " + destination
          + " Forsinkelse: " + delay;
      trainDeparture.setDelay(delay);
      trainDeparture.setTrack(-1);
      assert (trainDeparture.toString().equals(normalOutput));
    }

    @Test
    void test_toString() {
      String normalOutput = originalDepartureTime + " " + line + " " + trainNumber + " " + destination
          + " Spor: " + track + " Forsinkelse: " + delay;
      trainDeparture.setDelay(delay);
      assert (trainDeparture.toString().equals(normalOutput));
    }
  }
}