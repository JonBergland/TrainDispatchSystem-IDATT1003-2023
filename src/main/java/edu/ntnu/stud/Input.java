package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.Scanner;

public class Input {
    private Table table = new Table();
    private Clock clock;

    /**
     * Konstruktør
     */
    public Input(Table table, Clock clock){this.table = table; this.clock = clock;}
    Scanner in = new Scanner(System.in);

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

    public int intInput(String print, int dummyValue){
        System.out.println(print);
        String intInput = in.nextLine();
        int number = tryInt(intInput, dummyValue);
        return number;
    }

    public int hourInput(String details){
        String print = "Skriv inn tiden, i timer, " + details + " (mellom 0-23)";
        int dummyValue = 0;
        int hour = intInput(print, dummyValue);
        if (hour > 23 || hour < 0) {
            hour = 0; //hvis time satt inn er over 23 timer, blir veriden satt til dummyverdi
            System.out.println("Det som er satt inn er over den gitte rekkevidden. "+dummyValue+ " ble satt inn istedet");
        }
        return hour;
    }

    public int minuteInput(String details){
        String print = "Skriv inn tiden, i minutter, " + details + " (mellom 0-59)";
        int dummyValue = 0;
        int minute = intInput(print, dummyValue);
        if (minute > 59 || minute < 0) {
            minute = 0; //hvis minutter satt inn er over 59 timer, blir veriden satt til dummyverdi
            System.out.println("Det som er satt inn er over den gitte rekkevidden. "+dummyValue+ " ble satt inn istedet");
        }
        return minute;
    }

    public String lineInput(){
        System.out.println("Skriv inn navnet på linjen");
        String line = in.nextLine();
        return line;
    }
    public int trainNumberInput(){
        int trainNumber = intInput("Skriv inn et nytt, unikt tognummer", 0);
        //kjører en løkke som kjører så lenge tognummeret ikke er unikt eller inputen er dummyValue
        while(table.checkTrainNumber(trainNumber) || trainNumber == 0) {
            trainNumber = intInput("Det nummeret eksisterer allerede. Skriv inn et nytt, unikt tognummer", 0);
        }
        return trainNumber;
    }
    public String destinationInput(){
        System.out.println("Skriv inn navnet på destinasjonen");
        return in.nextLine();
    }
    public int trackInput(){
        String print = "Skriv inn ved hvilken spor toget skal gå fra. Hvis ikke bestemt, skriv inn -1";
        int track = intInput(print, -1);
        return track;
    }
    public LocalTime delayInput(){
        String print = "toget er forsinket med";
        return LocalTime.of(hourInput(print), minuteInput(print));
    }
    public LocalTime clockInput(){
        String print = "du vil sette klokken til";
        LocalTime time = LocalTime.of(hourInput(print), minuteInput(print));
        if (time.isBefore(clock.getClock())) {
            System.out.println("Tiden som ble satt inn var før det klokken er nå");
            time = clock.getClock();
        }

        return time;
    }
}
