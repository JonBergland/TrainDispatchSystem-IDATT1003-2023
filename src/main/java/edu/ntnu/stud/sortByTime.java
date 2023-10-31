package edu.ntnu.stud;

import java.util.Comparator;

//en klasse som skal sammenligne TrainDeparture objekter
public class sortByTime implements Comparator<TrainDeparture> {
    /**
     * @param a the first object to be compared.
     * @param b the second object to be compared.
     * @return int
     */
    public int compare(TrainDeparture a, TrainDeparture b){
        //har en metode som sammenligner den orginale avgangstiden til TrainDeparture objektene
        return a.getOriginalDepartureTime().compareTo(b.getOriginalDepartureTime());
    }
}
