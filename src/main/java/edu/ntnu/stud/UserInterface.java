package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.ClockException;
import edu.ntnu.stud.exceptions.DelayException;
import edu.ntnu.stud.exceptions.TableException;
import edu.ntnu.stud.exceptions.TrackException;
import edu.ntnu.stud.exceptions.TrainDepartureConstructorException;
import edu.ntnu.stud.sort.SortByDestination;
import edu.ntnu.stud.sort.SortByTime;
import edu.ntnu.stud.sort.SortByTrack;
import java.util.HashMap;
import java.util.Objects;

/**
 * The {@code UserInterface} class represents the user interface for managing train departures.
 * It includes methods for initializing the system with train departures and runs the menu program.
 * The user interface interacts with a {@link Table} to manage train departures and a
 * {@link Clock} to handle time.
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
      table.add(305, new TrainDeparture("15:30", "L2", "Sognsvann", 2));
      table.add(404, new TrainDeparture("10:30", "L13",  "Bergkrystallen", -1));
      table.add(406, new TrainDeparture("10:40", "L14",  "Bergkrystallen", 3));
      table.add(550, new TrainDeparture("16:30", "L8",  "Arendal", -1));
      this.table = new Table(SortByTime.sort(table.getHashMap()));
    } catch (TrainDepartureConstructorException | TableException e) {
      System.out.println("Your train-departures was not added. " + e.getMessage());
      System.exit(0); //exits the program if init isn't properly initialized
    }
    printTrainDeparture();
    doOperation(menuList());
  }

  /**
   * Initializes the system by creating instances of {@link Table},
   * {@link Clock}, and {@link Input}.
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
        [7] Sorter tabellen over tog avgangene
        [8] Fjern en togavgang
        [9] Oppdater klokken
        [10] Avslutt
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
    final int SORT_BY = 7;
    final int REMOVE_TRAINDEPARTURE = 8;
    final int UPDATE_CLOCK = 9;
    final int EXIT_SYSTEM = 10;
    while (true) {
      switch (menuChoice) {
        case PRINT_TRAINDEPARTURE -> printTrainDeparture();
        case ADD_TRAINDEPARTURE -> addTrainDeparture();
        case SET_TRACKTOTRAIN -> setTrackToTrain(chooseTrainNumber());
        case SET_DELAYTOTRAIN -> setDelayToTrain(chooseTrainNumber());
        case FIND_TRAINBYTRAINNUMBER -> findTrainByTrainNumber(chooseTrainNumber());
        case FIND_TRAINBYDESTINATION -> findTrainByDestination(chooseDestination());
        case SORT_BY -> sortBy(chooseSortingMethod());
        case REMOVE_TRAINDEPARTURE ->  removeTrainByTrainNumber(chooseTrainNumber());
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
    System.out.printf("%-19s%-19s%-8s%-10s%n%s%n", "Time: " + clock.getClock(), "Togavganger", "Spor: ", "Forsinkelse: ", "_".repeat(60));
    table.getHashMap().forEach((key, value) -> {
      try {
        System.out.println(new TrainDeparture(value).toString(key));
      } catch (TrainDepartureConstructorException e) {
        System.out.println("The train departure couldn't be printed: " + e.getMessage());
      }
    });
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
    } catch (TrainDepartureConstructorException | TableException e) {
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
  private void setTrackToTrain(int trainNumber) {
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
  private void setDelayToTrain(int trainNumber) {
    int delayInMinutes = input.intInput(
        "Skriv inn antall minuter toget er forsinket med: ", 0);
    try {
      if (table.setDelayToTrain(trainNumber, delayInMinutes)) {
        System.out.println(table.getHashMap().get(trainNumber).getDelay()
            + " was set to " + trainNumber);
      }
    } catch (DelayException e) {
      System.out.println("Delay was not set. " + e.getMessage());
    }

  }

  /**
   * Prints information about a train departure based on the user-selected train number.
   *
   */
  private void findTrainByTrainNumber(int trainNumber) {
    try {
      System.out.println(new TrainDeparture(table.getTrainByTrainNumber(trainNumber))
          .toString(trainNumber));
    } catch (TrainDepartureConstructorException e) {
      System.out.println("The train departure couldn't be printed. " + e.getMessage());
    }

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
  private int chooseTrainNumber() {
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
   */
  private void findTrainByDestination(HashMap<Integer, TrainDeparture> destinationList) {
    destinationList.forEach((key, value) -> {
      try {
        System.out.println(new TrainDeparture(value).toString(key));
      } catch (TrainDepartureConstructorException e) {
        System.out.println("The train departure couldn't be printed: " + e.getMessage());
      }
    });
  }

  /**
   * Prints all unique destinations in the table and allows the user to choose one.
   * <p>
   * This method returns a list of train departures with matching destinations
   * based on the user's selection.
   * </p>
   *
   * @return A list of train departures with matching destinations.
   */
  private HashMap<Integer, TrainDeparture> chooseDestination() {
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
   * Executes sorting of train departures based on the user's selected criteria.
   *
   * @param sortChoice    The user's selected sorting criteria.
   */
  private void sortBy(int sortChoice) {
    final int SORT_TIME = 1;
    final int SORT_DESTINATION = 2;
    final int SORT_TRACK = 3;
    try {
      do {
        switch (sortChoice) {
          case SORT_TIME -> this.table = new Table(SortByTime.sort(table.getHashMap()));
          case SORT_DESTINATION -> 
            this.table = new Table(SortByDestination.sort(table.getHashMap()));
          case SORT_TRACK -> this.table = new Table(SortByTrack.sort(table.getHashMap()));
          default -> {
            System.out.println("Tallet du satte inn samsvarer ikke med et tall fra listen");
            sortChoice = chooseSortingMethod();
          }
        }
      } while (sortChoice < SORT_TIME || sortChoice > SORT_TRACK);
    } catch (TableException e) {
      System.out.println("An error happened while running: " + e.getMessage());
    }
  }

  /**
   * Displays a menu for selecting the method for sorting train departures and
   * returns the user chosen method.
   *
   * @return The user's selected sorting method.
   */
  private int chooseSortingMethod() {
    System.out.println("""
          [1] Tid
          [2] Destinasjon
          [3] Spor
          """);
    return input.intInput(
        "Skriv inn tallet som korresponderer med hvordan du vil sortere togavgangene: ", 0);
  }

  /**
   * Removes a train departure from the table based on the provided train number.
   *
   * @param trainNumber   The train number to be removed.
   */
  private void removeTrainByTrainNumber(int trainNumber) {
    if (table.remove(trainNumber)) {
      System.out.println("The train departure was removed");
    } else {
      System.out.println("The train number was not valid. The train departure was not removed");
    }
  }

  /**
   * Gets input on a new hour and minute from the user and updates the clock.
   * <p>
   * If the new time is before the old time an exception is caught, and the time stays the same.
   * Removes all train departures with departure times after the new time.
   * </p>
   */
  private void updateClock() {
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