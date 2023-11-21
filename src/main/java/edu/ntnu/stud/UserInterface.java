package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.HashMap;

/**
 * This is the class for interactions with user
 */
public class UserInterface {
  private final Table table = new Table();
  private final Clock clock = new Clock();
  private final Input input = new Input();

  private final int PRINT_TRAINDEPARTURE = 1;
  private final int ADD_TRAINDEPARTURE = 2;
  private final int SET_TRACKTOTRAIN = 3;
  private final int SET_DELAYTOTRAIN = 4;
  private final int FIND_TRAINBYTRAINNUMBER = 5;
  private final int FIND_TRAINBYDESTINATION = 6;
  private final int UPDATE_CLOCK = 7;
  private final String buffer = "_".repeat(60);

  /**
   * Method for initializing Table and starting the program.
   */
  public void start() {
    init();
    doOperation(menuList());
  }

  /**
   * Method for adding 4 elements of trainDeparture to table
   */
  public void init() {
    // Oppretter først 4 objekter av klassen TrainDeparture og legger dem inn i et objekt av klassen Table
    try {
      table.add(601, new TrainDeparture(LocalTime.of(12, 15), "L3", "Hamar", 3));
      table.add(305, new TrainDeparture(LocalTime.of(15, 30), "L2", "Sognsvann", -1));
      table.add(404, new TrainDeparture(LocalTime.of(10, 30), "L13",  "Bergkrystallen", -1));
      table.add(406, new TrainDeparture(LocalTime.of(10, 40), "L4",  "Bergkrystallen", -1));
    } catch (IllegalArgumentException | DateTimeException e){
      System.out.println("You tried to write something not acceptable: " + e);
      System.exit(0);
    }
  }

  /**
   * Prints out a menu to user, takes in an int from user and returns the int
   *
   * @return menuChoice
   */
  private int menuList() {
    int menuChoice;
    do {
      System.out.println("_".repeat(60));
      //skriver ut funksjonene til bruker
      System.out.println("""
          [1] Vis tog avgangene
          [2] Legg til ny togavgang
          [3] Tildel spor til avgang
          [4] Legg inn forsinkelse
          [5] Søk etter togavgang basert på tognummer
          [6] Søk etter togavgang basert på destinasjon
          [7] Oppdater klokken
          [8] Avslutt
          """);

      //får inn valgt funksjon fra bruker og legger i variablen menuChoice
      menuChoice = input.intInput("Skriv inn tallet som korresponderer med handlingen du vil utføre: ", 0);
      //hvis det som ble satt inn ikke var et tall, blir dummy-verdien 0 satt inn og løkka gjentas
    } while (menuChoice == 0);
    return menuChoice; //returnerer valgt int verdi
  }

  /**
   * Takes in an int and does the corresponding operation in a switch. It then uses
   * {@link #menuList() menuList} to get new int for user and loops
   * @see #menuList() menuList to get new int from user and loops
   *
   * @param menuChoice         takes in an int that it does the corresponding int to
   */
  private void doOperation(int menuChoice) {
    while(true) {
      switch (menuChoice) {
        case PRINT_TRAINDEPARTURE -> printTrainDeparture();
        case ADD_TRAINDEPARTURE -> addTrainDeparture();
        case SET_TRACKTOTRAIN -> setTrackToTrain();
        case SET_DELAYTOTRAIN -> setDelayToTrain();
        case FIND_TRAINBYTRAINNUMBER -> findTrainByTrainNumber();
        case FIND_TRAINBYDESTINATION -> findTrainByDestination();
        case UPDATE_CLOCK -> updateClock();
        case 8 -> System.exit(0);
        default -> System.out.println("Tallet du satte inn samsvarer ikke med et tall fra listen");
      }
      menuChoice = menuList();
    }
  }

  /**
   * Prints out all the trainDepartures in Table
   */
  private void printTrainDeparture() {
    table.setHashMap(SortByTime.sort(table.getHashMap()));
    System.out.println(String.format("%" + -19 + "s", "Time: " + clock.getClock())
        + String.format("%" + -19 + "s", "Togavganger")
        + String.format("%" + -8 + "s", "Spor: ")
        + String.format("%" + -10 + "s", "Forsinkelse: ") + "\n"
        + this.buffer);

    for (int trainNumber : table.getHashMap().keySet()) {
      System.out.println(table.getHashMap().get(trainNumber).toString(trainNumber));
    }
  }

  /**
   * A function that adds a new traindeparture to the Table-class
   * with the help of user-input
   */
  private void addTrainDeparture() {
    int hour = input.hourInput("toget går");

    int minute = input.minuteInput("toget går");

    String line = input.stringInput("Skriv inn navnet på linjen");

    int trainNumber = input.intInput("Skriv inn et nytt, unikt tognummer", 0);

    String destination = input.stringInput("Skriv inn navnet på destinasjonen");

    String print = "Skriv inn ved hvilken spor toget skal gå fra. Hvis ikke bestemt, skriv inn -1";
    int track = input.intInput(print, -1);

    try {
      TrainDeparture newTrainDeparture = new TrainDeparture(LocalTime.of(hour, minute), line, destination, track);
      table.add(trainNumber, newTrainDeparture);
    } catch (IllegalArgumentException | DateTimeException e) {
      System.out.println("You tried to write something not acceptable: " + e);
    }
  }

  /**
   * A function that lets the user pick a track which the train leaves at
   */
  public void setTrackToTrain() {
    int trainNumber = chooseTrainNumber();
    String print = "Skriv inn ved hvilken spor toget skal gå fra. Hvis ikke bestemt, skriv inn -1";
    int track = input.intInput(print, -1);
    table.setTrackToTrain(trainNumber, track);
  }

  /**
   * A function that lets the user set the delay for a train departure
   */
  public void setDelayToTrain() {
    int trainNumber = chooseTrainNumber();
    String print = "toget er forsinket med";
    LocalTime delay = LocalTime.of(input.hourInput(print), input.minuteInput(print));
    table.setDelayToTrain(trainNumber, delay);
  }

  /**
   * A function that prints a train departure that corresponds to a
   * user picked train number.
   */
  public void findTrainByTrainNumber() {
    int trainNumber = chooseTrainNumber();
    System.out.println(table.getTrainByTrainNumber(trainNumber).toString(trainNumber));
  }

  /**
   * Prints out all the train-numbers in Table and lets user write one.
   * If user doesn't put in an existing train-number, it loops until user
   * puts in a valid input. Returns an existing, user-picked train-number.
   *
   * @return trainNumber
   */
  public int chooseTrainNumber() {
    table.getTrainNumberList().forEach(System.out::println);
    int trainNumber = input.intInput("Velg en av togavgangene", 0);

    while(table.getTrainByTrainNumber(trainNumber) == null || trainNumber == 0){
      table.getTrainNumberList().forEach(System.out::println);
      System.out.println( "Du må sette inn et tognummer fra listen");
      trainNumber = input.intInput("Velg en av togavgangene", 0);
    }
    return trainNumber;
  }

  /**
   * Uses {@link #chooseDestination() chooseDestination} to get a destination from user.
   * Prints all the trainDepartures that matches the destination
   */
  public void findTrainByDestination() {
    HashMap<Integer, TrainDeparture> destinationList = chooseDestination();
    destinationList.forEach((key, value) -> value.toString(key));
  }

  /**
   * Prints an out all the unique destinations in Table to user.
   * Method will loop until user picks one of the destinations.
   * Returns a HashMap over all the trainDepartures with matching destinations
   *
   * @return destinationsList
   */
  public HashMap<Integer, TrainDeparture> chooseDestination() {
    table.getUniqueDestinationList().forEach(System.out::println);
    String destination = input.stringInput("\nVelg en av destinasjonene");
    HashMap<Integer, TrainDeparture> destinationList = table.getTrainByDestination(destination);

    while (destinationList.isEmpty()) {
      System.out.println("Du må sette inn en destinasjon fra listen");
      table.getUniqueDestinationList().forEach(System.out::println);
      destination = input.stringInput("\nVelg en av destinasjonene");
      destinationList = table.getTrainByDestination(destination);
    }
    return destinationList;
  }

  /**
   * Gets input on new hour and minute from user. If new time is before old time,
   * an exception is sent and time stays the same. Removes all the trainDepartures
   * that has departureTime after new time
   */
  public void updateClock() { //en metode som oppdaterer klokka til ny tid satt av bruker
    String print = "du vil sette klokken til";
    LocalTime time = LocalTime.of(input.hourInput(print), input.minuteInput(print));
    try {
      clock.setClock(time);
    } catch (IllegalArgumentException e){
      System.out.println("Det du satte inn ble ikke akseptert. Tiden forblir den samme");
    }
    table.removeTrainDepartureBeforeTime(time);
  }
}