package edu.ntnu.stud.sort;

import edu.ntnu.stud.TrainDeparture;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class for sorting a HashMap of TrainDeparture objects.
 * <p>
 * The {@code Sort} class provides static methods to sort a HashMap with Integer keys and
 * TrainDeparture values in ascending order
 * </p>
 *
 * @see TrainDeparture
 * @see java.util.HashMap
 * @see java.util.LinkedHashMap
 * @see java.util.Comparator
 * @see java.util.stream.Collectors
 */
public class Sort {
  /**
   * Sorts a HashMap of TrainDeparture objects based on their original departure times.
   * <p>
   * This method takes a HashMap with Integer keys and TrainDeparture values and sorts
   * it in ascending order based on the original departure times of the TrainDeparture objects.
   * The sorting is performed using Java Streams and a Comparator which compares based on the
   * departure times of the TrainDepartures. The resulting sorted map is returned as a
   * LinkedHashMap to maintain the order of the TrainDepartures.
   * </p>
   *
   * @param map   The unsorted HashMap containing Integer keys and TrainDeparture values.
   * @return      A LinkedHashMap with the entries sorted by the original departure times.
   *              If the original departure times are equal, the order of insertion is maintained.
   */
  public static HashMap<Integer, TrainDeparture> sortByTime(HashMap<Integer, TrainDeparture> map) {
    return map.entrySet().stream()
        .sorted(Comparator.comparing(entry -> entry.getValue().getOriginalDepartureTime()))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            //if there are two entries with the same key, the existing entry is kept
            (existing, replacement) -> existing,
            LinkedHashMap::new
        ));
  }

  /**
   * Sorts a HashMap of TrainDeparture objects based on their destinations.
   * <p>
   * This method takes a HashMap with Integer keys and TrainDeparture values and sorts
   * it in ascending order based on the original departure times of the TrainDeparture objects.
   * The sorting is performed using Java Streams and a Comparator which compares based on the
   * destination of the TrainDepartures. The resulting sorted map is returned as a
   * LinkedHashMap to maintain the order of the TrainDepartures.
   * </p>
   *
   * @param map The unsorted HashMap containing Integer keys and TrainDeparture values.
   * @return    A LinkedHashMap with the entries sorted by the destination.
   *            If the destinations are equal, the order of insertion is maintained.
   */
  public static HashMap<Integer, TrainDeparture> sortByDestination(
      HashMap<Integer, TrainDeparture> map) {
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

  /**
   * Sorts a HashMap of TrainDeparture objects based on the track they arrive at.
   * <p>
   * This method takes a HashMap with Integer keys and TrainDeparture values and sorts
   * it in ascending order based on the track of the TrainDeparture objects.
   * The sorting is performed using Java Streams and a Comparator. The resulting sorted map
   * is returned as a LinkedHashMap to maintain the order of the TrainDepartures.
   * </p>
   *
   * @param map   The unsorted HashMap containing Integer keys and TrainDeparture values.
   * @return      A LinkedHashMap with the entries sorted by the track they arrive at.
   *              If the tracks are equal, the order of insertion is maintained.
   */
  public static HashMap<Integer, TrainDeparture> sortByTrack(HashMap<Integer, TrainDeparture> map) {
    return map.entrySet().stream()
        .sorted(Comparator.comparing(entry -> entry.getValue().getTrack()))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            //if there are two entries with the same key, the existing entry is kept
            (existing, replacement) -> existing,
            LinkedHashMap::new
        ));
  }
}
