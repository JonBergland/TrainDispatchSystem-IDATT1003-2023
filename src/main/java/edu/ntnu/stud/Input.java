package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.Scanner;

public class Input {
    private Table table = new Table();

    /**
     * Konstruktør
     */
    public Input(Table table){this.table = table;}
    Scanner in = new Scanner(System.in);

    public int hourInput(){
        System.out.println("Skriv inn ved hvilken time toget går" );
        String hourInput = in.nextLine();
        int hour = tryInt(hourInput, 0);
        System.out.println(hour);
        return hour;
    }

    public int minuteInput(){
        System.out.println("Skriv inn ved hvilken minutt toget går" );
        String minuteInput = in.nextLine();
        int minute = tryInt(minuteInput, 0);
        System.out.println(minute);
        return minute;
    }

    public String lineInput(){
        System.out.println("Skriv inn navnet på linjen");
        String line = in.nextLine();
        return line;
    }

    public int trainNumberInput(){
        System.out.println("Skriv inn et nytt, unikt tognummer" );
        String trainNumberUser = in.nextLine();
        int trainNumber = tryInt(trainNumberUser, 0);
        System.out.println(trainNumber);
        //kjører en løkke som kjører så lenge tognummeret ikke er unikt
        while(table.checkTrainNumber(trainNumber)) {
            System.out.println("Det nummeret eksisterer allerede. Skriv inn et nytt, unikt tognummer");
            trainNumberUser = in.nextLine();
            trainNumber = tryInt(trainNumberUser, 0);
            System.out.println(trainNumber);
        }
        return trainNumber;
    }

    public String destinationInput(){
        System.out.println("Skriv inn navnet på destinasjonen");
        String destination = in.nextLine();
        return destination;
    }

    public int trackInput(){
        System.out.println("Skriv inn ved hvilken spor toget skal gå fra. Hvis ikke bestemt, skriv inn -1");
        String trackInput = in.nextLine();
        int track = tryInt(trackInput, -1);
        return track;
    }

    public int delayInput(){
        System.out.println("Skriv inn eventuell forsinkelse. Hvis ingen, skriv inn 0");
        String delayInput = in.nextLine();
        int delay = tryInt(delayInput, 0);
        return delay;
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



}
