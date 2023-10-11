package edu.ntnu.stud;

import java.time.LocalTime;

public class trainDeparture {
    /**
     * Dette er entitetsklassen for en togavgang
     *
     *Objektsvariabler
     */
    private final LocalTime departureTime;
    private final String line;
    private final int trainNumber;
    private final String destination;
    private LocalTime delay;
    private int track;

    /**
     * Konstruktør
     */
    public trainDeparture(LocalTime departureTime, String line, int trainNumber, String destination, LocalTime delay, int track){
        this.departureTime = departureTime;
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

    public LocalTime getDelay() {
        return delay;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public String getLine() {
        return line;
    }

    public LocalTime setDelay(LocalTime delay){
        this.delay = delay;
        return delay;
    }

    public int setTrack(int track){
        this.track = track;
        return track;
    }

}
