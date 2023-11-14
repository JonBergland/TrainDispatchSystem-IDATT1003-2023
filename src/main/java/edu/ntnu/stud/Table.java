package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.abs;

/**
 * Dette er enitetsklassen for listen over tog-avganger
 */
public class Table {
  /**
   * Objektsvaribel som skal inneholde alle TrainDeparture-objektene
   */
  private HashMap<Integer, TrainDeparture> hashMap;
  private final Clock clock = new Clock();
  private final Input input = new Input();

  private final String buffer = "_".repeat(60);

  public Table() {
    this.hashMap = new HashMap<>();
  }

  /**
   * Gets the list over trainDepartures
   *
   * @return List<TrainDeparture>
   */
  public HashMap<Integer, TrainDeparture> getHashMap() { //en get-metode som returnere listen over TrainDeparture-objektene
    return this.hashMap;
  }

  /**
   * Gets all the trainDeparture with the same destination parameter
   *
   * @param destination
   * @return ArrayList<TrainDeparture>
   */
  public HashMap<Integer, TrainDeparture> getTrainByDestination(String destination) {
    HashMap<Integer, TrainDeparture> destinationMap = new HashMap<>();
    hashMap.entrySet().forEach(map -> {
      if (map.getValue().getDestination().equalsIgnoreCase(destination)) {
        destinationMap.put(map.getKey(), map.getValue());
      }
    });
    return destinationMap; //returnerer lista
  }

  /**
   * A function that prints all the exiting train numbers
   */
  private void printTrainNumbers() {
    for (int existingTrainNumber : hashMap.keySet()) {
      System.out.println(existingTrainNumber);
    }
  }
  /**
   * Prints all the unique trainDeparture destinations
   */
  public void printDestinationList() { //en metode som skriver ut alle de unike destinasjonene
    //lager en ny arraylist som skal romme alle de unike destinasjonene
    ArrayList<String> unique = new ArrayList<>();
    for (TrainDeparture trainDeparture : hashMap.values()) {
      String destination = trainDeparture.getDestination();
      if (!unique.contains(destination)) {
        System.out.println(destination + " ");
        unique.add(destination);
      }
    }
  }

  /**
   * Prints an overview of all the traindepartures in the Table class
   */
  public void printTrainDeparture() {
    this.hashMap = new SortByTime(this.hashMap).sort();
    System.out.println(String.format("%" + -19 + "s", "Time: " + clock.getClock())
        + String.format("%" + -19 + "s", "Togavganger")
        + String.format("%" + -8 + "s", "Spor: ")
        + String.format("%" + -10 + "s", "Forsinkelse: ") + "\n"
        + this.buffer);
    //bruker en lambda expression for å skrive ut hvert TrainDeparture-objekt i Table-objektet
    for (int trainNumber : this.hashMap.keySet()) {
      System.out.println(this.hashMap.get(trainNumber).toString(trainNumber));
    }
  }

  /**
   * A function that prints a train departure that corresponds to a
   * user picked train number.
   */
  public void findTrainByTrainNumber() {
    int trainNumber = chooseTrainNumber();
    //bruker toString() for å skrive ut riktig TrainDeparture
    System.out.println(hashMap.get(trainNumber).toString(trainNumber));
  }

  /**
   * A function that prints a list of train departures that corresponds
   * to a user picked destination
   */
  public void findTrainByDestination() {
    HashMap<Integer, TrainDeparture> destinationList = chooseDestination();
    destinationList.forEach((key, value) -> {
      System.out.println(value.toString(key)); //bruker et lambda-utrykk for å skrive ut objektene
    });
  }

  /**
   * Adds a map of key (trainNumber) and value (trainDeparture) to Hashmap
   *
   * @param trainNumber    The unique train number that is set as the key in the hash map
   * @param trainDeparture The trainDeparture entity that the train number belongs to
   */
  public void add(int trainNumber, TrainDeparture trainDeparture) {
    if (trainNumber < 0) {
      trainNumber = abs(trainNumber);
    } else if (hashMap.get(trainNumber) != null) {
      throw new IllegalArgumentException("Tog-nummeret er det samme som en annen togavgang");
    }
    this.hashMap.put(trainNumber, trainDeparture);
  }

  /**
   * A function that adds a new traindeparture to the Table-class
   * with the help of user-input
   */
  public void addTrainDeparture() {
    int hour = input.hourInput("toget går");

    int minute = input.minuteInput("toget går");

    String line = input.stringInput("Skriv inn navnet på linjen");

    int trainNumber = input.intInput("Skriv inn et nytt, unikt tognummer", 0);
    while (hashMap.get(trainNumber) != null || trainNumber == 0) {
      trainNumber = input.intInput("Det nummeret eksisterer allerede. Skriv inn et nytt, unikt tognummer", 0);
    }

    String destination = input.stringInput("Skriv inn navnet på destinasjonen");

    String print = "Skriv inn ved hvilken spor toget skal gå fra. Hvis ikke bestemt, skriv inn -1";
    int track = input.intInput(print, -1);

    //Lager en objekt av TrainDeparture med verdiene vi fikk fra bruker og legger den inn i Table-objektet
    try {
      TrainDeparture newTraindeparture = new TrainDeparture(LocalTime.of(hour, minute), line, destination, track);
      add(trainNumber, newTraindeparture);
    } catch (IllegalArgumentException e) {
      System.out.println("Kunne ikke legge til ny togavgang grunnet feilmelding " + e);
    }
  }

  /**
   * Sets a new hashMap to Table
   *
   * @param hashMap The new hashmap that overrides the original
   */
  public void setHashMap(HashMap<Integer, TrainDeparture> hashMap) {
    this.hashMap = hashMap;
  }


  /**
   * A function that lets the user pick a track which the train leaves at
   */
  public void setTrackToTrain() {
    int trainNumber = chooseTrainNumber();
    String print = "Skriv inn ved hvilken spor toget skal gå fra. Hvis ikke bestemt, skriv inn -1";
    int track = input.intInput(print, -1);
    this.hashMap.get(trainNumber).setTrack(track);
  }

  /**
   * A function that lets the user set the delay for a train departure
   */
  public void setDelayToTrain() {
    int trainNumber = chooseTrainNumber();
    String print = "toget er forsinket med";
    LocalTime delay = LocalTime.of(input.hourInput(print), input.minuteInput(print));
    this.hashMap.get(trainNumber).setDelay(delay);
  }

  public int chooseTrainNumber() {
    printTrainNumbers();
    int trainNumber = input.intInput("Velg en av togavgangene", 0);

    while(hashMap.get(trainNumber) == null || trainNumber == 0){
      System.out.println( "Du må sette inn et tognummer fra listen");
      trainNumber = input.intInput("\nVelg en av togavgangene", 0);
    }
    return trainNumber;
  }

  public HashMap<Integer, TrainDeparture> chooseDestination() { //en metode for å velge en destinasjon fra en liste
    HashMap<Integer, TrainDeparture> destinationList;
    printDestinationList();
    String destination = input.stringInput("\nVelg en av destinasjonene");
    destinationList = getTrainByDestination(destination);

    while (destinationList.isEmpty()) {
      System.out.println("Du må sette inn en destinasjon fra listen");
      printDestinationList();
      destination = input.stringInput("\nVelg en av destinasjonene");
      destinationList = getTrainByDestination(destination);
    }
    return destinationList;
  }

  public void updateClock() { //en metode som oppdaterer klokka til ny tid satt av bruker
    String print = "du vil sette klokken til";
    LocalTime time = LocalTime.of(input.hourInput(print), input.minuteInput(print));

    if (time.isBefore(clock.getClock())) {
      throw new IllegalArgumentException(
          "Klokken blir forsøkt satt til før nåverende tidspunkt");
    } else {
      clock.setClock(time);
      hashMap.entrySet().removeIf(map -> map.getValue().getDepartureTime().isBefore(time));
    }
  }
}

