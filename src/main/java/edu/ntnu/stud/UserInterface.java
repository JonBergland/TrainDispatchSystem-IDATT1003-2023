package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.*;

public class UserInterface {
    Scanner in = new Scanner(System.in);
    /**
     * Objekstvariabler
     */
    private Table table = new Table();
    private Clock clock = new Clock();
    private final Input input = new Input(table, clock);


    /**
     * Konstruktør
     */
    public void init(){
        table.getTable().add(new TrainDeparture(LocalTime.of(-12, 15), "", 601, "Frognerseteren", -1, LocalTime.of(0, 0)));
        table.getTable().add(new TrainDeparture(LocalTime.of(15, 30), "Linje 2", 305, "Sognsvann",-1,  LocalTime.of(0, 0)));
        table.getTable().add(new TrainDeparture(LocalTime.of(10, 30), "Linje 3", 404, "Bergkrystallen", -1, LocalTime.of(0, 0)));
        table.getTable().add(new TrainDeparture(LocalTime.of(10, 40), "Linje 4", 406, "Bergkrystallen", -1, LocalTime.of(0, 0)));

        doOperation(menuList());
    }
    public void printTrainDeparture() { //lager en metode som printer tog-oversikten ut til bruker
        System.out.println("Time: " + clock.getClock());
        table.getTable().sort(new sortByTime()); //sorterer tabellen med hjelp av sortByTime
        table.getTable().forEach(t-> System.out.println(t.toStrin()));
    }
    public void addTraindeparture(){
        //ber bruker skrive inn timen toget går
        int hour = input.hourInput("toget går");

        //ber bruker skrive inn minuttet toget går
        int minute = input.minuteInput("toget går");

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
        System.out.println(table.getTrainByTrainNumber(trainNumber).toStrin());
    }

    public void findTrainByDestination(){
        ArrayList<TrainDeparture> destinationList = chooseDestination();
        destinationList.forEach(t-> System.out.println(t.toStrin()));
    }

    public void setNewTime(){
        updateClock();
        updateTable();
    }

    private void updateClock(){
        int hour = 0;
        int minute = 0;
        LocalTime time;
        do {
            time = input.clockInput();
        } while(time.isBefore(clock.getClock()));

        clock.setClock(time);
        updateTable();
    }

    private void updateTable(){
        table.getTable().removeIf(trainDeparture -> trainDeparture.getDepartureTime().isBefore(clock.getClock()));
    }

    private int chooseTrainNumber(){
        int trainNumber = 0;
        String utskrift = "";
        do {
            System.out.print(utskrift);
            table.printTrainNumberList();

            trainNumber = input.intInput("\nVelg en av togavgangene", 0);
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

    private int menuList(){
        int menuChoice = 0;
        do {
            String s = "-";
            System.out.println(s.repeat(30));
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
        return menuChoice;
    }

    private void doOperation(int menuChoice){
        do {
            switch (menuChoice) {
                case 1:
                    printTrainDeparture();
                    break;
                case 2:
                    addTraindeparture();
                    break;
                case 3:
                    setTrackToTrain();
                    break;
                case 4:
                    setDelayToTrain();
                    break;
                case 5:
                    findTrainByTrainNumber();
                    break;
                case 6:
                    findTrainByDestination();
                    break;
                case 7:
                    updateClock();
                    break;
                case 8:
                    System.exit(0);

                default:
                    System.out.println("Tallet du satte inn korosponderer ikke med et tall fra listen");

            }

            menuChoice = menuList();

        } while (menuChoice != 8);

        /*
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
        }*/
    }
}
