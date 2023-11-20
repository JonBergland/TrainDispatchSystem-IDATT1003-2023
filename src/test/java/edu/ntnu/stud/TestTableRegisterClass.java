package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Table_TrainDeparture_Register-class")
public class TestTableRegisterClass {
  Table table = new Table();
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

    table.getHashMap().put(trainNumber1, trainDeparture1);
    table.getHashMap().put(trainNumber2, trainDeparture2);
    table.getHashMap().put(trainNumber3, trainDeparture3);
    table.getHashMap().put(trainNumber4, trainDeparture4);
  }

  @DisplayName("Test of method getTrainByDestination")
  @Test
  void testGetTrainByDestination() {
    String destination = "Oslo";

    HashMap<Integer, TrainDeparture> destinationList = new HashMap<>();
    destinationList.put(trainNumber1, trainDeparture1);
    destinationList.put(trainNumber3, trainDeparture3);

    assertEquals(destinationList, table.getTrainByDestination(destination));

  }
}

