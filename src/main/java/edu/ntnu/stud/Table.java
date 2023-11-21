package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;

import static java.lang.Math.abs;

/**
 * Dette er enitetsklassen for listen over tog-avganger
 */
public class Table {
  private HashMap<Integer, TrainDeparture> hashMap;
  private final Clock clock = new Clock();
  private final Input input = new Input();
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
   * Gets all the trainDeparture with the same destination as the parameter
   *
   * @param destination                         The destination that HashMap search for
   * @return destinationHashMap
   */
  public HashMap<Integer, TrainDeparture> getTrainByDestination(String destination) {
    HashMap<Integer, TrainDeparture> destinationHashMap = new HashMap<>();
    hashMap.forEach((key, value) -> {
      if (value.getDestination().equalsIgnoreCase(destination)) {
        destinationHashMap.put(key, value);
      }
    });
    return destinationHashMap; //returnerer lista
  }

  /**
   * Gets all the unique destinations in Table
   *
   * @return uniqueDestinations
   */
  public HashSet<String> getUniqueDestinationList() {
    HashSet<String> uniqueDestinations = new HashSet<>();
    for (TrainDeparture trainDeparture : hashMap.values()) {
      uniqueDestinations.add(trainDeparture.getDestination());
    }
    return uniqueDestinations;
  }

  public HashSet<Integer> getTrainNumberList() {
    return new HashSet<>(this.hashMap.keySet());
  }

  /**
   * A function that prints a train departure that corresponds to a
   * user picked train number.
   */
  public TrainDeparture getTrainByTrainNumber(int trainNumber) {
    return hashMap.get(trainNumber);
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
  public void add(int trainNumber, TrainDeparture trainDeparture) //legg til at den returnerer bool-verdi
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

  /**
   *
   *
   * @param trainNumber
   * @param track
   */
  public void setTrackToTrain(int trainNumber, int track) {
    this.hashMap.get(trainNumber).setTrack(track);
  }

  /**
   *
   * @param trainNumber
   * @param delay
   */
  public void setDelayToTrain(int trainNumber, LocalTime delay) {
    this.hashMap.get(trainNumber).setDelay(delay);
  }

  /**
   *
   * @param time
   */
  public void removeTrainDepartureBeforeTime(LocalTime time) {
    hashMap.entrySet().removeIf(map -> map.getValue().getDepartureTime().isBefore(time));
  }
}

