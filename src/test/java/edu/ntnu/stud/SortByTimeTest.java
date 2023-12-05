package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.TrainDepartureConstructorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SortByTime-class")
public class SortByTimeTest {
  int trainNumber1;
  TrainDeparture trainDeparture1;
  int trainNumber2;
  TrainDeparture trainDeparture2;
  int trainNumber3;
  TrainDeparture trainDeparture3;
  int trainNumber4;
  TrainDeparture trainDeparture4;
  HashMap<Integer, TrainDeparture> unsortedHashMap;
  HashMap<Integer, TrainDeparture> sortedHashMap;
  @BeforeEach
  void setupSortByTime() throws TrainDepartureConstructorException {
    unsortedHashMap = new HashMap<>();
    trainDeparture1 = new TrainDeparture("12:15",
        "L3", "Oslo", -1);
    trainDeparture2 = new TrainDeparture("15:30",
        "L2", "Hamar", -1);
    trainDeparture3 = new TrainDeparture("10:30",
        "L13", "Oslo", -1);
    trainDeparture4 = new TrainDeparture("10:40",
        "L4", "Lillehammer", -1);
    trainNumber1 = 601;
    trainNumber2 = 305;
    trainNumber3 = 404;
    trainNumber4 = 406;

    unsortedHashMap.put(trainNumber1, trainDeparture1);
    unsortedHashMap.put(trainNumber2, trainDeparture2);
    unsortedHashMap.put(trainNumber3, trainDeparture3);
    unsortedHashMap.put(trainNumber4, trainDeparture4);

    sortedHashMap  = new LinkedHashMap<>();

    sortedHashMap.put(trainNumber3, trainDeparture3);
    sortedHashMap.put(trainNumber4, trainDeparture4);
    sortedHashMap.put(trainNumber1, trainDeparture1);
    sortedHashMap.put(trainNumber2, trainDeparture2);
  }

  @Test
  @DisplayName("Test of sortByTime is the return the same as sorted map")
  void sortByTimeTheSameAsSortedMap() {
    List<TrainDeparture> expectedList = new ArrayList<>(sortedHashMap.values());
    List<TrainDeparture> actualList = new ArrayList<>(SortByTime.sort(unsortedHashMap).values());

    assertEquals(expectedList, actualList);
  }

  @Test
  @DisplayName("Test of sortByTime returns a different hashmap than original")
  void sortByTimeReturnsDifferentMap() {
    List<TrainDeparture> originalList = new ArrayList<>(unsortedHashMap.values());
    List<TrainDeparture> outcomeList = new ArrayList<>(SortByTime.sort(unsortedHashMap).values());


    assertNotEquals(originalList, outcomeList);
  }
}
