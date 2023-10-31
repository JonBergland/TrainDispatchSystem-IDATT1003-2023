package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.*;

/**
 * Dette er klassen for brukergrensesnittet
 */
public class UserInterface {
    /**
     * Objekstvariabler
     * Lager nye instanser av hvert objekt fordi dette er klassen som brukeren
     * kommer til å bruke for å kommunisere med resten av klassene
     */
    private Table table = new Table();
    private final Clock clock = new Clock();
    //setter input til final fordi den bare henter brukerinputt eller bruker mutable-metoder som endrer på andre objekt
    private final Input input = new Input(table, clock);


    public void init() { //metode for oppstart av programmet
        // Oppretter først 4 objekter av klassen TrainDeparture og legger dem inn i et objekt av klassen Table
        table.getTable().add(new TrainDeparture(LocalTime.of(12, 15), "", 601, "Frognerseteren", -1, LocalTime.of(0, 0)));
        table.getTable().add(new TrainDeparture(LocalTime.of(15, 30), "Linje 2", 305, "Sognsvann", -1, LocalTime.of(0, 0)));
        table.getTable().add(new TrainDeparture(LocalTime.of(10, 30), "Linje 3", 404, "Bergkrystallen", -1, LocalTime.of(0, 0)));
        table.getTable().add(new TrainDeparture(LocalTime.of(10, 40), "Linje 4", 406, "Bergkrystallen", -1, LocalTime.of(0, 0)));
        //kjører metodene som printer menyen til bruker, tar inn hva bruker velger og kjører tilhørende metode
        doOperation(menuList());
    }

    public void printTrainDeparture() { //lager en metode som printer tog-oversikten ut til bruker
        System.out.println("Time: " + clock.getClock()); //printer først den nåværende tiden
        table.getTable().sort(new sortByTime()); //sorterer tabellen med hjelp av sortByTime
        //bruker en lambda expression for å skrive ut hvert TrainDeparture-objekt i Table-objektet
        table.getTable().forEach(t -> System.out.println(t.toStrin()));
    }

    public void addTraindeparture() {//metoden for å legge til en objekt av klassen TrainDeparture
        //ber bruker skrive inn timen toget går
        int hour = input.hourInput("toget går");

        //ber bruker skrive inn minuttet toget går
        int minute = input.minuteInput("toget går");

        //ber brukeren skrive inn navnet på den nye linjen
        String line = input.stringInput("Skriv inn navnet på linjen");

        //ber brukeren skrive inn tognummeret
        int trainNumber = input.trainNumberInput();

        //ber brukeren skrive inn navnet til destinasjonen til linjen
        String destination = input.stringInput("Skriv inn navnet på destinasjonen");

        //ber brukeren om å sette spor til toget
        int track = input.trackInput();

        //ber bruker om å sette inn eventuell forsinkelse
        LocalTime delay = input.delayInput();

        //Lager en objekt av TrainDeparture med verdiene vi fikk fra bruker og legger den inn i Table-objektet
        TrainDeparture newTraindeparture = new TrainDeparture(LocalTime.of(hour, minute), line, trainNumber, destination, track, delay);
        table.getTable().add(newTraindeparture);
    }

    public void setTrackToTrain() { //metode for å sette spor til en togavgang basert på tognummeret til avgangen
        //bruker metoder fra klassen Input for å få trainNumber og track fra bruker
        int trainNumber = input.chooseTrainNumber();
        int track = input.trackInput();
        //setter track til valgt track på objekt av TrainDeparture som samsvarer til riktig trainNumber
        table.getTrainByTrainNumber(trainNumber).setTrack(track);
    }

    public void setDelayToTrain() {
        //bruker metoder fra klassen Input for å få trainNumber og delay fra bruker
        int trainNumber = input.chooseTrainNumber();
        LocalTime delay = input.delayInput();
        //setter delay til valgt delay på objekt av TrainDeparture som samsvarer til riktig trainNumber
        table.getTrainByTrainNumber(trainNumber).setDelay(delay);
    }

    public void findTrainByTrainNumber() { //en metode som finner en TrainDeparture basert på trainNumber
        //bruker velger en av de eksisterende trainNumber med hjelp av input-klassen
        int trainNumber = input.chooseTrainNumber();
        //bruker toStrin() for å skrive ut riktig TrainDeparture
        System.out.println(table.getTrainByTrainNumber(trainNumber).toStrin());
    }

    public void findTrainByDestination() { //en metode som finner en TrainDeparture basert på destination
        //lager en arrayList som tar inn en liste med trainDepartures som har valgt destination som destination
        ArrayList<TrainDeparture> destinationList = input.chooseDestination();
        destinationList.forEach(t -> System.out.println(t.toStrin())); //bruker et lambda-utrykk for å skrive ut objektene
    }

    private void updateClock() { //en metode som oppdaterer klokka til ny tid satt av bruker
        LocalTime time;
        do { //bruker en do-while løkke som får input av bruker om nytt klokkeslett
            time = input.clockInput();
        } while (time.isBefore(clock.getClock())); //gjentas hvis klokkeslettet gitt er før det nåværende klokkeslettet
        //setter klokka til nytt klokkeslett som er det samme eller etter det gamle klokkeslettet
        clock.setClock(time);
        //fjerner togavganger som har vært med metoden updateTable
        updateTable();
    }

    private void updateTable() {
        //en metode for å fjerne objekter av TrainDeparture fra Table hvis departureTime er før klokkeslettet
        table.getTable().removeIf(t -> t.getDepartureTime().isBefore(clock.getClock()));
    }

    private int menuList() { //en metode som lager en meny over funksjonene til programmet og lar bruker velge en av dem
        int menuChoice;
        do {
            String s = "-"; //en streng som skal markere et skille mellom tidligere kjørt funksjon og menyen
            System.out.println(s.repeat(30));
            //skriver ut funksjonene til bruker
            System.out.println("""
                    [1] Vis tog avgangene
                    [2] Legg til ny togavgang
                    [3] Tildel spor til avgang
                    [4] Legg inn forsinkelse
                    [5] Søk etter togavgang basert på tognummer
                    [6] Søk etter togavgang basert på destinasjon
                    [7] Oppdater klokken
                    [8] Avslutt
                    """);

            //får inn valgt funksjon fra bruker og legger i variablen menuChoice
            menuChoice = input.intInput("Skriv inn tallet som korresponderer med handlingen du vil utføre: ", 0);
            //hvis det som ble satt inn ikke var et tall, blir dummy-verdien 0 satt inn og løkka gjentas
        } while (menuChoice == 0);
        return menuChoice; //returnerer valgt int verdi
    }

    private void doOperation(int menuChoice) { //en metode som tar inn tallverdien som bruker satte inn som parameter
        do { //kjører en do-while løkke som kjører så lenge meny-valget ikke er 8
            switch (menuChoice) { //bruker switch til å kjøre metoden som tilsvarer til den brukervalgte verdien
                case 1 -> printTrainDeparture();
                case 2 -> addTraindeparture();
                case 3 -> setTrackToTrain();
                case 4 -> setDelayToTrain();
                case 5 -> findTrainByTrainNumber();
                case 6 -> findTrainByDestination();
                case 7 -> updateClock();
                case 8 -> System.exit(0);
                default -> System.out.println("Tallet du satte inn samsvarer ikke med et tall fra listen");
            }
            //finner ny menuChoice ved hjelp av menuList-metoden
            menuChoice = menuList();
            //løkken kjøres "uendelig" fordi hvis bruker velger 8, avsluttes programmet med hjelp av system.exit
        } while (menuChoice != 8);
    }
}