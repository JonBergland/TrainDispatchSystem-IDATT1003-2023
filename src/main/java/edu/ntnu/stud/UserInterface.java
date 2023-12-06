package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.ClockException;
import edu.ntnu.stud.exceptions.DelayException;
import edu.ntnu.stud.exceptions.TableAddException;
import edu.ntnu.stud.exceptions.TrackException;
import edu.ntnu.stud.exceptions.TrainDepartureConstructorException;
import edu.ntnu.stud.sort.SortByDestination;
import edu.ntnu.stud.sort.SortByTime;

import java.util.HashMap;
import java.util.Objects;

/**
 * The {@code UserInterface} class represents the user interface for managing train departures.
 * It includes methods for initializing the system with train departures and running the menu program.
 * The user interface interacts with a {@link Table} to manage train departures and a {@link Clock} to handle time.
 */
public class UserInterface {
  private Table table;
  private Clock clock;
  private Input input;

  /**
   * Initializes the system with predefined train departures and runs the menu program indefinitely.
   * <p>
   * This method adds predefined train departures to the table and enters an infinite loop
   * to run the menu program using the {@link #doOperation(int)} method.
   * </p>
   */
  public void start() {
    init();
    try {
      table.add(601, new TrainDeparture("12:15", "L3", "Hamar", -1));
      table.add(305, new TrainDeparture("15:30", "L2", "Sognsvann", -1));
      table.add(404, new TrainDeparture("10:30", "L13",  "Bergkrystallen", -1));
      table.add(406, new TrainDeparture("10:40", "L4",  "Bergkrystallen", -1));
      table.add(550, new TrainDeparture("16:30", "L8",  "Arendal", -1));

    } catch (Exception e) {
      System.out.println("Your train-departures was not added. " + e.getMessage());
      System.exit(0); //exits the program if init isn't properly initialized
    }
    printTrainDeparture();
    doOperation(menuList());
  }

  /**
   * Initializes the system by creating instances of {@link Table}, {@link Clock}, and {@link Input}.
   */
  public void init() {
    this.table = new Table();
    this.clock = new Clock();
    this.input = new Input();
  }

  /**
   * Prints a menu for the user, takes an input, and returns the selected option.
   *
   * @return The user's menu choice.
   */
  public int menuList() {
      System.out.println("_".repeat(60));
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
    return input.intInput(
        "Skriv inn tallet som korresponderer med handlingen du vil utføre: ", 0);
  }

  /**
   * Executes the operation corresponding to the provided menu choice.
   * <p>
   * This method uses a switch statement to perform the operation based on the user's menu choice.
   * It continuously loops, allowing the user to perform multiple operations until choosing to exit.
   * </p>
   *
   * @param menuChoice   The user's selected menu choice.
   */
  private void doOperation(int menuChoice) {
    final int PRINT_TRAINDEPARTURE = 1;
    final int ADD_TRAINDEPARTURE = 2;
    final int SET_TRACKTOTRAIN = 3;
    final int SET_DELAYTOTRAIN = 4;
    final int FIND_TRAINBYTRAINNUMBER = 5;
    final int FIND_TRAINBYDESTINATION = 6;
    final int UPDATE_CLOCK = 7;
    final int EXIT_SYSTEM = 8;
    while (true) {
      switch (menuChoice) {
        case PRINT_TRAINDEPARTURE -> {
          table.setHashMap(SortByTime.sort(table.getHashMap()));
          printTrainDeparture();
        }
        case ADD_TRAINDEPARTURE -> addTrainDeparture();
        case SET_TRACKTOTRAIN -> setTrackToTrain();
        case SET_DELAYTOTRAIN -> setDelayToTrain();
        case FIND_TRAINBYTRAINNUMBER -> findTrainByTrainNumber();
        case FIND_TRAINBYDESTINATION -> findTrainByDestination();
        case UPDATE_CLOCK -> updateClock();
        case EXIT_SYSTEM -> System.exit(0);
        default -> System.out.println("Tallet du satte inn samsvarer ikke med et tall fra listen");
      }
      menuChoice = menuList();
    }
  }

  /**
   * Prints all train departures in the table, sorted by time.
   */
  private void printTrainDeparture() {
    /*
    System.out.println(String.format("%" + -19 + "s", "Time: " + clock.getClock())
        + String.format("%" + -19 + "s", "Togavganger")
        + String.format("%" + -8 + "s", "Spor: ")
        + String.format("%" + -10 + "s", "Forsinkelse: ") + "\n"
        + "_".repeat(60));
     */
    System.out.printf("%-19s%-19s%-8s%-10s%n%s%n",
        "Time: " + clock.getClock(), "Togavganger", "Spor: ", "Forsinkelse: ",
        "_".repeat(60));

    for (int trainNumber : table.getHashMap().keySet()) {
      System.out.println(Objects.requireNonNull(new TrainDeparture(table.getHashMap().get(trainNumber))
          .toString(trainNumber)));
    }
  }

  /**
   * Adds a new train departure to the table based on user input.
   * <p>
   * This method prompts the user for information to create a new {@link TrainDeparture} object
   * and adds it to the table. Handles exceptions if the addition fails.
   * </p>
   */
  private void addTrainDeparture() {
    String originalDepartureTime = input.stringInput(
        "Skriv inn tiden togavgangen på formatet HH:mm");

    String line = input.stringInput("Skriv inn navnet på linjen");

    int trainNumber = input.intInput("Skriv inn et nytt, unikt tognummer", 0);
    while (table.getTrainByTrainNumber(trainNumber) != null) {
      System.out.println("Tognummeret du skrev inn var ikke unikt");
      trainNumber = input.intInput("Skriv inn et nytt, unikt tognummer", 0);
    }

    String destination = input.stringInput("Skriv inn navnet på destinasjonen");

    String print = "Skriv inn ved hvilken spor toget skal gå fra. Hvis ikke bestemt, skriv inn -1";
    int track = input.intInput(print, -1);

    try {
      TrainDeparture newTrainDeparture = new TrainDeparture(
          originalDepartureTime, line, destination, track);
      if (table.add(trainNumber, newTrainDeparture)) {
        System.out.println("The train-departure was added");
      }
    } catch (TrainDepartureConstructorException | TableAddException e) {
      System.out.println("The train-departure was not added. " + e.getMessage());
    }
  }

  /**
   * Allows the user to assign a track to a train based on user input.
   * <p>
   * This method prompts the user to enter a track number for a specific train.
   * </p>
   *
   */
  public void setTrackToTrain() {
    int trainNumber = chooseTrainNumber();
    String print = "Skriv inn ved hvilken spor toget skal gå fra. Hvis ikke bestemt, skriv inn -1";
    int track = input.intInput(print, -1);
    try {
      if (table.setTrackToTrain(trainNumber, track)) {
        System.out.print("Sporet til " + trainNumber + " er satt til ");
        if (track == -1) {
          System.out.println("ubestemt (-1)");
        } else {
          System.out.println(track);
        }
      }

    } catch (TrackException e) {
      System.out.println(e.getMessage() + ". The track was not set to the train");
    }
  }

  /**
   * Allows the user to set a delay for a train departure based on user input.
   * <p>
   * This method prompts the user to enter a delay time for a specific train departure.
   * </p>
   *
   */
  public void setDelayToTrain() {
    int trainNumber = chooseTrainNumber();
    String delay = input.stringInput("Skriv inn forsinkelsen til togavgangen på formatet HH:mm");
    try {
      if (table.setDelayToTrain(trainNumber, delay)) {
        System.out.println(table.getHashMap().get(trainNumber).getDelay() + " was set to " + trainNumber);
      }
    } catch (DelayException e) {
      System.out.println("Delay was not set. " + e.getMessage());
    }

  }

  /**
   * Prints information about a train departure based on the user-selected train number.
   *
   */
  public void findTrainByTrainNumber() {
    int trainNumber = chooseTrainNumber();
    System.out.println(new TrainDeparture(table.getTrainByTrainNumber(trainNumber))
        .toString(trainNumber));
  }

  /**
   * Allows the user to choose a train number from the list of available train departures.
   * <p>
   * This method displays the list of train numbers, prompts the user to choose one,
   * and ensures that a valid and existing train number is selected.
   * </p>
   *
   * @return The user-selected train number.
   */
  public int chooseTrainNumber() {
    table.getTrainNumberList().forEach(System.out::println);
    int trainNumber = input.intInput("Velg en av togavgangene", 0);

    while (table.getTrainByTrainNumber(trainNumber) == null || trainNumber == 0) {
      System.out.println("Du må sette inn et tognummer fra listen");
      table.getTrainNumberList().forEach(System.out::println);
      trainNumber = input.intInput("Velg en av togavgangene", 0);
    }
    return trainNumber;
  }


  /**
   * Prints information about train departures based on the user-selected destination.
   *
   */
  public void findTrainByDestination() {
    HashMap<Integer, TrainDeparture> destinationList = chooseDestination();
    destinationList.forEach((key, value) ->
        System.out.println(new TrainDeparture(value).toString(key)));
  }

  /**
   * Prints all unique destinations in the table and allows the user to choose one.
   * <p>
   * This method returns a list of train departures with matching destinations based on the user's selection.
   * </p>
   *
   * @return A list of train departures with matching destinations.
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

  public void sortBy(int sortChoice) {
    final int SORT_TIME = 1;
    final int SORT_DESTINATION = 2;
    final int SORT_LINE = 3;
    final int SORT_TRACK = 4;
    final int SORT_DELAY = 5;
    switch (sortChoice) {
      case SORT_TIME -> {
        table.setHashMap(SortByTime.sort(table.getHashMap()));
        printTrainDeparture();
      }
      case SORT_DESTINATION -> {
        table.setHashMap(SortByDestination.sort(table.getHashMap()));
        printTrainDeparture();
      }
      case SORT_LINE -> setTrackToTrain();
      case SORT_TRACK -> setDelayToTrain();
      case SORT_DELAY -> findTrainByTrainNumber();
      default -> System.out.println("Tallet du satte inn samsvarer ikke med et tall fra listen");
    }
  }

  public int sortMenuList() {
    System.out.println("""
          [1] Tid
          [2] Destinasjon
          [3] Linje
          [4] Spor
          [5] Forsinkelse
          """);
    return input.intInput(
        "Skriv inn tallet som korresponderer med hvordan du vil sortere togavgangene: ", 0);
  }

  /**
   * Gets input on a new hour and minute from the user and updates the clock.
   * <p>
   * If the new time is before the old time, an exception is caught,
   * and the time stays the same. Removes all train departures with departure times after the new time.
   * </p>
   */
  public void updateClock() { //en metode som oppdaterer klokka til ny tid satt av bruker
    String newTime = input.stringInput("Skriv inn et nytt klokkeslett på formatet HH:mm");
    try {
      clock.setClock(newTime);
      System.out.println("Time is updated");
    } catch (ClockException e) {
      System.out.println(e.getMessage() + ". Time stays the same");
    }
    table.removeTrainDepartureBeforeTime(clock.getClock());
  }
}