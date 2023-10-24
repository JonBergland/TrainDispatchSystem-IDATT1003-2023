package edu.ntnu.stud;

import java.util.Comparator;

//en klasse som skal sammenligne TrainDeparture objekter
public class sortByTime implements Comparator<TrainDeparture> {
    public int compare(TrainDeparture a, TrainDeparture b){
        //har en metode som sammenligner den reele avgangstiden med hverandre
        return a.getDepartureTime().compareTo(b.getDepartureTime());
    }
}
