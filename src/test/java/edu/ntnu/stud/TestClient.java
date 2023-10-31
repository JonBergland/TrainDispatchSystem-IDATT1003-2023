package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;

public class TestClient {
    /**
     * @param args
     */

    public static void main(String[] args) {
        TestClient testClient = new TestClient();

        testClient.alleTester();
    }

    public void alleTester() {
        String utskrift = "";

        //tester for trainDeparture
        testTraindepartureConstructor();
        testTrainDepartureGet();
        utskrift += testTrainDepartureSet();
        utskrift += testTrainDeparturetoStrin();

        /**
         //tester for table
         utskrift += testTableGet();
         utskrift += testTableCheckTrainNumber();
         */
        System.out.println(utskrift);
    }

    /**
     * Enhetstester for TrainDeparture
     */
    private void testTraindepartureConstructor() {
        LocalTime originalDepartureTime = LocalTime.of(8, 30);
        String line = "";
        int trainNumber = -204;
        String destination = "";
        int track = -3;
        LocalTime delay = LocalTime.of(0, 0);

        String utskrift = "";
        try {
            originalDepartureTime = LocalTime.of(-1, 3);
        } catch (DateTimeException e) {
            utskrift += "Negativ test av departureTime er vellykket\n";
        }

        try {
            TrainDeparture trainDeparture = new TrainDeparture(originalDepartureTime, line, trainNumber, destination, track, delay);
            if (!trainDeparture.getLine().equals("NULL")) {
                throw new IllegalArgumentException("Line-dummyvalue blir ikke satt");
            }
            if (trainDeparture.getTrainNumber() != 204) {
                throw new IllegalArgumentException("Trainnumber absolutt verdi blir ikke satt");
            }
            if (!trainDeparture.getDestination().equals("NULL")) {
                throw new IllegalArgumentException("Destination-dummyvalue blir ikke satt");
            }
            if (trainDeparture.getTrack() != -1) {
                throw new IllegalArgumentException("Track dummyvalue blir ikke satt");
            }
            utskrift += "Test av konstruktøren i TrainDeparture var vellykket";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println(utskrift);
    }

    private void testTrainDepartureGet() { //test som tester alle get metodene i TrainDeparture
        LocalTime originalDepartureTime = LocalTime.of(8, 30);
        String line = "L4";
        int trainNumber = 204;
        String destination = "Oslo";
        int track = 3;
        LocalTime delay = LocalTime.of(0, 0);

        TrainDeparture trainDeparture = new TrainDeparture(originalDepartureTime, line, trainNumber, destination, track, delay);

        try {
            if (!trainDeparture.getOriginalDepartureTime().equals(originalDepartureTime)) {
                throw new IllegalArgumentException("Get-metode for original avgangstid gir ikke riktig output");
            }
            if (!trainDeparture.getLine().equals(line)) {
                throw new IllegalArgumentException("Get-metode for linjenavnet gir ikke riktig output");
            }
            if (trainDeparture.getTrainNumber() != trainNumber) {
                throw new IllegalArgumentException("Get-metode for tognummeret gir ikke riktig output");
            }
            if (!trainDeparture.getDestination().equals(destination)) {
                throw new IllegalArgumentException("Get-metode for destinasjonen gir ikke riktig output");
            }
            if (trainDeparture.getTrack() != track) {
                throw new IllegalArgumentException("Get-metode for sporet gir ikke riktig output");
            }
            if (!trainDeparture.getDelay().equals(delay)) {
                throw new IllegalArgumentException("Get-metode for forsinkelsen gir ikke riktig output");
            }
            System.out.println("Test av get-metodene i TrainDeparture var vellykket");

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private String testTrainDepartureSet() {
        LocalTime originalDepartureTime = LocalTime.of(8, 30);
        String line = "L4";
        int trainNumber = 204;
        String destination = "Oslo";
        int track = -1;
        LocalTime delay = LocalTime.of(0, 0);

        TrainDeparture trainDeparture = new TrainDeparture(originalDepartureTime, line, trainNumber, destination, track, delay);
        String utskrift = "";

        int newTrack = 3;
        LocalTime newDelay = LocalTime.of(1, 30);
        trainDeparture.setTrack(newTrack);
        trainDeparture.setDelay(newDelay);

        if (trainDeparture.getTrack() != newTrack) {
            utskrift += "Det nye sporet er ikke satt\n";
        }
        if (!trainDeparture.getDelay().equals(newDelay)) {
            utskrift += "Den nye forsinkelsen er ikke satt\n";
        }
        if (utskrift.isEmpty()) {
            utskrift += "Test av set-metodene i Traindeparture er vellykket\n";
        }
        return utskrift;
    }

    private String testTrainDeparturetoStrin() {
        LocalTime originalDepartureTime = LocalTime.of(8, 30);
        String line = "L4";
        int trainNumber = 204;
        String destination = "Oslo";
        int track = -1;
        LocalTime delay = LocalTime.of(0, 0);

        TrainDeparture trainDeparture = new TrainDeparture(originalDepartureTime, line, trainNumber, destination, track, delay);
        String utskrift = "";

        //tester toStrin() med track og delay usynlige
        String toStrinTest = trainDeparture.getOriginalDepartureTime() + " " + trainDeparture.getLine() + " "
                + trainDeparture.getTrainNumber() + " " + trainDeparture.getDestination();
        if (!trainDeparture.toStrin().equals(toStrinTest)) {
            utskrift += "toStrin() printer ikke riktig for usynlige spor og forsinkelser\n";
        }

        //tester toStrin() med track synlig og delay usynlige
        int newTrack = 3;
        trainDeparture.setTrack(newTrack);
        toStrinTest = trainDeparture.getOriginalDepartureTime() + " " + trainDeparture.getLine() + " "
                + trainDeparture.getTrainNumber() + " " + trainDeparture.getDestination() + " Spor: " + trainDeparture.getTrack();

        if (!trainDeparture.toStrin().equals(toStrinTest)) {
            utskrift += "toString() printer ikke riktig for synlige spor\n";
        }

        LocalTime newDelay = LocalTime.of(1, 30);
        trainDeparture.setDelay(newDelay);
        toStrinTest = trainDeparture.getOriginalDepartureTime() + " " + trainDeparture.getLine() + " "
                + trainDeparture.getTrainNumber() + " " + trainDeparture.getDestination()
                + " Spor: " + trainDeparture.getTrack() + " Forsinkelse: " + trainDeparture.getDelay();

        if (!trainDeparture.toStrin().equals(toStrinTest)) {
            utskrift += "toString() printer ikke riktig for synlige forsinkelser\n";
        }

        if (utskrift.isEmpty()) {
            utskrift += "Test av toStrin i TrainDeparture er vellykket\n";
        }

        return utskrift;
    }

    /**
     * Enhetstester for table
     */

    private String testTableGet() {
        ArrayList<TrainDeparture> tableList = new ArrayList<>();
        tableList.add(new TrainDeparture(LocalTime.of(12, 15), "L1", 600, "Oslo", -1, LocalTime.of(0, 0)));
        tableList.add(new TrainDeparture(LocalTime.of(15, 30), "L2", 300, "Hamar", -1, LocalTime.of(0, 0)));

        Table table = new Table();
        table.getTable().add(new TrainDeparture(LocalTime.of(12, 15), "L1", 600, "Oslo", -1, LocalTime.of(0, 0)));
        table.getTable().add(new TrainDeparture(LocalTime.of(15, 30), "L2", 300, "Hamar", -1, LocalTime.of(0, 0)));

        String utskrift = "";

        for (int i = 0; i < table.getTable().size(); i++) {
            if (table.getTable().get(i).getTrainNumber() != tableList.get(i).getTrainNumber()) {
                utskrift += "Get table gir ikke liste over togavganger tilbake\n";
            }

        }

        if (utskrift.isEmpty()) {
            utskrift += "Testen av get-metodene til Table er vellykket\n";
        }

        return utskrift;
    }

    private String testTableCheckTrainNumber() {
        Table table = new Table();
        table.getTable().add(new TrainDeparture(LocalTime.of(12, 15), "L1", 600, "Oslo", -1, LocalTime.of(0, 0)));
        table.getTable().add(new TrainDeparture(LocalTime.of(15, 30), "L2", 300, "Hamar", -1, LocalTime.of(0, 0)));

        String utskrift = "";

        if (table.checkTrainNumber(600) && !table.checkTrainNumber(601)) {
            utskrift += "Testen for checkTrainNumber var vellykket\n";
        } else {
            utskrift += "ChechTrainNumber gir ikke riktig output\n";
        }
        return utskrift;
    }


}

