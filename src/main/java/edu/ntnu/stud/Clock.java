package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * Dette er klassen for klokka
 */
public class Clock {
    /**
     * Objekts variabel
     */
    private LocalTime clock;

    /**
     * Konstruktør
     */
    public Clock(){ //når Clock blir initialisert settes den til 00:00
        this.clock = LocalTime.of(0, 0);
    }

    /**
     * @return LocalTime
     */
    public LocalTime getClock(){ //en get-metode for å returnere klokkeslettet
        return clock;
    }

    /**
     * @param time
     */
    public void setClock(LocalTime time){ //en setmetode som setter parameteren lik klokkeslettet
        this.clock = time;
    }
}
