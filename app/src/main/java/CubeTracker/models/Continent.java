package CubeTracker.models;

public class Continent {
  private String id;
  private String name;

  public String getid() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return ("ID: " + id + " -- Name: " + name);
  }
}
