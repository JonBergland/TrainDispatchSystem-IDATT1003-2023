package edu.ntnu.stud;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

    public void printTrainNumberList(){
        table.forEach(trainDeparture -> System.out.print(trainDeparture.getTrainNumber() + " "));
    }

    public TrainDeparture getTrainToTrainNumber(int trainNumber){
        for (TrainDeparture i : table){
            if (i.getTrainNumber() == trainNumber){
                return i;
            }
        }
        return null;
    }
}
