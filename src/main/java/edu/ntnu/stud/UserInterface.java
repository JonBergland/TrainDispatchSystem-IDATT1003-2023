package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.*;

public class UserInterface {
    Scanner in = new Scanner(System.in);
    /**
     * Objekstvariabler
     */
    private Table table = new Table();
    private Input input;

    private Clock clock = new Clock();

    /**
     * Konstruktør
     */
    public UserInterface(Table table){this.table = table; this.input = new Input(this.table);}

    public void printTrainDeparture() { //lager en metode som printer tog-oversikten ut til bruker
        Collections.sort(table.getTable(), new sortByTime()); //sorterer tabellen med hjelp av sortByTime
        table.getTable().forEach(TrainDeparture::toStrin);
        String s = "-";
        System.out.println(s.repeat(30));
    }
    public void addTraindeparture(){
        //ber bruker skrive inn timen toget går
        int hour = input.hourInput("toget går (mellom 0-23");

        //ber bruker skrive inn minuttet toget går
        int minute = input.minuteInput("toget går (mellom 0-59)");

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
        int trainNumber = chooseTrainNumber();

        System.out.println("Skriv inn hvilket spor toget skal på");
        String trackInput = in.nextLine();
        int track = tryInt(trackInput, -1);

        table.getTrainToTrainNumber(trainNumber).setTrack(track);
    }

    public void setDelayToTrain(){
        int trainNumber = chooseTrainNumber();

        System.out.println("Skriv hvor forsinket toget er i timer:");
        String delayHourInput = in.nextLine();
        int delayHour = tryInt(delayHourInput, 0);

        System.out.println("Skriv hvor forsinket toget er i minutter:");
        String delayMinuteInput = in.nextLine();
        int delayMinute = tryInt(delayMinuteInput, 0);

        table.getTrainToTrainNumber(trainNumber).setDelay(LocalTime.of(delayHour, delayMinute));
    }

    public void findTrainDeparture(){
        int trainNumber = chooseTrainNumber();
        table.getTrainToTrainNumber(trainNumber).toStrin();
    }

    public void updateClock(){
        int hour = input.hourInput("du vil sette klokken til");
        int minute = input.minuteInput("du vil sette klokken til");
        clock.setClock(hour, minute);
        System.out.println(clock.getClock());
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
    private int chooseTrainNumber(){
        int trainNumber = 0;
        String utskrift = "";
        do {
            System.out.print(utskrift);
            table.printTrainNumberList();
            System.out.println("\nVelg en av togavgangene");
            String trainNumberInput = in.nextLine();
            trainNumber = tryInt(trainNumberInput, 0);
            utskrift = "Du må sette inn et tognummer fra listen\n";
        } while (!table.checkTrainNumber(trainNumber));
        return trainNumber;
    }

    public void setTable(Table table){
        this.table = table;
    }
}
