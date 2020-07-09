package chap04;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/*
  Thread Safe
 */
public class DelegatingVehicleTracker {

  private final ConcurrentHashMap<String, Point> locations;
  private final Map<String, Point> unmodifiableMap;

  public DelegatingVehicleTracker(Map<String, Point> points) {
    this.locations = new ConcurrentHashMap<>(points);
    this.unmodifiableMap = Collections.unmodifiableMap(locations);
  }

  public Map<String, Point> getLocations() {
    return unmodifiableMap;
  }

  public Point getLocation(String id) {
    return locations.get(id);
  }

  public void setLocation(String id, int x, int y) {
    if (locations.replace(id, new Point(x, y)) == null) {
      throw new IllegalArgumentException("invalid vehicle name : " + id);
    }
  }
}
