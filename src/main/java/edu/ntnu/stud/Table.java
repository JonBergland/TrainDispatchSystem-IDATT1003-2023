package edu.ntnu.stud;

import java.util.ArrayList;
import java.util.List;

/**
 * Dette er enitetsklassen for listen over tog-avganger
 */
public class Table {
  /**
   * Objektsvaribel som skal inneholde alle TrainDeparture-objektene
   */
  private final List<TrainDeparture> table;

  public Table(){
    this.table = new ArrayList<>();
  }

  /**
   * Gets the list over trainDepartures
   *
   * @return List<TrainDeparture>
   */
  public List<TrainDeparture> getTable() { //en get-metode som returnere listen over TrainDeparture-objektene
    return this.table;
  }

  /**
   * @param newTrainNumber
   * @return boolean
   */
  public boolean checkTrainNumber(int newTrainNumber) {//en metode som sjekker om parameteren er et trainNumber
    boolean output = false; //setter variablen til false
    for (TrainDeparture i : table) { //løkker gjennom alle objektene i Table-objektet
      if (i.getTrainNumber() == newTrainNumber) { //sjekker om tognumrene er de samme
        output = true; //setter output til true hvis tognumrene er like og breaker
        break;
      }
    }
    return output; //returnerer den bolske verdien
  }

  public void printTrainNumberList() { //en metode som bruker et lambda-utrykk til å skrive ut alle tognumrene
    table.forEach(trainDeparture -> System.out.print(trainDeparture.getTrainNumber() + " \n"));
  }

  public void printDestinationList() { //en metode som skriver ut alle de unike destinasjonene
    //lager en ny arraylist som skal romme alle de unike destinasjonene
    ArrayList<String> unique = new ArrayList<>();
    table.forEach(trainDeparture -> { //bruker lambda-utrykk til å løkke igjen om alle trainDeparture-objektene
      String destination = trainDeparture.getDestination();
      if (!unique.contains(destination)) { //sjekker om listen ikke inneholder destinasjonen
        //hvis en ny, unik destinasjon printes destinasjonen ut og legges til i listen
        System.out.println(destination + " ");
        unique.add(destination);
      }
    });
  }

  /**
   * @param trainNumber
   * @return TrainDeparture
   */
  public TrainDeparture getTrainByTrainNumber(int trainNumber) { //en metode som finner toget med samsvarende tognummer
    for (TrainDeparture i : table) { //løkker igjennom alle objektene i Table-objektet
      if (i.getTrainNumber() == trainNumber) { //sjekker om tognummeret er likt
        return i; //returnerer objektet
      }
    }
    return null; //hvis det ikke er et tognummer med samsvarende tognummer, returneres null
  }

  /**
   * @param destination
   * @return ArrayList<TrainDeparture>
   */
  public ArrayList<TrainDeparture> getTrainByDestination(String destination) { //en metode for å finne togavganger
    //med lik destinasjon som parameteren
    //lager en tom liste som skal romme alle TrainDeparture-objektene
    ArrayList<TrainDeparture> destinationList = new ArrayList<>();
    table.forEach(t -> { //går igjennom alle objektene i Table-objektet
      //sjekker om destinasjonen er lik destinasjonen til TrainDeparture-objektet uansett størelse på bokstaver
      if (t.getDestination().equalsIgnoreCase(destination)) {
        destinationList.add(t); //legger til objektet i lista
      }
    });

    return destinationList; //returnerer lista
  }
}
