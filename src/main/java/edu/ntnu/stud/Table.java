package edu.ntnu.stud;

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

  public Table(){
    this.hashMap = new HashMap<Integer, TrainDeparture>();
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
   * Adds a map of key (trainNumber) and value (trainDeparture) to Hashmap
   *
   * @param trainNumber
   * @param trainDeparture
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
   * Sets a new hashMap to Table
   *
   * @param hashMap
   */
  public void setHashMap(HashMap<Integer, TrainDeparture> hashMap) {
    this.hashMap = hashMap;
  }


  /**
   * @param newTrainNumber
   * @return boolean
   */
  public boolean checkTrainNumber(int newTrainNumber) {//en metode som sjekker om parameteren er et trainNumber
    boolean output = false; //setter variablen til false
    if (this.hashMap.get(newTrainNumber) != null){
      output = true;
    }
    return output; //returnerer den bolske verdien
  }

  /**
   * Prints all the trainDeparture's trainNumber
   */
  public void printTrainNumberList() { //en metode som bruker et lambda-utrykk til å skrive ut alle tognumrene
    for (int trainNumber : hashMap.keySet()) {
      System.out.println(trainNumber);
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
   * @param trainNumber
   * @return TrainDeparture

  public TrainDeparture getTrainByTrainNumber(int trainNumber) { //en metode som finner toget med samsvarende tognummer
    return hashMap.get(trainNumber); //hvis det ikke er et tognummer med samsvarende tognummer, returneres null
  }*/

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
}
