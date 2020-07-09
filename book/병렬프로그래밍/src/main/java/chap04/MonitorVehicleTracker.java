package chap04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
  Thread Not Safe
 */
public class MonitorVehicleTracker {

  private final Map<String, MutablePoint> locations;

  public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
    this.locations = deepCopy(locations);
  }

  private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> locations) {
    Map<String, MutablePoint> result = new HashMap<>();

    for (String id : locations.keySet()) {
      result.put(id, new MutablePoint(locations.get(id)));
    }

    return Collections.unmodifiableMap(result);
  }


}
