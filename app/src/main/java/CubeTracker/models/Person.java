package CubeTracker.models;

public class Person {
  private String id;
  private String name;
  private String slug;
  private String country;
  private int numComps;
  private String[] compIDs;
  private int numChamps;

  public String getid() {
    return id;
  }

  public String getname() {
    return name;
  }

  public String getslug() {
    return slug;
  }

  public String getCountry() {
    return country;
  }

  public int getNumComps() {
    return numComps;
  }

  public int getNumChamps() {
    return numChamps;
  }
}
