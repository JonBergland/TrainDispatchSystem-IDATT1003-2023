package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.*;

public class UserInterface {
    Scanner in = new Scanner(System.in);
    /**
     * Objekstvariabler
     */
    //private ArrayList<TrainDeparture> table;
    private Table table = new Table();

    /**
     * Konstruktør
     */
    public UserInterface(Table table){this.table = table;}

    public void printTrainDeparture() { //lager en metode som printer tog-oversikten ut til bruker
        Collections.sort(table.getTable(), new sortByTime()); //sorterer tabellen med hjelp av sortByTime
        table.getTable().forEach(
                t ->
                {String output = t.getOriginalDepartureTime() + " " + t.getLine() + " "
                        + t.getTrainNumber() + " " + t.getDestination();
                    if (t.getTrack() > -1) {
                        output += " Spor: " + t.getTrack();
                    }
                    if (t.getDelay().isAfter(LocalTime.of(0, 0))) {
                        output += " Forsinkelse: " + t.getDelay();
                    }
                    System.out.println(output);
        });
        String s = "-";
        System.out.println(s.repeat(30));
    }
    public void addTraindeparture(){
        Input input = new Input(table);
        //ber bruker skrive inn timen toget går
        int hour = input.hourInput();

        //ber bruker skrive inn minuttet toget går
        int minute = input.minuteInput();

        //ber brukeren skrive inn navnet på den nye linjen
        String line = input.lineInput();

        //ber brukeren skrive inn tognummeret
        int trainNumber = input.trainNumberInput();

        //ber brukeren skrive inn navnet til destinasjonen til linjen
        String destination = input.destinationInput();

        //ber brukeren om å sette spor til toget
        int track = input.trackInput();

        //ber bruker om å sette inn eventuell forsinkelse
        int delay = input.delayInput();

        TrainDeparture newTraindeparture = new TrainDeparture(LocalTime.of(hour, minute), line, trainNumber, destination, track, LocalTime.of(0, delay));
        table.getTable().add(newTraindeparture);
        printTrainDeparture();
    }

    public void setTrackToTrain(){
        int trainNumber = 0;
        do {
            table.printTrainNumberList();
            System.out.println("\nVelg en av togavgangene å tildele spor på");
            String trainNumberInput = in.nextLine();
            trainNumber = tryInt(trainNumberInput, 0);
        } while (!table.checkTrainNumber(trainNumber));

        System.out.println("Skriv inn hvilket spor toget skal på");
        String trackInput = in.nextLine();
        int track = tryInt(trackInput, -1);

        table.getTrainToTrainNumber(trainNumber).setTrack(track);

    }

    private Integer tryInt(String tall, int dummyValue){
        int output = 0;
        try {
            output = Integer.parseInt(tall);
        }
        catch (NumberFormatException e) {
            System.out.println(e + ". Det du skrev inn ble ikke akseptert og verdien "
                    + dummyValue + " ble satt istedet");
            output = dummyValue;
        }
        return output;
    }

    public void setTable(Table table){
        this.table = table;
    }
}
