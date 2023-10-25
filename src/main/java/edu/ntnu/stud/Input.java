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

    public int hourInput(String detaljer){
        System.out.println("Skriv inn ved hvilken time " + detaljer);
        String hourInput = in.nextLine();
        int dummyValue = 0;
        int hour = tryInt(hourInput, 0);
        if (hour > 23) {hour = 0;} //hvis time satt inn er over 23 timer, blir veriden satt til dummyverdi
        System.out.println("Det som er satt inn er over den gitte rekkevidden. "+dummyValue+ " ble satt inn istedet");
        System.out.println(hour);
        return hour;
    }

    public int minuteInput(String detaljer){
        System.out.println("Skriv inn ved hvilken minutt" + detaljer);
        String minuteInput = in.nextLine();
        int dummyValue = 0;
        int minute = tryInt(minuteInput, dummyValue);
        if (minute > 59) {minute = 0;} //hvis minutter satt inn er over 59 timer, blir veriden satt til dummyverdi
        System.out.println("Det som er satt inn er over den gitte rekkevidden."+dummyValue+ "ble satt inn istedet");
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
