package edu.ntnu.stud;

//import org.junit.BeforeClass;
import edu.ntnu.stud.Exceptions.TrainDepartureConstructorException;
import org.junit.jupiter.api.*;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@DisplayName("TrainDeparture-entity-class")
public class TestTrainDeparture {
  String originalDepartureTime;
  String line;
  int trainNumber;
  String destination;
  int track;
  LocalTime delay;
  TrainDeparture trainDeparture;

  @BeforeEach
  void setup() throws TrainDepartureConstructorException, IllegalArgumentException {
    originalDepartureTime = "08:30";
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
    @DisplayName("Test of constructor with no exceptions")
    void constructorPositiveTest() {
      assertDoesNotThrow(() -> new TrainDeparture(originalDepartureTime,
          line, destination, track));
    }

    @Test
    @DisplayName("Test of constructor when line empty")
    void lineWhenEmpty() {
      line = "";
      assertThrows(TrainDepartureConstructorException.class, () -> new TrainDeparture(originalDepartureTime,
          line, destination, track));
    }

    @Test
    @DisplayName("Test of constructor when destination is empty")
    void destinationWhenEmpty() {
      destination = "";
      assertThrows(TrainDepartureConstructorException.class, () -> new TrainDeparture(originalDepartureTime,
          line, destination, track));
    }

    @Test
    @DisplayName("Test of constructor when track is 0 or less")
    void trackWhen0OrLess() {
      track = -4;
      assertThrows(TrainDepartureConstructorException.class, () -> new TrainDeparture(originalDepartureTime,
          line, destination, track));
    }

    @Test
    @DisplayName("Test of constructor when originalDepartureTime is not formatted properly")
    void originalDepartureTimeWhenNotFormattedProperly() {
      originalDepartureTime = "08:9";
      assertThrows(TrainDepartureConstructorException.class, () -> new
          TrainDeparture(originalDepartureTime, line, destination, track));
    }

    @Test
    @DisplayName("Test of constructor when originalDepartureTime has invalid value for minute")
    void originalDepartureTimeWhenInvalidValueForMinute () {
      originalDepartureTime = "08:61";
      assertThrows(TrainDepartureConstructorException.class, () -> new
          TrainDeparture(originalDepartureTime, line, destination, track));
    }

    @Test
    @DisplayName("Test of constructor when originalDepartureTime has invalid value for hour")
    void originalDepartureTimeWhenInvalidValueForHour () {
      originalDepartureTime = "25:00";
      assertThrows(TrainDepartureConstructorException.class, () -> new
          TrainDeparture(originalDepartureTime, line, destination, track));
    }
  }

  @Nested
  @DisplayName("get() methods")
  class trainDepartureGetMethods {
    @Test
    @DisplayName("Test of get OriginalDepartureTime")
    void testGetOriginalDepartureTime() {
      LocalTime originalDepartureTime2 =
          LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern(originalDepartureTime)));
      assertEquals(originalDepartureTime2, trainDeparture.getOriginalDepartureTime());
    }

    @Test
    @DisplayName("Test of get line")
    void testGetLine() {
      assertEquals(line, trainDeparture.getLine());
    }

    @Test
    @DisplayName("Test of get destination")
    void testGetDestination() {
      assertEquals(destination, trainDeparture.getDestination());
    }

    @Test
    @DisplayName("Test of get track")
    void testGetTrack() {
      assertEquals(track, trainDeparture.getTrack());
    }

    @Test
    @DisplayName("Test of get delay")
    void testGetDelay() {
      assertEquals(LocalTime.of(0, 0), trainDeparture.getDelay());
    }

    @Test
    @DisplayName("Test of get departure time")
    void testGetDepartureTime() {
      LocalTime originalDepartureTime3 =
          LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern(originalDepartureTime)));
      assertEquals(originalDepartureTime3, trainDeparture.getDepartureTime());
    }
  }

  @Nested
  @DisplayName("set() methods")
  class trainDepartureSetMethods {
    @Test
    @DisplayName("Test of set track")
    void testSetTrack() {
      track = 1;
      trainDeparture.setTrack(track);
      assertEquals(track, trainDeparture.getTrack());
    }

    @Test
    @DisplayName("Test of set delay")
    void testSetDelay() {
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
    void toStringWithoutTrackAndDelay() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 6 + "s", " ") + "|"
          + String.format("%" + 12 + "s", " ");
      track = -1;
      trainDeparture.setTrack(-1);
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }

    @Test
    @DisplayName("Test of ToString without delay")
    void toStringWithoutDelay() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 4 + "s", "") + String.format("%" + -2 + "s", track) + "|"
          + String.format("%" + 12 + "s", " ");
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }

    @Test
    @DisplayName("Test of ToString without track")
    void toStringWithoutTrack() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 6 + "s", " ") + "|"
          + String.format("%" + 12 + "s", delay);
      trainDeparture.setDelay(delay);
      trainDeparture.setTrack(-1);
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }

    @Test
    @DisplayName("Test of ToString")
    void toStringWithTrackAndDelay() {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 4 + "s", "") + String.format("%" + -2 + "s", track) + "|"
          + String.format("%" + 12 + "s", delay);
      trainDeparture.setDelay(delay);
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }
  }
}
