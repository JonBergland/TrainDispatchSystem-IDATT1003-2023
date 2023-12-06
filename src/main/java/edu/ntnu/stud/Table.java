package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.DelayException;
import edu.ntnu.stud.exceptions.TableAddException;
import edu.ntnu.stud.exceptions.TrackException;
import edu.ntnu.stud.exceptions.TrainDepartureConstructorException;
import edu.ntnu.stud.verification.Verification;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a table of TrainDeparture-objects indexed by train numbers.
 * <p>
 *   The class contains a HashMap with Integer-keys (train numbers) and
 *   TrainDeparture-values. This class works as a container for managing and
 *   organizing TrainDeparture-objects.
 * </p>
 */
public class Table {
  private HashMap<Integer, TrainDeparture> hashMap;

  /**
   * Constructs a Table object with an empty HashMap for storing TrainDeparture-objects.
   * Upon initializing, a new HashMap is created for the hashMap-variable in Table.
   */
  public Table() {
    this.hashMap = new HashMap<>();
  }

  /**
   * Gets the list over trainDepartures.
   * This method returns the HashMap in the Table-class
   *
   * @return The Hashmap with all the TrainDeparture-objects
   */
  public HashMap<Integer, TrainDeparture> getHashMap() {
    return this.hashMap;
  }

  /**
   * Gets the TrainDeparture objects with a specific destination.
   * This method iterates through all the entry-sets in the HashMap
   * variable in Table and returns all the entries with the specific
   * destination as a new HashMap
   *
   * @param destination                         The specific destination that the method
   *                                            searches the HashMap for
   * @return destinationHashMap
   */
  public HashMap<Integer, TrainDeparture> getTrainByDestination(String destination) {
    HashMap<Integer, TrainDeparture> destinationHashMap = new HashMap<>();
    hashMap.forEach((key, value) -> {
      if (value.getDestination().equalsIgnoreCase(destination)) {
        destinationHashMap.put(key, value);
      }
    });
    return destinationHashMap;
  }

  /**
   * Gets all the unique destinations in Table.
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
   * Retrieves a TrainDeparture object based on a given Integer-key (trainNumber) from the HashMap.
   * This method takes a given Integer-key and returns the associated TrainDeparture object.
   * If an associated TrainDeparture object doesn't exist, this method returns null.
   *
   * @param trainNumber               The key which this method gets the associated TrainDeparture
   *                                  object for.
   * @return The TrainDeparture object or null if the trainNumber has no associated TrainDeparture
   */
  public TrainDeparture getTrainByTrainNumber(int trainNumber) {
    return hashMap.get(trainNumber);
  }

  /**
   * Adds an Integer-key (trainNumber) and a TrainDeparture-value (trainDeparture)
   * to the Hashmap variable in Table. This method takes a trainNumber (which represents
   * the key in the HashMap) and a TrainDeparture object (which represents the value in
   * the HashMap).
   *
   * @param trainNumber               The unique train number that is set as the key in the hash map
   * @param trainDeparture            The trainDeparture entity that the train number belongs to
   *
   * @throws IllegalArgumentException Throws Exception if the trainNumber
   *                                  already exists in the register
   */
  public boolean add(int trainNumber, TrainDeparture trainDeparture)
      throws TableAddException, TrainDepartureConstructorException {
    try {
      Verification.requireNonZeroOrLess(trainNumber,
          "The train number was 0 or less");
      if (hashMap.get(trainNumber) != null) {
        throw new IllegalArgumentException("The train-number already exists");
      }
      //make a deep copy of TrainDeparture
      this.hashMap.put(trainNumber, new TrainDeparture(trainDeparture));
    } catch (IllegalArgumentException e) {
      throw new TableAddException(e.getMessage());
    }
    return true;
  }

  /**
   * Sets a new HashMap as the HashMap variable in Table
   * This method takes in a new HashMap as parameter and
   * creates a deep copy of it. It is then set as the new
   * HashMap in the Table-class
   *
   * @param hashMap       The new hashmap that overrides the original
   */
  public void setHashMap(HashMap<Integer, TrainDeparture> hashMap) {
    HashMap<Integer, TrainDeparture> newHashMap = new LinkedHashMap<>();
    for (Map.Entry<Integer, TrainDeparture> entry : hashMap.entrySet()) {
      newHashMap.put(entry.getKey(), new TrainDeparture(entry.getValue()));
    }
    this.hashMap = newHashMap; //made a deep copy of the new HashMap
  }

  /**
   * Sets track to a specific train-departure in the HashMap.
   * This method designates a track to the train-departure corresponding
   * to the given trainNumber
   *
   * @param trainNumber         The trainDeparture's trainNumber
   * @param track               The track you want to assign
   */
  public boolean setTrackToTrain(int trainNumber, int track) throws TrackException {
    return this.hashMap.get(trainNumber).setTrack(track);
  }

  /**
   * Sets delay to a specific train-departure in the HashMap.
   * This method sets a delay to the train-departure corresponding
   * to the given trainNumber.
   *
   * @param trainNumber         The trainDeparture's trainNumber
   * @param delay               The delay you want to assign
   */
  public boolean setDelayToTrain(int trainNumber, String delay) throws DelayException {
    if (this.hashMap.get(trainNumber) != null) {
      this.hashMap.get(trainNumber).setDelay(delay);
    } else {
      //TODO Add a unique exception to train-number
      throw new DelayException("Train-number is not unique");
    }
    return true;
  }

  /**
   * Removes trainDeparture objects that is before the specified time.
   * This method iterates through all the entries in the hashmap and removes
   * those where the TrainDeparture objects have an earlier departure than
   * the specified time
   *
   * @param time            The LocalTime variable which marks a threshold time.
   *                        All TrainDeparture-object with an earlier departure than
   *                        time, gets removed
   */
  public void removeTrainDepartureBeforeTime(LocalTime time) {
    hashMap.entrySet().removeIf(map -> map.getValue().getDepartureTime().isBefore(time));
  }
}

