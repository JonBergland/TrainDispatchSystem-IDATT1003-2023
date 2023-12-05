package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.DelayException;
import edu.ntnu.stud.exceptions.TableAddException;
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

@DisplayName("Table-class")
public class TestTable {
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
  void setupTable() throws TrainDepartureConstructorException, IllegalArgumentException, TrackException {
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

  @Nested
  @DisplayName("Test of get() methods")
  class tableGetMethods {

    @Test
    @DisplayName("Test of getHashMap")
    void testGetHashMap() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);
      newHashMap.put(trainNumber2, trainDeparture2);
      newHashMap.put(trainNumber3, trainDeparture3);
      newHashMap.put(trainNumber4, trainDeparture4);

      assertEquals(newHashMap, tableTest.getHashMap());
    }

    @Test
    @DisplayName("Test of getTrainByDestination")
    void testGetTrainByDestination() {
      String destination = "Oslo";
      HashMap<Integer, TrainDeparture> destinationList = new HashMap<>();
      destinationList.put(trainNumber1, trainDeparture1);
      destinationList.put(trainNumber3, trainDeparture3);

      assertEquals(destinationList, tableTest.getTrainByDestination(destination));
    }

    @Test
    @DisplayName("Test of getUniqueDestinations")
    void testGetUniqueDestinations() {
      HashSet<String> uniqueDestinations = new HashSet<>();
      uniqueDestinations.add("Oslo");
      uniqueDestinations.add("Hamar");
      uniqueDestinations.add("Lillehammer");

      assertEquals(uniqueDestinations, tableTest.getUniqueDestinationList());
    }

    @Test
    @DisplayName("Test of getTrainNumberList")
    void testGetTrainNumberList() {
      HashSet<Integer> trainNumberHashSet = new HashSet<>(tableTest.getHashMap().keySet());
      assertEquals(trainNumberHashSet, tableTest.getTrainNumberList());
    }

    @Test
    @DisplayName("Test of getTrainByTrainNumber")
    void testGetTrainByTrainNumber() {
      assertEquals(trainDeparture1.toString(trainNumber1),
          tableTest.getTrainByTrainNumber(trainNumber1).toString(trainNumber1));
    }
  }

  @Nested
  @DisplayName("Test of add()")
  class tableAddMethod {
    @Test
    @DisplayName("Test of add() with postive trainNumber")
    void testAddWithPositiveTrainNumber() throws TrainDepartureConstructorException, TableAddException, TrackException {
      int trainNumber = 501;
      TrainDeparture trainDeparture = new TrainDeparture("14:30", "L5",
          "Skien", -1);

      tableTest.add(trainNumber, trainDeparture);
      TrainDeparture trainDepartureTable = tableTest.getTrainByTrainNumber(trainNumber);
      String trainDepartureString1 = trainDeparture.getOriginalDepartureTime() + trainDeparture.getLine() +
          trainDeparture.getDestination() + trainDeparture.getTrack();
      String trainDepartureString2 = trainDepartureTable.getOriginalDepartureTime() +
          trainDepartureTable.getLine() + trainDepartureTable.getDestination()
          + trainDepartureTable.getTrack();
      assertEquals(trainDepartureString1, trainDepartureString2);
    }

    @Test
    @DisplayName("Test of add() with negative trainNumber")
    void testAddWithNegativeTrainNumber() throws TrainDepartureConstructorException, TrackException {
      int trainNumber = -501;
      TrainDeparture trainDeparture = new TrainDeparture("14:30", "L5",
          "Skien", -1);

      assertThrows(TableAddException.class, () ->
          tableTest.add(trainNumber, trainDeparture));
    }

    @Test
    @DisplayName("Test of add() with 0 as trainNumber")
    void addWithZeroAsTrainNumber() throws TrainDepartureConstructorException, TrackException {
      int trainNumber = 0;
      TrainDeparture trainDeparture = new TrainDeparture("14:30", "L5",
          "Skien", -1);

      assertThrows(TableAddException.class, () ->
          tableTest.add(trainNumber, trainDeparture));
    }

    @Test
    @DisplayName("Test of add() with already existing trainNumber")
    void testAddWithAlreadyExistingTrainNumber() throws TrainDepartureConstructorException, TrackException {
      TrainDeparture trainDeparture = new TrainDeparture("14:30", "L5",
          "Skien", -1);

      assertThrows(TableAddException.class, () ->
          tableTest.add(trainNumber1, trainDeparture));
    }
  }

  @Nested
  @DisplayName("Test of set() methods")
  class setMethods {
    @Test
    @DisplayName("Test of setHashMap with same key before and after")
    void testOfSetHashMapSameKeyBeforeAndAfter() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);

      tableTest.setHashMap(newHashMap);

      assertEquals(newHashMap.keySet(), tableTest.getHashMap().keySet());
    }

    @Test
    @DisplayName("Test of setHashMap with not the same TrainDeparture object")
    void testOfSetHashMapNotTheSameObject() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);

      tableTest.setHashMap(newHashMap);

      assertNotEquals(newHashMap.get(trainNumber1), tableTest.getHashMap().get(trainNumber1));
    }

    @Test
    @DisplayName("Test of positive Integer setTrackToTrain")
    void positiveIntegerSetTrackToTrain() throws TrackException {
      int newTrack = 2;
      assertTrue(tableTest.setTrackToTrain(trainNumber2, newTrack));
    }

    @Test
    @DisplayName("Test of negative, non minus 1 Integer setTrackToTrain")
    void negativeNonMinus1IntegerSetTrackToTrain() throws TrackException {
      int newTrack = -5;
      tableTest.setTrackToTrain(trainNumber2, newTrack);
      assertEquals(-1, tableTest.getTrainByTrainNumber(trainNumber2).getTrack());
    }

    @Test
    @DisplayName("Test of zero integer setTrackToTrain")
    void zeroIntegerSetTrackToTrain() throws TrackException {
      int newTrack = 0;
      tableTest.setTrackToTrain(trainNumber2, newTrack);
      assertEquals(-1, tableTest.getTrainByTrainNumber(trainNumber2).getTrack());
    }

    @Test
    @DisplayName("Test of setDelayToTrain with correct trainNumber")
    void testSetDelayToTrainCorrectTrainNumber() throws DelayException {
      String delay = "01:00";
      tableTest.setDelayToTrain(trainNumber3, delay);

      assertEquals(LocalTime.of(1,0), trainDeparture3.getDelay());
    }

    @Test
    @DisplayName("Test of setDelayToTrain with incorrect trainNumber")
    void testSetDelayToTrainIncorrectTrainNumber() throws DelayException {
      String delay = "01:00";
      int incorrectTrainNumber = 111;

      assertThrows(DelayException.class, () ->
          tableTest.setDelayToTrain(incorrectTrainNumber, delay));
    }
  }

  @Nested
  @DisplayName("Test of remove method")
  class removeMethods {
    @Test
    @DisplayName("Test of removeTrainDepartureBeforeTime with no effect")
    void testRemoveTrainDepartureBeforeTimeWithNoEffect() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>(tableTest.getHashMap());
      LocalTime time = LocalTime.of(10, 0);
      tableTest.removeTrainDepartureBeforeTime(time);
      assertEquals(newHashMap, tableTest.getHashMap());
    }

    @Test
    @DisplayName("Test of removeTrainDepartureBeforeTime")
    void testRemoveTrainDepartureBeforeTime() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);
      newHashMap.put(trainNumber2, trainDeparture2);

      LocalTime time = LocalTime.of(12, 0);
      tableTest.removeTrainDepartureBeforeTime(time);
      assertEquals(newHashMap, tableTest.getHashMap());
    }

    @Test
    @DisplayName("Test of removeTrainDepartureBeforeTime with time sett as a departure time")
    void testRemoveTrainDepartureBeforeTimeWithTimeAsDepartureTime() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);
      newHashMap.put(trainNumber2, trainDeparture2);

      LocalTime time = LocalTime.of(12, 15);
      tableTest.removeTrainDepartureBeforeTime(time);
      assertEquals(newHashMap, tableTest.getHashMap());
    }
  }
}

