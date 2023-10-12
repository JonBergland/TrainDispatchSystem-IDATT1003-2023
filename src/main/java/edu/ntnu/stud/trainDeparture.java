package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class trainDeparture {
    /**
     * Dette er entitetsklassen for en tog-avgang
     *
     *Objektsvariabler
     */
    private final LocalTime OriginalDepartureTime;
    private final String line;
    private final int trainNumber;
    private final String destination;
    private int delay;
    private int track;

    /**
     * Konstruktør
     */
    public trainDeparture(LocalTime OriginalDepartureTime, String line, int trainNumber, String destination, int delay, int track){
        this.OriginalDepartureTime = OriginalDepartureTime;
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.delay = delay;
        this.track = track;
    }


    /**
     * Objekstmetoder
     *
     */
    public int getTrainNumber() {
        return trainNumber;
    }
    public int getTrack() {
        return track;
    }

    public int getDelay() {
        return delay;
    }

    public LocalTime getOriginalDepartureTime() {
        return OriginalDepartureTime;
    }

    public String getDestination() {
        return destination;
    }

    public String getLine() {
        return line;
    }
    public LocalTime getDepartureTime(){
        LocalTime departureTime = this.OriginalDepartureTime.plusMinutes(this.delay);
        return departureTime;
    }

    public void setDelay(int delay){
        this.delay += delay;
    }

    public void setTrack(int track){
        this.track = track;
    }

}
