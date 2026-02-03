package CubeTracker.models;

public class Country {
  private String iso2Code;
  private String name;

  public String getIso2Code() {
    return iso2Code;
  }

  public String getCode() {
    return iso2Code;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Country: " + name + " (" + iso2Code + ")";
  }
}
