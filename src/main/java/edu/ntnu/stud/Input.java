package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
  private Table table = new Table();
  private Clock clock;

  /**
   * Konstruktør klassen for klokken
   */
  //får inn Table-objektet og klokke-objektet som parametere
  public Input(Table table, Clock clock) {
    this.table = table;
    this.clock = clock;
  }

  Scanner in = new Scanner(System.in);

  /**
   * @param tall
   * @param dummyValue
   * @return int
   */
  private int tryInt(String tall, int dummyValue) { //en metode for å sjekke om en streng er et tall (int)
    int output = 0;
    try {
      output = Integer.parseInt(tall); //sjekker om strengen kan konverteres til tall
    } catch (NumberFormatException e) { //hvis den ikke kan konverteres blir en feilmelding skrevet ut til bruker
      System.out.println(e + ". Det du skrev inn ble ikke akseptert og verdien "
          + dummyValue + " ble satt istedet");
      output = dummyValue; //setter resultatet til dummy-verdien hvis strengen ikke kunne konverteres
    }
    return output; //returnerer den konverterte strengen eller dummy-verdien
  }

  /**
   *
   * @param print
   * @param dummyValue
   * @return int
   */
  public int intInput(String print, int dummyValue) { //en metode for en tall-input fra bruker
    System.out.println(print); //skriver ut en egendefinert streng til bruker
    String intInput = in.nextLine(); //får inn bruker-input
    return tryInt(intInput, dummyValue); //bruker intInput-metoden for å sjekke at input fra bruker er et tall
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
  public int hourInput(String details) { //en metode for time input fra bruker
    //lager en streng som skal skrives ut til bruker
    String print = "Skriv inn tiden, i timer, " + details + " (mellom 0-23)";
    int dummyValue = 0; //setter dummy-verdi
    int hour = intInput(print, dummyValue); //bruker tall-input metoden for å få et tall fra bruker
    if (hour > 23 || hour < 0) { //sjekker om tallet er mellom 0 og 23 (innenfor tidsbegrensningen til programmet)
      hour = 0; //hvis time satt inn er over 23 timer, blir verdien satt til dummyverdi
      //skriver ut en feilmelding til bruker om at dummy-verdien er blitt satt istedet for deres input
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
   * @return int
   */
  public int trainNumberInput() {
    int trainNumber = intInput("Skriv inn et nytt, unikt tognummer", 0);
    //kjører en løkke som kjører så lenge tognummeret ikke er unikt eller inputen er dummyValue
    while (table.checkTrainNumber(trainNumber) || trainNumber == 0) {
      trainNumber = intInput("Det nummeret eksisterer allerede. Skriv inn et nytt, unikt tognummer", 0);
    }
    return trainNumber;
  }

  /**
   * @return int
   */
  public int trackInput() { //en metode for track-input fra bruker
    //lager en streng som skal skrives ut til bruker
    String print = "Skriv inn ved hvilken spor toget skal gå fra. Hvis ikke bestemt, skriv inn -1";
    //får track fra bruker ved hjelp av intInput-metoden
    int track = intInput(print, -1);
    return track; //returnerer den brukervalgte verdien
  }

  /**
   * @return LocalTime            Returns hours and minutes set by user
   */
  public LocalTime delayInput() { //en metode for delay-input fra bruker
    String print = "toget er forsinket med"; //en tekst-streng som skal skrives ut til bruker
    //returnerer delay ved hjelp av metodene hourInput og minuteInput
    return LocalTime.of(hourInput(print), minuteInput(print));
  }

  /**
   * @return LocalTime
   */
  public LocalTime clockInput() { //en metode for å få et nytt klokkeslett å sette objektet Clock til
    String print = "du vil sette klokken til"; //streng som skal skrives ut til bruker
    //bruker metodene hourInput og minuteInput for å få en ny tid
    LocalTime time = LocalTime.of(hourInput(print), minuteInput(print));
    if (time.isBefore(clock.getClock())) { //sjekker om tiden gitt av bruker er før nåværende tid
      //skriver feilmelding til bruker hvis tiden er før
      System.out.println("Tiden som ble satt inn var før det klokken er nå");
      time = clock.getClock(); //setter tiden til den gamle tiden
    }
    return time; //returnerer en ny tid eller samme, nåværende verdi
  }

  /**
   * @return int
   */
  public int chooseTrainNumber() { //en metode for å velge et trainNumber fra en liste
    int trainNumber = 0; //initialiserer variablen trainNumber før løkken
    String print = ""; //lager en tom streng
    do { //en løkke som printer listen over trainNumbers og lar bruker velge en av dem
      //printer en streng som er tom første gjennomgang av løkken og har en feilmelding de neste gjennomgangene
      System.out.print(print);
      table.printTrainNumberList(); //printer listen over trainNumbers
      //får inn et trainNumber fra bruker
      trainNumber = intInput("\nVelg en av togavgangene", 0);
      print = "Du må sette inn et tognummer fra listen\n"; //setter print til feilmeldingen
      //hvis trainNumber fra bruker ikke er i Table-objektet, løkker løkken på nytt
    } while (!table.checkTrainNumber(trainNumber));
    return trainNumber;
  }

  /**
   * @return ArrayList<TrainDeparture>
   */
  public ArrayList<TrainDeparture> chooseDestination() { //en metode for å velge en destinasjon fra en liste
    ArrayList<TrainDeparture> destinationList; //lager en tom arraylist
    String print = ""; //lager en tom streng
    do { //en do-while løkke som printer liste over destinasjonene og ber bruker velge en av dem
      //printer en streng som er tom første gjennomgang av løkken og har en feilmelding de neste gjennomgangene
      System.out.print(print);
      table.printDestinationList(); //printer listen over alle unike destinasjoner
      //bruker stringInput-metoden for å få streng-input fra bruker
      String destination = stringInput("\nVelg en av destinasjonene");
      //leter gjennom Table-objektet etter TrainDepartures med samme destinasjon
      destinationList = table.getTrainByDestination(destination);
      print = "Du må sette inn en destinasjon fra listen\n"; //setter print til feilmeldingen
      //hvis listen er tom har ikke programmet funnet TrainDeparture med lik destinasjon og løkken løkkes igjen
    } while (destinationList.isEmpty());
    //når programmet har en liste med TrainDepartures med lik destinasjon, returneres listen
    return destinationList;
  }
}
