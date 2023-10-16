package edu.ntnu.stud;

import java.util.Comparator;

public class sortByTime implements Comparator<trainDeparture> {
    public int compare(trainDeparture a, trainDeparture b)
    {
        return a.getOriginalDepartureTime().compareTo(b.getOriginalDepartureTime());
    }
}
