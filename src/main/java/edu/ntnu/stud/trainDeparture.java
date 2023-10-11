package edu.ntnu.stud;

import java.time.LocalTime;

public class trainDeparture {
    /**
     * Dette er entitetsklassen for en togavgang
     */

    /**
     *Objektsvariabler
     */
    private LocalTime departureTime;
    private String line;
    private int trainNumber;
    private String destination;
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
     */
}
