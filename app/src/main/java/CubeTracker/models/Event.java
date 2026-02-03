package CubeTracker.models;

public class Event {
  private String id;
  private String name;
  private String format;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getFormat() {
    return format;
  }

  @Override
  public String toString() {
    return "Event: " + name + " (" + id + ") - Format: " + format;
  }
}
