package edu.ntnu.stud.sort;

import edu.ntnu.stud.TrainDeparture;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class for sorting a HashMap of TrainDeparture objects based on their destinations.
 * <p>
 * The {@code SortByDestination} class provides a static method to sort a HashMap with Integer keys and
 * TrainDeparture values in ascending order based on the destination of the TrainDeparture objects.
 * </p>
 *
 * @see TrainDeparture
 * @see java.util.HashMap
 * @see java.util.LinkedHashMap
 * @see java.util.Comparator
 * @see java.util.stream.Collectors
 */
public class SortByDestination {
  /**
   * Sorts a HashMap of TrainDeparture objects based on their destinations.
   * <p>
   * This method takes a HashMap with Integer keys and TrainDeparture values and sorts
   * it in ascending order based on the original departure times of the TrainDeparture objects.
   * The sorting is performed using Java Streams and a Comparator which compares based on the
   * destination of the TrainDepartures. The resulting sorted map is returned as a
   * LinkedHashMap to maintain the order of the TrainDepartures.
   * </p>
   * @param map   The unsorted HashMap containing Integer keys and TrainDeparture values.
   * @return      A LinkedHashMap with the entries sorted by the destination.
   *              If the destinations are equal, the order of insertion is maintained.
   */
  public static HashMap<Integer, TrainDeparture> sort(HashMap<Integer, TrainDeparture> map) {
    return map.entrySet().stream()
        .sorted(Comparator.comparing(entry -> entry.getValue().getDestination()))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            //if there are two entries with the same key, the existing entry is kept
            (existing, replacement) -> existing,
            LinkedHashMap::new
        ));
  }
}

