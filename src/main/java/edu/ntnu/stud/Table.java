package edu.ntnu.stud;

import java.util.ArrayList;

public class Table{
    private ArrayList<TrainDeparture> table = new ArrayList<>();

    public ArrayList<TrainDeparture> getTable() {
        return table;
    }

    public boolean checkTrainNumber(int newTrainNumber){
        boolean output = false;
        for(TrainDeparture i: table){
            if (i.getTrainNumber() == newTrainNumber){
                output = true;
            }
        }
        return output;
    }
}
