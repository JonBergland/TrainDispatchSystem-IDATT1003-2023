package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Dette er klassen for brukergrensesnittet
 */
public class UserInterface {
  private final Table table = new Table();
  private final Clock clock = new Clock();
  private final Input input = new Input();

  int printTrainDeparture = 1; //osv..

  private final String buffer = "_".repeat(60);

  public void start() {
    init();
    doOperation(menuList());
  }
  public void init() { //metode for oppstart av programmet
    // Oppretter først 4 objekter av klassen TrainDeparture og legger dem inn i et objekt av klassen Table
    try {
      table.add(601, new TrainDeparture(LocalTime.of(12, 15), "L3", "Frognerseteren", 3));
      table.add(305, new TrainDeparture(LocalTime.of(15, 30), "L2", "Sognsvann", -1));
      table.add(404, new TrainDeparture(LocalTime.of(10, 30), "L13",  "Bergkrystallen", -1));
      table.add(406, new TrainDeparture(LocalTime.of(10, 40), "L4",  "Bergkrystallen", -1));
    } catch (IllegalArgumentException | DateTimeException e){
      System.out.println("You tried to write something not acceptable: " + e);
    }
  }

  private int menuList() { //en metode som lager en meny over funksjonene til programmet og lar bruker velge en av dem
    int menuChoice;
    do {
      System.out.println(buffer);
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

  private void doOperation(int menuChoice) { //en metode som tar inn tallverdien som bruker satte inn som parameter
    while(true) { //kjører en do-while løkke som kjører så lenge meny-valget ikke er 8
      switch (menuChoice) { //bruker switch til å kjøre metoden som tilsvarer til den brukervalgte verdien
        case 1 -> printTrainDeparture();
        case 2 -> addTrainDeparture();
        case 3 -> setTrackToTrain();
        case 4 -> setDelayToTrain();
        case 5 -> findTrainByTrainNumber();
        case 6 -> findTrainByDestination();
        case 7 -> updateClock();
        case 8 -> System.exit(0);
        default -> System.out.println("Tallet du satte inn samsvarer ikke med et tall fra listen");
      }
      menuChoice = menuList();
    }
  }

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
    System.out.println(table.findTrainByTrainNumber(trainNumber).toString(trainNumber));
  }

  public int chooseTrainNumber() {
    table.getTrainNumberList().forEach(System.out::println);
    int trainNumber = input.intInput("Velg en av togavgangene", 0);

    while(table.findTrainByTrainNumber(trainNumber) == null || trainNumber == 0){
      table.getTrainNumberList().forEach(System.out::println);
      System.out.println( "Du må sette inn et tognummer fra listen");
      trainNumber = input.intInput("Velg en av togavgangene", 0);
    }
    return trainNumber;
  }

  public void findTrainByDestination() {
    HashMap<Integer, TrainDeparture> destinationList = chooseDestination();
    destinationList.forEach((key, value) -> value.toString(key));
  }

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