package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class trainDeparture {
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
    private LocalTime delay;
    private int track;

    /**
     * Konstruktør
     */
    public trainDeparture(LocalTime OriginalDepartureTime, String line, int trainNumber, String destination, LocalTime delay, int track){
        this.originalDepartureTime = OriginalDepartureTime;
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
    public void printTrainDeparture(){
        String output = originalDepartureTime + " " + line + " " + destination;
        if(delay.isAfter(LocalTime.of(0, 0))){
            output += " " + delay;
        }
        if (track > -1){
            output += " " + track;
        }
        System.out.println(output);
    }

    public void setDelay(LocalTime delay){
        this.delay = this.delay.plusHours(delay.getHour());
        this.delay = this.delay.plusMinutes(delay.getMinute());
    }

    public void setTrack(int track){
        this.track = track;
    }

}
