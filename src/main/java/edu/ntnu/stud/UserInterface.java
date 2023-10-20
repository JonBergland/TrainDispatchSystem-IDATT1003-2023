package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class UserInterface {
    /**
     * Objekstvariabler
     */
    private ArrayList<TrainDeparture> table;

    /**
     * Konstruktør
     */
    public UserInterface(ArrayList<TrainDeparture> table){
        this.table= table;
    }

    public void printTrainDeparture() {
        Collections.sort(table, new sortByTime());
        for (TrainDeparture i : table) {
            String output = i.getOriginalDepartureTime() + " " + i.getLine() + " " + i.getDestination();
            if (i.getDelay().isAfter(LocalTime.of(0, 0))) {
                output += " " + i.getDelay();
            }
            if (i.getTrack() > -1) {
                output += " " + i.getTrack();
            }
            System.out.println(output);
        }
    }
}
