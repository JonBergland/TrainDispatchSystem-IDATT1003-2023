package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.

  public static void main(String[] args) {
    Table table = new Table();
    //ArrayList<TrainDeparture> table = new ArrayList<>();
    table.getTable().add(new TrainDeparture(LocalTime.of(12, 15), "Linje 1", 601, "Frognerseteren", -1, LocalTime.of(0, 0)));
    table.getTable().add(new TrainDeparture(LocalTime.of(15, 30), "Linje 2", 305, "Sognsvann",-1,  LocalTime.of(0, 0)));
    table.getTable().add(new TrainDeparture(LocalTime.of(10, 30), "Linje 3", 404, "Bergkrystallen", -1, LocalTime.of(0, 0)));

    UserInterface userInterface = new UserInterface(table);
    userInterface.printTrainDeparture();

    //userInterface.addTraindeparture();
    //userInterface.printTrainDeparture();

    //System.out.println(table.getTable());
    //List objectNummer = table.getTable().stream().filter(t -> t.getTrainNumber() == 601).collect(toList());
    //System.out.println(objectNummer.get(0));
    //userInterface.setTrackToTrain();
    //userInterface.printTrainDeparture();

    LocalTime tid = LocalTime.of(12, 60);
    System.out.println(tid);




  }






}
