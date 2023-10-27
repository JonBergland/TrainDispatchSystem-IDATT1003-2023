package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;

public class TestClient {

    public static void main(String[] args) {
        TestClient testClient = new TestClient();

        testClient.alleTester();
    }

    public void alleTester(){
        String utskrift = "";

        //tester for trainDeparture
        utskrift += testTrainDepartureGet();
        utskrift += testTrainDepartureSet();
        utskrift += testTrainDeparturetoStrin();

        //tester for table
        utskrift += testTableGet();

        System.out.println(utskrift);
    }

    /**
     *Enhetstester for TrainDeparture
     */
    private String testTrainDepartureGet(){ //test som tester alle get metodene i TrainDeparture
        LocalTime originalDepartureTime = LocalTime.of(8,30);
        String line = "L4";
        int trainNumber = 204;
        String destination = "Oslo";
        int track = 3;
        LocalTime delay = LocalTime.of(0,0);

        TrainDeparture trainDeparture = new TrainDeparture(originalDepartureTime, line, trainNumber, destination, track, delay);
        String utskrift = "";
        
        if (!trainDeparture.getOriginalDepartureTime().equals(originalDepartureTime)){
            utskrift += "Avgangstiden er ikke den samme\n";
        }
        if (!trainDeparture.getLine().equals(line)) {
            utskrift += "Linjen er ikke den samme\n";
        }
        if (trainDeparture.getTrainNumber() != trainNumber) {
            utskrift += "Tognummeret er ikke det samme\n";
        }
        if (!trainDeparture.getDestination().equals(destination)) {
            utskrift += "Destinasjonen er ikke den samme\n";
        }
        if (trainDeparture.getTrack() != track) {
            utskrift += "Sporet er ikke det samme\n";
        }
        if (!trainDeparture.getDelay().equals(delay)){
            utskrift += "Forsinkelsen er ikke det samme\n";
        }

        if (utskrift.isEmpty()){
            utskrift += "Test av get-metodene i TrainDeparture var vellykket\n";
        }
        return utskrift;
    }
    private String testTrainDepartureSet(){
        LocalTime originalDepartureTime = LocalTime.of(8,30);
        String line = "L4";
        int trainNumber = 204;
        String destination = "Oslo";
        int track = -1;
        LocalTime delay = LocalTime.of(0,0);

        TrainDeparture trainDeparture = new TrainDeparture(originalDepartureTime, line, trainNumber, destination, track, delay);
        String utskrift = "";

        int newTrack = 3;
        LocalTime newDelay = LocalTime.of(1, 30);
        trainDeparture.setTrack(newTrack);
        trainDeparture.setDelay(newDelay);

        if (trainDeparture.getTrack() != newTrack){
            utskrift += "Det nye sporet er ikke satt\n";
        }
        if (!trainDeparture.getDelay().equals(newDelay)){
            utskrift += "Den nye forsinkelsen er ikke satt\n";
        }
        if (utskrift.isEmpty()){
            utskrift += "Test av set-metodene i Traindeparture er vellykket\n";
        }
        return utskrift;
    }
    private String testTrainDeparturetoStrin(){
        LocalTime originalDepartureTime = LocalTime.of(8,30);
        String line = "L4";
        int trainNumber = 204;
        String destination = "Oslo";
        int track = -1;
        LocalTime delay = LocalTime.of(0,0);

        TrainDeparture trainDeparture = new TrainDeparture(originalDepartureTime, line, trainNumber, destination, track, delay);
        String utskrift = "";

        //tester toStrin() med track og delay usynlige
        String toStrinTest = trainDeparture.getOriginalDepartureTime() + " " + trainDeparture.getLine() + " "
                + trainDeparture.getTrainNumber() + " " + trainDeparture.getDestination();
        if (!trainDeparture.toStrin().equals(toStrinTest)){
            utskrift += "toStrin() printer ikke riktig for usynlige spor og forsinkelser\n";
        }

        //tester toStrin() med track synlig og delay usynlige
        int newTrack = 3;
        trainDeparture.setTrack(newTrack);
        toStrinTest = trainDeparture.getOriginalDepartureTime() + " " + trainDeparture.getLine() + " "
                + trainDeparture.getTrainNumber() + " " + trainDeparture.getDestination() + " Spor: " + trainDeparture.getTrack();

        if (!trainDeparture.toStrin().equals(toStrinTest)){
            utskrift += "toString() printer ikke riktig for synlige spor\n";
        }

        LocalTime newDelay = LocalTime.of(1, 30);
        trainDeparture.setDelay(newDelay);
        toStrinTest = trainDeparture.getOriginalDepartureTime() + " " + trainDeparture.getLine() + " "
                + trainDeparture.getTrainNumber() + " " + trainDeparture.getDestination()
                + " Spor: " + trainDeparture.getTrack() + " Forsinkelse: " + trainDeparture.getDelay();

        if (!trainDeparture.toStrin().equals(toStrinTest)){
            utskrift += "toString() printer ikke riktig for synlige forsinkelser\n";
        }

        if (utskrift.isEmpty()){
            utskrift += "Test av toStrin i TrainDeparture er vellykket\n";
        }

        return utskrift;
    }

    /**
     * Enhetstester for table
     */

    private String testTableGet(){
        ArrayList<TrainDeparture> tableList = new ArrayList<>();
        tableList.add(new TrainDeparture(LocalTime.of(12, 15), "L1", 600, "Oslo", -1, LocalTime.of(0, 0)));
        tableList.add(new TrainDeparture(LocalTime.of(15, 30), "L2", 300, "Hamar",-1,  LocalTime.of(0, 0)));

        Table table = new Table();
        table.getTable().add(new TrainDeparture(LocalTime.of(12, 15), "L1", 600, "Oslo", -1, LocalTime.of(0, 0)));
        table.getTable().add(new TrainDeparture(LocalTime.of(15, 30), "L2", 300, "Hamar",-1,  LocalTime.of(0, 0)));

        String utskrift = "";

        for (int i = 0; i < table.getTable().size(); i++) {
            if (table.getTable().get(i).getTrainNumber() != tableList.get(i).getTrainNumber()){
                utskrift += "Get table gir ikke liste over togavganger tilbake\n";
            }

        }

        if (utskrift.isEmpty()){
            utskrift += "Testen av get-metodene til Table er vellykket\n";
        }

        return utskrift;
    }
}

