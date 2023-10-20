package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.*;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.

  public static void main(String[] args) {
    Table table = new Table();
    //ArrayList<TrainDeparture> table = new ArrayList<>();
    table.getTable().add(new TrainDeparture(LocalTime.of(12, 15), "Linje 1", 601, "Frognerseteren", LocalTime.of(0, 0), -1));
    table.getTable().add(new TrainDeparture(LocalTime.of(15, 30), "Linje 2", 305, "Sognsvann", LocalTime.of(0, 0), -1));
    table.getTable().add(new TrainDeparture(LocalTime.of(10, 30), "Linje 3", 404, "Bergkrystallen", LocalTime.of(0, 0), -1));

    UserInterface userInterface = new UserInterface(table.getTable());
    userInterface.printTrainDeparture();


  }






}
