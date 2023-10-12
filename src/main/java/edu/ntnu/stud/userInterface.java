package edu.ntnu.stud;

import java.time.LocalTime;

public class userInterface {
    /**
     * Objekstvariabler
     */
    private trainDeparture trainDeparture;

    /**
     * Konstruktør
     */
    public userInterface(trainDeparture trainDeparture){
        this.trainDeparture = trainDeparture;
    }

    public void printTrainDeparture(){
        String output = trainDeparture.getOriginalDepartureTime() + " " + trainDeparture.getLine()
                + " "+ trainDeparture.getDestination();
        if(trainDeparture.getDelay().isAfter(LocalTime.of(0, 0))){
            output += " " + trainDeparture.getDelay();
        }
        if (trainDeparture.getTrack() > -1){
            output += " " + trainDeparture.getTrack();
        }
        System.out.println(output);
    }
}
