package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.TrainDepartureConstructorException;
import edu.ntnu.stud.sort.Sort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testcases for the Sort-classes.
 * <p>
 * The cases test the SortByDestination, SortByLine, SortByTime and SortByTrack
 * </p>
 */
@DisplayName("Sorts-class")
public class SortsTest {
  int trainNumber1;
  TrainDeparture trainDeparture1;
  int trainNumber2;
  TrainDeparture trainDeparture2;
  int trainNumber3;
  TrainDeparture trainDeparture3;
  HashMap<Integer, TrainDeparture> unsortedHashMap;
  HashMap<Integer, TrainDeparture> sortedHashMap;
  @BeforeEach
  void setupSort() throws TrainDepartureConstructorException {
    unsortedHashMap = new HashMap<>();
    trainDeparture1 = new TrainDeparture("12:15",
        "L3", "Oslo", 3);
    trainDeparture2 = new TrainDeparture("15:30",
        "L2", "Hamar", 1);
    trainDeparture3 = new TrainDeparture("10:30",
        "L13", "Arendal", -1);
    trainNumber1 = 601;
    trainNumber2 = 305;
    trainNumber3 = 404;

    unsortedHashMap.put(trainNumber1, trainDeparture1);
    unsortedHashMap.put(trainNumber2, trainDeparture2);
    unsortedHashMap.put(trainNumber3, trainDeparture3);

    sortedHashMap = new LinkedHashMap<>();
  }

  @Test
  @DisplayName("Test of sortByTime is the return the same as sorted map")
  void sortByTimeTheSameAsSortedMap() {
    sortedHashMap.put(trainNumber3, trainDeparture3);
    sortedHashMap.put(trainNumber1, trainDeparture1);
    sortedHashMap.put(trainNumber2, trainDeparture2);
    List<TrainDeparture> expectedList = new ArrayList<>(sortedHashMap.values());
    List<TrainDeparture> actualList = new ArrayList<>(Sort.sortByTime(unsortedHashMap).values());

    assertEquals(expectedList, actualList);
  }

  @Test
  @DisplayName("Test of sortByTime returns a different hashmap than original")
  void sortByTimeReturnsDifferentMap() {
    sortedHashMap.put(trainNumber3, trainDeparture3);
    sortedHashMap.put(trainNumber1, trainDeparture1);
    sortedHashMap.put(trainNumber2, trainDeparture2);
    List<TrainDeparture> originalList = new ArrayList<>(unsortedHashMap.values());
    List<TrainDeparture> outcomeList = new ArrayList<>(Sort.sortByTime(unsortedHashMap).values());

    assertNotEquals(originalList, outcomeList);
  }

  @Test
  @DisplayName("Test of sortByDestination is the return the same as sorted map")
  void sortByDestinationTheSameAsSortedMap() {
    sortedHashMap.put(trainNumber3, trainDeparture3);
    sortedHashMap.put(trainNumber2, trainDeparture2);
    sortedHashMap.put(trainNumber1, trainDeparture1);
    List<TrainDeparture> expectedList = new ArrayList<>(sortedHashMap.values());
    List<TrainDeparture> actualList = new ArrayList<>(Sort.sortByDestination(unsortedHashMap).values());

    assertEquals(expectedList, actualList);
  }

  @Test
  @DisplayName("Test of sortByDestination returns a different hashmap than original")
  void sortByDestinationReturnsDifferentMap() {
    sortedHashMap.put(trainNumber3, trainDeparture3);
    sortedHashMap.put(trainNumber2, trainDeparture2);
    sortedHashMap.put(trainNumber1, trainDeparture1);
    List<TrainDeparture> originalList = new ArrayList<>(unsortedHashMap.values());
    List<TrainDeparture> outcomeList = new ArrayList<>(Sort.sortByDestination(unsortedHashMap).values());

    assertNotEquals(originalList, outcomeList);
  }

  @Test
  @DisplayName("Test of sortByTrack is the return the same as sorted map")
  void sortByTrackTheSameAsSortedMap() {
    sortedHashMap.put(trainNumber3, trainDeparture3);
    sortedHashMap.put(trainNumber2, trainDeparture2);
    sortedHashMap.put(trainNumber1, trainDeparture1);
    List<TrainDeparture> expectedList = new ArrayList<>(sortedHashMap.values());
    List<TrainDeparture> actualList = new ArrayList<>(Sort.sortByTrack(unsortedHashMap).values());

    assertEquals(expectedList, actualList);
  }

  @Test
  @DisplayName("Test of sortByTrack returns a different hashmap than original")
  void sortByTrackReturnsDifferentMap() {
    sortedHashMap.put(trainNumber3, trainDeparture3);
    sortedHashMap.put(trainNumber2, trainDeparture2);
    sortedHashMap.put(trainNumber1, trainDeparture1);
    List<TrainDeparture> originalList = new ArrayList<>(unsortedHashMap.values());
    List<TrainDeparture> outcomeList = new ArrayList<>(Sort.sortByTrack(unsortedHashMap).values());

    assertNotEquals(originalList, outcomeList);
  }
}
