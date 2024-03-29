package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.DelayException;
import edu.ntnu.stud.exceptions.TableException;
import edu.ntnu.stud.exceptions.TrackException;
import edu.ntnu.stud.exceptions.TrainDepartureConstructorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testcases for the table-class.
 * <p>
 *   The cases test the table-constructors, the get-methods, the set-methods,
 *   the add-method and the remove-methods
 * </p>
 */
@DisplayName("Table-class")
public class TableTest {
  Table tableTest = new Table();
  int trainNumber1;
  TrainDeparture trainDeparture1;
  int trainNumber2;
  TrainDeparture trainDeparture2;
  int trainNumber3;
  TrainDeparture trainDeparture3;
  int trainNumber4;
  TrainDeparture trainDeparture4;

  @BeforeEach
  void setupTable() throws TrainDepartureConstructorException, IllegalArgumentException {
    trainNumber1 = 601;
    trainDeparture1 = new TrainDeparture("12:15", "L3", "Oslo", -1);
    trainNumber2 = 305;
    trainDeparture2 = new TrainDeparture("15:30", "L2", "Hamar", -1);
    trainNumber3 = 404;
    trainDeparture3 = new TrainDeparture("10:30", "L13", "Oslo", -1);
    trainNumber4 = 406;
    trainDeparture4 = new TrainDeparture("10:40", "L4", "Lillehammer", -1);

    tableTest.getHashMap().put(trainNumber1, trainDeparture1);
    tableTest.getHashMap().put(trainNumber2, trainDeparture2);
    tableTest.getHashMap().put(trainNumber3, trainDeparture3);
    tableTest.getHashMap().put(trainNumber4, trainDeparture4);
  }

  /**
   * Testcases for the table constructor
   */
  @Nested
  @DisplayName("Test of the Table constructor")
  class tableConstructor {
    @Test
    @DisplayName("Test of table constructor with same key before and after")
    void tableConstructorSameKeyBeforeAndAfter() throws TableException {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);

      tableTest = new Table(newHashMap);
      assertEquals(newHashMap.keySet(), tableTest.getHashMap().keySet());
    }

    @Test
    @DisplayName("Test of table constructor with not the same TrainDeparture object")
    void tableConstructorNotTheSameObject() throws TableException {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);

      tableTest = new Table(newHashMap);
      assertNotEquals(newHashMap.get(trainNumber1), tableTest.getHashMap().get(trainNumber1));
    }

    @Test
    @DisplayName("Test of table constructor with null as TrainDeparture object")
    void tableConstructorNullTrainDepartureObject() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, null);

      assertThrows(TableException.class, () -> new Table(newHashMap));
    }
  }

  /**
   * Testcases for the table get-methods
   */
  @Nested
  @DisplayName("Test of get() methods")
  class tableGetMethods {

    @Test
    @DisplayName("Test of getHashMap")
    void getHashMap() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);
      newHashMap.put(trainNumber2, trainDeparture2);
      newHashMap.put(trainNumber3, trainDeparture3);
      newHashMap.put(trainNumber4, trainDeparture4);

      assertEquals(newHashMap, tableTest.getHashMap());
    }

    @Test
    @DisplayName("Test of getTrainByDestination")
    void getTrainByDestination() {
      String destination = "Oslo";
      HashMap<Integer, TrainDeparture> destinationList = new HashMap<>();
      destinationList.put(trainNumber1, trainDeparture1);
      destinationList.put(trainNumber3, trainDeparture3);

      assertEquals(destinationList, tableTest.getTrainByDestination(destination));
    }

    @Test
    @DisplayName("Test of getUniqueDestinations")
    void getUniqueDestinations() {
      HashSet<String> uniqueDestinations = new HashSet<>();
      uniqueDestinations.add("Oslo");
      uniqueDestinations.add("Hamar");
      uniqueDestinations.add("Lillehammer");

      assertEquals(uniqueDestinations, tableTest.getUniqueDestinationSet());
    }

    @Test
    @DisplayName("Test of getTrainNumberList")
    void getTrainNumberList() {
      HashSet<Integer> trainNumberHashSet = new HashSet<>(tableTest.getHashMap().keySet());
      assertEquals(trainNumberHashSet, tableTest.getTrainNumberSet());
    }

    @Test
    @DisplayName("Test of getTrainByTrainNumber")
    void getTrainByTrainNumber() throws TrainDepartureConstructorException {
      assertEquals(trainDeparture1.toString(trainNumber1),
          tableTest.getTrainByTrainNumber(trainNumber1).toString(trainNumber1));
    }
  }

  /**
   * Testcases for the add-method constructor
   */
  @Nested
  @DisplayName("Test of add()")
  class tableAddMethod {
    @Test
    @DisplayName("Test of add() with postive trainNumber")
    void addWithPositiveTrainNumber() throws TrainDepartureConstructorException, TableException {
      int trainNumber = 501;
      TrainDeparture trainDeparture = new TrainDeparture("14:30", "L5",
          "Skien", -1);

      tableTest.add(trainNumber, trainDeparture);
      assertEquals(trainDeparture.toString(trainNumber),
          tableTest.getHashMap().get(trainNumber).toString(trainNumber));
    }

    @Test
    @DisplayName("Test of add() with negative trainNumber")
    void addWithNegativeTrainNumber() throws TrainDepartureConstructorException {
      int trainNumber = -501;
      TrainDeparture trainDeparture = new TrainDeparture("14:30", "L5",
          "Skien", -1);

      assertThrows(TableException.class, () ->
          tableTest.add(trainNumber, trainDeparture));
    }

    @Test
    @DisplayName("Test of add() with 0 as trainNumber")
    void addWithZeroAsTrainNumber() throws TrainDepartureConstructorException {
      int trainNumber = 0;
      TrainDeparture trainDeparture = new TrainDeparture("14:30", "L5",
          "Skien", -1);

      assertThrows(TableException.class, () ->
          tableTest.add(trainNumber, trainDeparture));
    }

    @Test
    @DisplayName("Test of add() with already existing trainNumber")
    void addWithAlreadyExistingTrainNumber() throws TrainDepartureConstructorException {
      TrainDeparture trainDeparture = new TrainDeparture("14:30", "L5",
          "Skien", -1);

      assertThrows(TableException.class, () ->
          tableTest.add(trainNumber1, trainDeparture));
    }
  }

  /**
   * Testcases for the set-method constructor
   */
  @Nested
  @DisplayName("Test of set() methods")
  class setMethods {
    @Test
    @DisplayName("Test of positive Integer setTrackToTrain")
    void positiveIntegerSetTrackToTrain() throws TrackException {
      int newTrack = 2;
      assertTrue(tableTest.setTrackToTrain(trainNumber2, newTrack));
    }

    @Test
    @DisplayName("Test of negative, non minus 1 Integer setTrackToTrain")
    void negativeNonMinus1IntegerSetTrackToTrain() {
      int newTrack = -5;
      assertThrows(TrackException.class, () -> tableTest.setTrackToTrain(trainNumber2, newTrack));
    }

    @Test
    @DisplayName("Test of zero integer setTrackToTrain")
    void zeroIntegerSetTrackToTrain() {
      int newTrack = 0;
      assertThrows(TrackException.class, () -> tableTest.setTrackToTrain(trainNumber2, newTrack));
    }

    @Test
    @DisplayName("Test of invalid trainNumber setTrackToTrain")
    void invalidTrainNumberSetTrackToTrain() {
      int newTrack = 1;
      int invalidTrainNumber = 123;
      assertThrows(TrackException.class, () -> tableTest.setTrackToTrain(invalidTrainNumber,newTrack));
    }

    @Test
    @DisplayName("Test of setDelayToTrain with correct trainNumber")
    void setDelayToTrainCorrectTrainNumber() throws DelayException {
      int delay = 60;
      tableTest.setDelayToTrain(trainNumber3, delay);

      assertEquals(LocalTime.of(1,0), trainDeparture3.getDelay());
    }

    @Test
    @DisplayName("Test of setDelayToTrain with incorrect trainNumber")
    void setDelayToTrainIncorrectTrainNumber() {
      int delay = 60;
      int incorrectTrainNumber = 111;

      assertThrows(DelayException.class, () ->
          tableTest.setDelayToTrain(incorrectTrainNumber, delay));
    }
  }

  /**
   * Testcases for the remove-method constructor
   */
  @Nested
  @DisplayName("Test of remove methods")
  class removeMethods {
    @Test
    @DisplayName("Test of removeTrainDepartureBeforeTime with no effect")
    void removeTrainDepartureBeforeTimeWithNoEffect() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>(tableTest.getHashMap());
      LocalTime time = LocalTime.of(10, 0);
      tableTest.removeTrainDepartureBeforeTime(time);
      assertEquals(newHashMap, tableTest.getHashMap());
    }

    @Test
    @DisplayName("Test of removeTrainDepartureBeforeTime")
    void removeTrainDepartureBeforeTime() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);
      newHashMap.put(trainNumber2, trainDeparture2);

      LocalTime time = LocalTime.of(12, 0);
      tableTest.removeTrainDepartureBeforeTime(time);
      assertEquals(newHashMap, tableTest.getHashMap());
    }

    @Test
    @DisplayName("Test of removeTrainDepartureBeforeTime with time sett as a departure time")
    void removeTrainDepartureBeforeTimeWithTimeAsDepartureTime() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);
      newHashMap.put(trainNumber2, trainDeparture2);

      LocalTime time = LocalTime.of(12, 15);
      tableTest.removeTrainDepartureBeforeTime(time);
      assertEquals(newHashMap, tableTest.getHashMap());
    }

    @Test
    @DisplayName("Test of remove with existing trainNumber")
    void removeTrainDepartureWithExistingTrainNumber() throws TableException {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);
      newHashMap.put(trainNumber2, trainDeparture2);

      tableTest.removeTrainByTrainNumber(trainNumber3);
      tableTest.removeTrainByTrainNumber(trainNumber4);
      assertEquals(newHashMap, tableTest.getHashMap());
    }

    @Test
    @DisplayName("Test of remove with non existing trainNumber")
    void removeTrainDepartureWithNonExistingTrainNumber() {
      assertThrows(TableException.class, () -> tableTest.removeTrainByTrainNumber(123));
    }
  }
}

