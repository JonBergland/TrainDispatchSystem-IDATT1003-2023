package edu.ntnu.stud;

import java.time.LocalTime;

public class Clock {
    /**
     * Objekts variabel
     */
    private LocalTime clock;

    /**
     * Konstruktør
     */
    public Clock(){
        this.clock = LocalTime.of(0, 0);
    }

    public LocalTime getClock(){
        return clock;
    }

    public void setClock(int hour, int minute){
        this.clock = LocalTime.of(hour, minute);
    }
}
