package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;

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
    hashMap.forEach((key, value) -> {
      if (value.getDestination().equalsIgnoreCase(destination)) {
        destinationMap.put(key, value);
      }
    });
    return destinationMap; //returnerer lista
  }

  /**
   * Gets all the unique destinations in Table
   *
   * @return uniqueDestinations
   */
  public HashSet<String> getUniqueDestinationList() {
    HashSet<String> uniqueDestination = new HashSet<>();
    for (TrainDeparture trainDeparture : hashMap.values()) {
      uniqueDestination.add(trainDeparture.getDestination());
    }
    return uniqueDestination;
  }

  public HashSet<Integer> getTrainNumberList() {
    return new HashSet<>(this.hashMap.keySet());
  }

  /**
   * A function that prints a train departure that corresponds to a
   * user picked train number.
   */
  public TrainDeparture findTrainByTrainNumber(int trainNumber) {
    return hashMap.get(trainNumber);
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
   * @param trainNumber               The unique train number that is set as the key in the hash map
   * @param trainDeparture            The trainDeparture entity that the train number belongs to
   *
   * @throws IllegalArgumentException Throws Exception if the trainNumber
   *                                  already exists in the register
   */
  public void add(int trainNumber, TrainDeparture trainDeparture)
      throws IllegalArgumentException {
    if (trainNumber < 0) {
      trainNumber = abs(trainNumber);
    }
    if (hashMap.get(trainNumber) != null) {
      throw new IllegalArgumentException("The train-number already exists");
    } else {
      this.hashMap.put(trainNumber, trainDeparture);
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

  public void setTrackToTrain(int trainNumber, int track) {
    this.hashMap.get(trainNumber).setTrack(track);
  }

  public void setDelayToTrain(int trainNumber, LocalTime delay) {
    this.hashMap.get(trainNumber).setDelay(delay);
  }

  public int chooseTrainNumber() {
    for (int existingTrainNumber : hashMap.keySet()) {
      System.out.println(existingTrainNumber);
    }
    int trainNumber = input.intInput("Velg en av togavgangene", 0);

    while(hashMap.get(trainNumber) == null || trainNumber == 0){
      System.out.println( "Du må sette inn et tognummer fra listen");
      trainNumber = input.intInput("\nVelg en av togavgangene", 0);
    }
    return trainNumber;
  }

  public HashMap<Integer, TrainDeparture> chooseDestination() { //en metode for å velge en destinasjon fra en liste
    HashSet<String> uniqueDestinations = getUniqueDestinationList();
    uniqueDestinations.forEach(System.out::println);
    String destination = input.stringInput("\nVelg en av destinasjonene");
    HashMap<Integer, TrainDeparture> destinationList = getTrainByDestination(destination);

    while (destinationList.isEmpty()) {
      System.out.println("Du må sette inn en destinasjon fra listen");
      uniqueDestinations.forEach(System.out::println);
      destination = input.stringInput("\nVelg en av destinasjonene");
      destinationList = getTrainByDestination(destination);
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
    } finally {
      hashMap.entrySet().removeIf(map -> map.getValue().getDepartureTime().isBefore(time));
    }
  }
}

