package edu.ntnu.stud;

//import org.junit.BeforeClass;
import edu.ntnu.stud.exceptions.DelayException;
import edu.ntnu.stud.exceptions.TrainDepartureConstructorException;
import org.junit.jupiter.api.*;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("TrainDeparture-entity-class")
public class TrainDepartureTest {
  String originalDepartureTime;
  String line;
  int trainNumber;
  String destination;
  int track;
  String delay;
  TrainDeparture trainDeparture;

  @BeforeEach
  void setup() throws TrainDepartureConstructorException, IllegalArgumentException {
    originalDepartureTime = "08:30";
    line = "L3";
    trainNumber = 204;
    destination = "Oslo";
    track = 3;
    delay = "01:00";

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
    @DisplayName("Test of constructor when track is less than -1")
    void trackWhen0OrLessThanMinus1() throws TrainDepartureConstructorException {
      track = -4;
      TrainDeparture newTrainDeparture = new TrainDeparture(originalDepartureTime,
          line, destination, track);
      assertEquals(-1, newTrainDeparture.getTrack());
    }

    @Test
    @DisplayName("Test of constructor when track is 0")
    void trackWhen0() throws TrainDepartureConstructorException {
      track = 0;
      TrainDeparture newTrainDeparture = new TrainDeparture(originalDepartureTime,
          line, destination, track);
      assertEquals(-1, newTrainDeparture.getTrack());
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
    void getOriginalDepartureTime() {
      LocalTime originalDepartureTime2 =
          LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern(originalDepartureTime)));
      assertEquals(originalDepartureTime2, trainDeparture.getOriginalDepartureTime());
    }

    @Test
    @DisplayName("Test of get line")
    void getLine() {
      assertEquals(line, trainDeparture.getLine());
    }

    @Test
    @DisplayName("Test of get destination")
    void getDestination() {
      assertEquals(destination, trainDeparture.getDestination());
    }

    @Test
    @DisplayName("Test of get track")
    void getTrack() {
      assertEquals(track, trainDeparture.getTrack());
    }

    @Test
    @DisplayName("Test of get delay")
    void getDelay() {
      assertEquals(LocalTime.of(0, 0), trainDeparture.getDelay());
    }

    @Test
    @DisplayName("Test of get departure time")
    void getDepartureTime() {
      LocalTime originalDepartureTime3 =
          LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern(originalDepartureTime)));
      assertEquals(originalDepartureTime3, trainDeparture.getDepartureTime());
    }
    @Test
    @DisplayName("Test of get deep copy has the same info as original TrainDeparture")
    void getDeepCopySameAsOriginalTrainDeparture() {
      assertEquals(trainDeparture.toString(trainNumber),
          Objects.requireNonNull(trainDeparture.getDeepCopy()).toString(trainNumber));
    }

    @Test
    @DisplayName("Test of get deep copy is not the same object")
    void getDeepCopyNotTheSameObject() {
      assertNotEquals(trainDeparture, trainDeparture.getDeepCopy());
    }
  }

  @Nested
  @DisplayName("set() methods")
  class trainDepartureSetMethods {
    @Test
    @DisplayName("Test of positive integer set track")
    void positiveIntegerSetTrack() {
      track = 1;
      assertTrue(trainDeparture.setTrack(track));
    }

    @Test
    @DisplayName("Test of zero integer set track")
    void zeroIntegerSetTrack() {
      track = 0;
      trainDeparture.setTrack(track);
      assertEquals(-1, trainDeparture.getTrack());
    }

    @Test
    @DisplayName("Test of less than -1 integer set track")
    void lessThanMinus1IntegerSetTrack() {
      track = -2;
      trainDeparture.setTrack(track);
      assertEquals(-1, trainDeparture.getTrack());
    }

    @Test
    @DisplayName("Test of set delay with correct formatted input")
    void correctFormatSetDelay() throws DelayException {
      trainDeparture.setDelay(delay);
      assertEquals(LocalTime.of(1,0), trainDeparture.getDelay());
    }

    @Test
    @DisplayName("Test of set delay with incorrect formatted input")
    void incorrectFormatSetDelay() {
      String incorrectDelay = "1:00";
      assertThrows(DelayException.class, () ->
          trainDeparture.setDelay(incorrectDelay));
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
    void toStringWithoutTrack() throws DelayException {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 6 + "s", " ") + "|"
          + String.format("%" + 12 + "s", delay);
      trainDeparture.setDelay(delay);
      trainDeparture.setTrack(-1);

      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }

    @Test
    @DisplayName("Test of ToString")
    void toStringWithTrackAndDelay() throws DelayException {
      String normalOutput = toStringSetup();
      normalOutput += String.format("%" + 4 + "s", "") + String.format("%" + -2 + "s", track) + "|"
          + String.format("%" + 12 + "s", delay);
      trainDeparture.setDelay(delay);
      assertEquals(normalOutput, trainDeparture.toString(trainNumber));
    }
  }
}
