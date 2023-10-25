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

    public void menu(){

    }
    public void printTrainDeparture() { //lager en metode som printer tog-oversikten ut til bruker
        System.out.println("Time: " + clock.getClock());
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
        LocalTime delay = input.delayInput();

        TrainDeparture newTraindeparture = new TrainDeparture(LocalTime.of(hour, minute), line, trainNumber, destination, track, delay);
        table.getTable().add(newTraindeparture);
        printTrainDeparture();
    }

    public void setTrackToTrain(){
        int trainNumber = chooseTrainNumber();
        int track = input.trackInput();
        table.getTrainByTrainNumber(trainNumber).setTrack(track);
    }

    public void setDelayToTrain(){
        int trainNumber = chooseTrainNumber();
        LocalTime delay = input.delayInput();
        table.getTrainByTrainNumber(trainNumber).setDelay(delay);
    }

    public void findTrainByTrainNumber(){
        int trainNumber = chooseTrainNumber();
        table.getTrainByTrainNumber(trainNumber).toStrin();
    }

    public void findTrainByDestination(){
        ArrayList<TrainDeparture> destinationList = chooseDestination();
        destinationList.forEach(TrainDeparture::toStrin);
    }

    public void setNewTime(){
        updateClock();
        updateTable();
        printTrainDeparture();
    }

    private void updateClock(){
        int hour = 0;
        int minute = 0;
        LocalTime time;
        do {
            time = input.clockInput();
        } while(time.isBefore(clock.getClock()));

        clock.setClock(hour, minute);
        System.out.println(clock.getClock());
    }

    private void updateTable(){
        table.getTable().removeIf(trainDeparture -> trainDeparture.getOriginalDepartureTime().isBefore(clock.getClock()));
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

    private ArrayList<TrainDeparture> chooseDestination(){
        ArrayList<TrainDeparture> destinationList = new ArrayList<>();
        String utskrift = "";
        do {
            System.out.print(utskrift);
            table.printDestinationList();
            System.out.println("\nVelg en av destinasjonene");
            String destination = in.nextLine();
            destinationList = table.getTrainByDestination(destination);
            utskrift = "Du må sette inn en destinasjon fra listen\n";
        } while (destinationList.isEmpty());
        return destinationList;
    }

    private void menuList(){
        int menuChoice = 0;
        do {
            System.out.println("[1] Vis tog avgangene\n" +
                    "[2] Legg til ny togavgang\n" +
                    "[3] Tildel spor til avgang\n" +
                    "[4] Legg inn forsinkelse\n" +
                    "[5] Søk etter togavgang basert på tognummer\n" +
                    "[6] Søk etter togavgang basert på destinasjon\n" +
                    "[7] Oppdater klokken\n" +
                    "[8] Avslutt\n");

            menuChoice = input.intInput("Skriv inn tallet som korresponderer med handlingen du vil utføre: ", 0);
        } while (menuChoice == 0);
    }

    private void doOperation(int menuChoice){
        if(menuChoice == 1){
            printTrainDeparture();
        } else if (menuChoice == 2) {
            addTraindeparture();
        } else if (menuChoice == 3) {
            setTrackToTrain();
        } else if (menuChoice == 4) {
            setDelayToTrain();
        } else if (menuChoice == 5) {
            findTrainByTrainNumber();
        } else if (menuChoice == 6) {
            findTrainByDestination();
        } else if (menuChoice == 7) {
            updateClock();
        } else {
            System.exit(0);
        }


    }

    public void setTable(Table table){
        this.table = table;
    }
}
