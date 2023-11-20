package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
  void setupTable() {
    trainNumber1 = 601;
    trainDeparture1 = new TrainDeparture(LocalTime.of(12, 15), "L3", "Oslo", -1);
    trainNumber2 = 305;
    trainDeparture2 = new TrainDeparture(LocalTime.of(15, 30), "L2", "Hamar", -1);
    trainNumber3 = 404;
    trainDeparture3 = new TrainDeparture(LocalTime.of(10, 30), "L13", "Oslo", -1);
    trainNumber4 = 406;
    trainDeparture4 = new TrainDeparture(LocalTime.of(10, 40), "L4", "Lillehammer", -1);

    tableTest.getHashMap().put(trainNumber1, trainDeparture1);
    tableTest.getHashMap().put(trainNumber2, trainDeparture2);
    tableTest.getHashMap().put(trainNumber3, trainDeparture3);
    tableTest.getHashMap().put(trainNumber4, trainDeparture4);
  }
  @Nested
  @DisplayName("Test of get() methods")
  class tableGetMethods {

    @DisplayName("Test of getHashMap")
    @Test
    void testGetHashMap() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);
      newHashMap.put(trainNumber2, trainDeparture2);
      newHashMap.put(trainNumber3, trainDeparture3);
      newHashMap.put(trainNumber4, trainDeparture4);

      assertEquals(newHashMap, tableTest.getHashMap());
    }

    @DisplayName("Test of getTrainByDestination")
    @Test
    void testGetTrainByDestination() {
      String destination = "Oslo";
      HashMap<Integer, TrainDeparture> destinationList = new HashMap<>();
      destinationList.put(trainNumber1, trainDeparture1);
      destinationList.put(trainNumber3, trainDeparture3);

      assertEquals(destinationList, tableTest.getTrainByDestination(destination));
    }

    @DisplayName("Test of getUniqueDestinations")
    @Test
    void testGetUniqueDestinations() {
      HashSet<String> uniqueDestinations = new HashSet<>();
      uniqueDestinations.add("Oslo");
      uniqueDestinations.add("Hamar");
      uniqueDestinations.add("Lillehammer");

      assertEquals(uniqueDestinations, tableTest.getUniqueDestinationList());
    }
  }

  @Nested
  @DisplayName("Test of add()")
  class tableAddMethod {
    @DisplayName("Test of add() with postive trainNumber")
    @Test
    void testAddWithPositiveTrainNumber() {
      int trainNumber = 501;
      TrainDeparture trainDeparture = new TrainDeparture(LocalTime.of(14, 30), "L5",
          "Skien", -1);

      tableTest.add(trainNumber, trainDeparture);
      assertEquals(trainDeparture, tableTest.getHashMap().get(trainNumber));
    }

    @DisplayName("Test of add() with negative trainNumber")
    @Test
    void testAddWithNegativeTrainNumber() {
      int trainNumber = -501;
      TrainDeparture trainDeparture = new TrainDeparture(LocalTime.of(14, 30), "L5",
          "Skien", -1);

      tableTest.add(trainNumber, trainDeparture);
      assertEquals(trainDeparture, tableTest.getHashMap().get(abs(trainNumber)));
    }

    @DisplayName("Test og add() with already existing trainNumber")
    @Test
    void testAddWithAlreadyExistingTrainNumber() {
      TrainDeparture trainDeparture = new TrainDeparture(LocalTime.of(14, 30), "L5",
          "Skien", -1);

      assertThrows(IllegalArgumentException.class, () ->
          tableTest.add(trainNumber1, trainDeparture));
    }
  }

  @Nested
  @DisplayName("Test of set() methods")
  class setMethods {
    @DisplayName("Test of setHashMap")
    @Test
    void testOfSetHashMap() {
      HashMap<Integer, TrainDeparture> newHashMap = new HashMap<>();
      newHashMap.put(trainNumber1, trainDeparture1);

      tableTest.setHashMap(newHashMap);

      assertEquals(newHashMap, tableTest.getHashMap());
    }
  }
  /*
  @Nested
  @DisplayName("Test of choose() methods")
  class chooseMethods {
    @DisplayName("Test of chooseTrainNumber")
    @Test
    void testOfChooseTrainNumber() {

    }
  }*/
}

