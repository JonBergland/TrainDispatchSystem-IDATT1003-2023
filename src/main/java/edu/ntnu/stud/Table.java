package edu.ntnu.stud;

import edu.ntnu.stud.exceptions.DelayException;
import edu.ntnu.stud.exceptions.TableException;
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
 * The class contains a HashMap with Integer-keys (train numbers) and
 * TrainDeparture-values. This class works as a container for managing and
 * organizing TrainDeparture-objects.
 * </p>
 */
public class Table {
  private final HashMap<Integer, TrainDeparture> hashMap;

  /**
   * Constructs a Table object with an empty HashMap for storing TrainDeparture-objects.
   * Upon initializing, a new HashMap is created for the hashMap-variable in Table.
   */
  public Table() {
    this.hashMap = new HashMap<>();
  }

  /**
   * Constructs a Table object from an already existing hashmap.
   *
   * @param oldHashMap The existing HashMap that is copied
   */
  public Table(HashMap<Integer, TrainDeparture> oldHashMap)
      throws TableException {
    try {
      HashMap<Integer, TrainDeparture> newHashMap = new LinkedHashMap<>();
      for (Map.Entry<Integer, TrainDeparture> entry : oldHashMap.entrySet()) {
        newHashMap.put(entry.getKey(), new TrainDeparture(entry.getValue()));
      }
      this.hashMap = newHashMap; //made a deep copy of the old HashMap
    } catch (TrainDepartureConstructorException | NullPointerException e) {
      throw new TableException(e.getMessage());
    }
  }

  /**
   * Gets the list over trainDepartures.
   * <p>
   * This method returns the HashMap in the Table-class
   * </p>
   *
   * @return The Hashmap with all the TrainDeparture-objects
   */
  public HashMap<Integer, TrainDeparture> getHashMap() {
    return this.hashMap;
  }

  /**
   * Gets the TrainDeparture objects with a specific destination.
   * <p>
   * This method iterates through all the entry-sets in the HashMap
   * variable in Table and returns all the entries with the specific
   * destination as a new HashMap
   * </p>
   *
   * @param destination The specific destination that the method
   *                    searches the HashMap for
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
   * <p>
   * This method loops through the HashMap in Table and adds the destinations to a
   * hashset
   * </p>
   *
   * @return a HashSet with all the unique destinations in the HashMap in Table
   */
  public HashSet<String> getUniqueDestinationSet() {
    HashSet<String> uniqueDestinations = new HashSet<>();
    for (TrainDeparture trainDeparture : hashMap.values()) {
      uniqueDestinations.add(trainDeparture.getDestination());
    }
    return uniqueDestinations;
  }

  /**
   * Gets all the trainNumbers in Table.
   * <p>
   *   This method puts all the keys in the HashMap in Table and returns them
   *   as a HashSet
   * </p>
   *
   * @return a HashSet with all the keys in the HashMap in Table
   */
  public HashSet<Integer> getTrainNumberSet() {
    return new HashSet<>(this.hashMap.keySet());
  }

  /**
   * Retrieves a TrainDeparture object based on a given Integer-key (trainNumber) from the HashMap.
   * <p>
   * This method takes a given Integer-key and returns the associated TrainDeparture object.
   * If an associated TrainDeparture object doesn't exist, this method returns null.
   * </p>
   *
   * @param trainNumber The key which this method gets the associated TrainDeparture
   *                    object for.
   * @return            A deep copy of the TrainDeparture object or null if the
   *                    trainNumber has no associated TrainDeparture
   */
  public TrainDeparture getTrainByTrainNumber(int trainNumber)
      throws TrainDepartureConstructorException {
    return new TrainDeparture(hashMap.get(trainNumber));
  }

  /**
   * Adds an Integer-key ({@code trainNumber}) and a TrainDeparture-value ({@code trainDeparture})
   * to the {@code hashMap} variable in Table.
   * <p>
   * This method takes a trainNumber (which represents
   * the key in the HashMap) and a TrainDeparture object (which represents the value in
   * the HashMap), validates them and puts them in the {@code hashMap} variable in Table
   * </p>
   *
   * @param trainNumber               The unique train number that is set as the key in the hash map
   * @param trainDeparture            The trainDeparture entity that the train number belongs to
   * @throws IllegalArgumentException Throws Exception if the trainNumber
   *                                  already exists in the register
   */
  public boolean add(int trainNumber, TrainDeparture trainDeparture)
      throws TableException, TrainDepartureConstructorException {
    try {
      Verification.requireNonZeroOrLess(trainNumber,
          "The train number was 0 or less");
      if (hashMap.get(trainNumber) != null) {
        throw new IllegalArgumentException("The train-number already exists");
      }
      //make a deep copy of TrainDeparture
      this.hashMap.put(trainNumber, new TrainDeparture(trainDeparture));
    } catch (IllegalArgumentException e) {
      throw new TableException(e.getMessage());
    }
    return true;
  }

  /**
   * Sets track to a specific train-departure in the HashMap.
   * <p>
   * This method designates a track to the train-departure corresponding
   * to the given trainNumber
   * </p>
   *
   * @param trainNumber The trainDeparture's trainNumber
   * @param track       The track you want to assign
   */
  public boolean setTrackToTrain(int trainNumber, int track) throws TrackException {
    if (this.hashMap.get(trainNumber) != null) {
      this.hashMap.get(trainNumber).setTrack(track);
    } else {
      throw new TrackException("The train-number is not associated with a train-departure");
    }
    return true;
  }

  /**
   * Sets delay to a specific train-departure in the HashMap.
   * <p>
   * This method sets a delay to the train-departure corresponding
   * to the given trainNumber.
   * </p>
   *
   * @param trainNumber    The trainDeparture's trainNumber
   * @param delayInMinutes The delay you want to assign
   */
  public boolean setDelayToTrain(int trainNumber, int delayInMinutes) throws DelayException {
    if (this.hashMap.get(trainNumber) != null) {
      this.hashMap.get(trainNumber).setDelay(delayInMinutes);
    } else {
      throw new DelayException("The train-number is not associated with a train-departure");
    }
    return true;
  }

  /**
   * Removes trainDeparture objects that is before the specified time.
   * <p>
   * This method iterates through all the entries in the hashmap and removes
   * those where the TrainDeparture objects have an earlier departure than
   * the specified time
   * </p>
   *
   * @param time The LocalTime variable which marks a threshold time.
   *             All TrainDeparture-object with an earlier departure than
   *             time, gets removed
   */
  public void removeTrainDepartureBeforeTime(LocalTime time) {
    hashMap.entrySet().removeIf(map -> map.getValue().getDepartureTime().isBefore(time));
  }

  /**
   * Removes the train departure value that correspond with the
   * train number key in the HashMap in Table.
   *
   * @param trainNumber The train number key corresponding with the train departure value
   *                    you want to remove from the HashMap in table
   * @return            a boolean representing if the operation was successful or not
   */
  public boolean removeTrainByTrainNumber(int trainNumber) throws TableException {
    if (hashMap.get(trainNumber) != null) {
      hashMap.remove(trainNumber);
    } else {
      throw new TableException("TrainDeparture was not removed");
    }
    return true;
  }

  /**
   * Turn all the information about the TrainDeparture objects in the HashMap
   * into to a String.
   *
   * @return String
   */
  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    hashMap.forEach((trainNumber, trainDeparture) ->
        output.append(trainDeparture.toString(trainNumber)).append("\n")
    );
    return output.toString();
  }
}

