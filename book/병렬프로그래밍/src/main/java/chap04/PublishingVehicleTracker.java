package chap04;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PublishingVehicleTracker {

  private final Map<String, SafePoint> locations;
  private final Map<String, SafePoint> unmodifiableMap;

  public PublishingVehicleTracker(Map<String, SafePoint> locations, Map<String, SafePoint> unmodifiableMap) {
    this.locations = new ConcurrentHashMap<>(locations);
    this.unmodifiableMap = Collections.unmodifiableMap(unmodifiableMap);
  }

  public Map<String, SafePoint> getLocations() {
    return unmodifiableMap;
  }

  public SafePoint getLocation(String id) {
    return locations.get(id);
  }

  public void setLocation(String id, int x, int y) {
    if (!locations.containsKey(id)) {
      throw new IllegalArgumentException("invalid Vehicle name : " + id);
    }

    locations.get(id).set(x, y);
  }
}
