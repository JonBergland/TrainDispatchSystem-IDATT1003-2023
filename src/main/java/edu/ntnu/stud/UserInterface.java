package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.*;

public class UserInterface {
    /**
     * Objekstvariabler
     */
    //private ArrayList<TrainDeparture> table;
    private Table table = new Table();

    /**
     * Konstruktør
     */
    public UserInterface(Table table){this.table = table;}

    public void printTrainDeparture() {
        Collections.sort(table.getTable(), new sortByTime());
        for (TrainDeparture i : table.getTable()) {
            String output = i.getOriginalDepartureTime() + " " + i.getLine() + " " + i.getDestination();
            if (i.getTrack() > -1) {
                output += " Spor: " + i.getTrack();
            }
            if (i.getDelay().isAfter(LocalTime.of(0, 0))) {
                output += " Forsinkelse: " + i.getDelay();
            }
            System.out.println(output);
        }
    }
    public void addTraindeparture(){
        Scanner in = new Scanner(System.in);
        //ber bruker skrive inn timen toget går
        System.out.println("Skriv inn ved hvilken time toget går" );
        String hourInput = in.nextLine();
        int hour = tryInt(hourInput, 0);
        System.out.println(hour);

        //ber bruker skrive inn minuttet toget går
        System.out.println("Skriv inn ved hvilken minutt toget går" );
        String minuteInput = in.nextLine();
        int minute = tryInt(minuteInput, 0);
        System.out.println(minute);

        //ber brukeren skrive inn navnet på den nye linjen
        System.out.println("Skriv inn navnet på linjen");
        String line = in.nextLine();
        System.out.println(line);

        //ber brukeren skrive inn tognummeret
        System.out.println("Skriv inn et nytt, unikt tognummer" );
        String trainNumberInput = in.nextLine();
        int trainNumber = tryInt(trainNumberInput, 0);
        System.out.println(trainNumber);
        //kjører en løkke som kjører så lenge tognummeret ikke er unikt
        while(table.checkTrainNumber(trainNumber)) {
            System.out.println("Det nummeret eksisterer allerede. Skriv inn et nytt, unikt tognummer");
            trainNumberInput = in.nextLine();
            trainNumber = tryInt(trainNumberInput, 0);
            System.out.println(trainNumber);
        }

        //ber brukeren skrive inn navnet til destinasjonen til linjen
        System.out.println("Skriv inn navnet på destinasjonen");
        String destination = in.nextLine();
        System.out.println(destination);

        //ber brukeren om å sette spor til toget
        System.out.println("Skriv inn ved hvilken spor toget skal gå fra. Hvis ikke bestemt, skriv inn -1");
        String trackInput = in.nextLine();
        int track = tryInt(trackInput, -1);
        System.out.println(track);

        //ber bruker om å sette inn eventuell forsinkelse
        System.out.println("Skriv inn eventuell forsinkelse. Hvis ingen, skriv inn 0");
        String delayInput = in.nextLine();
        int delay = tryInt(delayInput, 0);
        System.out.println(delay);


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
