package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  /**
   * @param args
   */
  // TODO: Fill in the main method and any other methods you need.
  public static void main(String[] args) {
    start(); //starter programmet
  }

  //en metode som lager en objekt av klassen UserInterface og bruker init-metoden til å kjøre programet
  public static void start() {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
  }
}





