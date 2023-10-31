package edu.ntnu.stud;

import java.time.LocalTime;

import static java.lang.Math.abs;

/**
 * Dette er entitetsklassen for en tog-avgang
 */
public class TrainDeparture {
    /**
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
        this.originalDepartureTime = OriginalDepartureTime; //tester om tiden er innenfor riktig rekkevidde i input-klassen
        if (line.isEmpty()){
            this.line = "NULL";
        } else { this.line = line;}

        this.trainNumber = abs(trainNumber); //tar absolutt verdien av konstruktør verdien

        if (destination.isEmpty()){
            this.destination = "NULL";
        } else { this.destination = destination;}

        if (track < -1){
            this.track = -1;
        } else { this.track = track;}

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
    public String toStrin() {
        String output = getOriginalDepartureTime() + " " + getLine() + " "
                + getTrainNumber() + " " + getDestination();
        if (getTrack() > -1) {
            output += " Spor: " + getTrack();
        }
        if (getDelay().isAfter(LocalTime.of(0, 0))) {
            output += " Forsinkelse: " + getDelay();
        }
        return output;
    }
    public void setDelay(LocalTime delay){
        this.delay = this.delay.plusHours(delay.getHour());
        this.delay = this.delay.plusMinutes(delay.getMinute());
    }
    public void setTrack(int track){
        this.track = track;
    }

}
