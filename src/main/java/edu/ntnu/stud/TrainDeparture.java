package edu.ntnu.stud;

import java.time.LocalTime;


public class TrainDeparture {
    /**
     * Dette er entitetsklassen for en tog-avgang
     *
     *Objektsvariabler
     * Lager en variabel til hver av atributtene til objektet
     */

    private final LocalTime originalDepartureTime;
    private final String line;
    private final int trainNumber;
    private final String destination;
    private int track;
    private LocalTime delay;


    /**
     * Konstruktør
     */
    public TrainDeparture(LocalTime OriginalDepartureTime, String line, int trainNumber, String destination,  int track, LocalTime delay){
        this.originalDepartureTime = OriginalDepartureTime;
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.track = track;
        this.delay = delay;
    }


    /**
     * Objekstmetoder
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

    public LocalTime getOriginalDepartureTime() {
        return originalDepartureTime;
    }

    public String getDestination() {
        return destination;
    }

    public String getLine() {
        return line;
    }
    public LocalTime getDepartureTime(){
        LocalTime departureTime = this.originalDepartureTime.plusHours(this.delay.getHour());
        departureTime = departureTime.plusMinutes(this.delay.getMinute());
        return departureTime;
    }

    public void setDelay(LocalTime delay){
        this.delay = this.delay.plusHours(delay.getHour());
        this.delay = this.delay.plusMinutes(delay.getMinute());
    }

    public void setTrack(int track){
        this.track = track;
    }

}
