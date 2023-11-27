package edu.ntnu.stud;

import java.util.*;
import java.util.stream.Collectors;

public class SortByTime {
  /**
   * Sorts a HashMap of TrainDeparture objects based on their original departure times.
   * This method takes a HashMap with Integer keys and TrainDeparture values and sorts
   * it in ascending order based on the original departure times of the TrainDeparture objects.
   * The sorting is performed using Java Streams and a Comparator which compares based on the
   * departure times of the TrainDepartures. The resulting sorted map is returned as a
   * LinkedHashMap to maintain the order of the TrainDepartures.
   *
   * @param map The unsorted HashMap containing Integer keys and TrainDeparture values.
   * @return A LinkedHashMap with the entries sorted by the original departure times.
   *         If the original departure times are equal, the order of insertion is maintained.
   */
  public static HashMap<Integer, TrainDeparture> sort(HashMap<Integer, TrainDeparture> map){
    return map.entrySet().stream()
        .sorted(Comparator.comparing(entry -> entry.getValue().getOriginalDepartureTime()))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (existing, replacement) -> existing,
            LinkedHashMap::new
        ));
  }

}
