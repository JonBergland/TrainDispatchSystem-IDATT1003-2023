package edu.ntnu.stud;

import java.util.*;

//en klasse som skal sammenligne TrainDeparture objekter
public class SortByTime {
  public static HashMap<Integer, TrainDeparture> sort(HashMap<Integer, TrainDeparture> map){
    List<Map.Entry<Integer, TrainDeparture> > list =
        new LinkedList<>(map.entrySet());

    list.sort(Comparator.comparing(a -> a.getValue().getOriginalDepartureTime()));

    HashMap<Integer, TrainDeparture> newHashMap = new LinkedHashMap<Integer, TrainDeparture>();
    for (Map.Entry<Integer, TrainDeparture> hashMap : list) {
      System.out.println(hashMap.getKey());
      newHashMap.put(hashMap.getKey(), hashMap.getValue());
    }
    return newHashMap;
  }

}
