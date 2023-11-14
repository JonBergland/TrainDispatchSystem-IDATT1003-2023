package edu.ntnu.stud;

import java.util.*;

//en klasse som skal sammenligne TrainDeparture objekter
public class SortByTime {
  HashMap<Integer, TrainDeparture> hashMap;

  public SortByTime(HashMap<Integer, TrainDeparture> hashMap) {
    this.hashMap = hashMap;
  }

  public HashMap<Integer, TrainDeparture> sort(){
    List<Map.Entry<Integer, TrainDeparture> > list =
        new LinkedList<>(hashMap.entrySet());

    list.sort(Comparator.comparing(a -> a.getValue().getOriginalDepartureTime()));

    HashMap<Integer, TrainDeparture> newHashMap = new LinkedHashMap<Integer, TrainDeparture>();
    for (Map.Entry<Integer, TrainDeparture> hashMap : list) {
      System.out.println(hashMap.getKey());
      newHashMap.put(hashMap.getKey(), hashMap.getValue());
    }
    return newHashMap;
  }

  /**
   * @param a the first object to be compared.
   * @param b the second object to be compared.
   * @return int

  public int compare(TrainDeparture a, TrainDeparture b) {
    List<Map.Entry<Integer, TrainDeparture> > list =
        new LinkedList<Map.Entry<Integer, TrainDeparture> > (table.getHashMap().entrySet());

    Collections.sort(list, new Comparator<Map.Entry<Integer, TrainDeparture>>() {
      @Override
      public int compare(Map.Entry<Integer, TrainDeparture> a, Map.Entry<Integer, TrainDeparture> b) {
        return a.getValue().getOriginalDepartureTime().compareTo(b.getValue().getOriginalDepartureTime());
      }
    });

    HashMap<Integer, TrainDeparture> newHashMap = new LinkedHashMap<Integer, TrainDeparture>();
    for (Map.Entry<Integer, TrainDeparture> hashMap : list) {
      newHashMap.put(hashMap.getKey(), hashMap.getValue());
    }

    //har en metode som sammenligner den orginale avgangstiden til TrainDeparture objektene
    table.setHashMap(newHashMap);
  }*/

}
