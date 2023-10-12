package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.

  public static void main(String[] args) {
    trainDeparture[] table = new trainDeparture[2];
    table[0] = new trainDeparture(LocalTime.of(12, 15), "Linje 1", 601, "Frognerseteren", LocalTime.of(0, 0), -1);
    table[1] = new trainDeparture(LocalTime.of(15, 30), "Linje 2", 305, "Sognsvann", LocalTime.of(0, 0), -1);


    for(trainDeparture i : table){
      i.printTrainDeparture();
    }
  }






}
