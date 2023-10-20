package edu.ntnu.stud;

import java.util.Comparator;

public class sortByTime implements Comparator<TrainDeparture> {
    public int compare(TrainDeparture a, TrainDeparture b)
    {
        return a.getOriginalDepartureTime().compareTo(b.getOriginalDepartureTime());
    }
}
