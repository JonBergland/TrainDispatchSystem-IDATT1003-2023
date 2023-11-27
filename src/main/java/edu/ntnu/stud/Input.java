package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Input {
  //private HashMap<Integer, TrainDeparture> hashMap;
  //private final Clock clock;

  /**
   * Konstruktør klassen for klokken
   */
  //får inn Table-objektet og klokke-objektet som parametere
  public Input() {
  }
  Scanner in = new Scanner(System.in);

  /**
   * Tries to convert the number into integer
   *
   * @param number
   * @return boolean
   */
  private boolean tryInt(String number) { //en metode for å sjekke om en streng er et tall (int)
    boolean isInt = true;
    try {
      Integer.parseInt(number); //sjekker om strengen kan konverteres til tall
    } catch (NumberFormatException e) {
      isInt = false;
     }
    return isInt;
  }

  /**
   *
   * @param print
   * @param dummyValue
   * @return int
   */
  public int intInput(String print, int dummyValue) { //en metode for en tall-input fra bruker
    System.out.println(print); //skriver ut en egendefinert streng til bruker
    String intInput = in.nextLine();
    int output = 0;
    if (tryInt(intInput)) {
      output = Integer.parseInt(intInput);
    }
    return output; //bruker intInput-metoden for å sjekke at input fra bruker er et tall
  }

  /**
   * @param print
   * @return String
   */
  public String stringInput(String print) { //en metode for en streng-input fra bruker
    System.out.println(print); //skriver ut en egendefinert streng til bruker
    return in.nextLine(); //returnerer bruker-inputet
  }

  /**
   * @param details
   * @return int
   */
  public int hourInput(String details) {
    String print = "Skriv inn tiden, i timer, " + details + " (mellom 0-23)";

    int dummyValue = 0;
    int hour = intInput(print, dummyValue);
    if (hour > 23 || hour < 0) {
      hour = dummyValue;
      System.out.println("Det som er satt inn er over den gitte rekkevidden. " + dummyValue + " ble satt inn istedet");
    }
    return hour; //retunerer bruker-input eller dummy-veri
  }

  /**
   * @param details
   * @return int
   */
  public int minuteInput(String details) { //en metode for minutt input fra bruker
    //en tekst-streng som skal skrives ut til bruker
    String print = "Skriv inn tiden, i minutter, " + details + " (mellom 0-59)";
    int dummyValue = 0; //setter en dummy-veri
    int minute = intInput(print, dummyValue); //får et tall-input fra bruker ved hjelp av metoden intInput
    if (minute > 59 || minute < 0) { //sjekker om inputen er innenfor begrensningen
      minute = 0; //hvis minutter satt inn er over 59 timer, blir veriden satt til dummyverdi
      //sender ut en feilmelding til bruker om at dummy-verdien ble satt inn istedet for inputen
      System.out.println("Det som er satt inn er over den gitte rekkevidden. " + dummyValue + " ble satt inn istedet");
    }
    return minute; //retunerer bruker-input eller dummy-veri
  }

  /**
   * A function that gives you a list over current train numbers
   * and lets you choose one of them
   *
   * @return int
   */
  public int chooseTrainNumber(HashMap<Integer, TrainDeparture> hashMap) {
    int trainNumber = 0;
    String print = "";
    do {
      System.out.print(print);
      for (int existingTrainNumber : hashMap.keySet()) {
        System.out.println(existingTrainNumber);
      }
      trainNumber = intInput("\nVelg en av togavgangene", 0);
      print = "Du må sette inn et tognummer fra listen\n"; //setter print til feilmeldingen
      //hvis trainNumber fra bruker ikke er i Table-objektet, løkker løkken på nytt
    } while (hashMap.get(trainNumber) == null);
    return trainNumber;
  }

}
