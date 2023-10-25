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

    public void printTrainNumberList(){
        table.forEach(trainDeparture -> System.out.print(trainDeparture.getTrainNumber() + " "));
    }

    public void printDestinationList() {
        ArrayList<String> unique = new ArrayList<>();
        table.forEach(trainDeparture -> {
                    String destination = trainDeparture.getDestination();
                    if (!unique.contains(destination)) {
                        System.out.println(destination + " ");
                        unique.add(destination);
                    }
        });
    }

    public TrainDeparture getTrainByTrainNumber(int trainNumber){
        for (TrainDeparture i : table){
            if (i.getTrainNumber() == trainNumber){
                return i;
            }
        }
        return null;
    }

    public ArrayList<TrainDeparture> getTrainByDestination(String destination){
        ArrayList<TrainDeparture> destinationList = new ArrayList<>();
        for (TrainDeparture trainDeparture : table) {
            if(trainDeparture.getDestination().equalsIgnoreCase(destination)){
               destinationList.add(trainDeparture);
            }
        }
        return destinationList;
    }
}
