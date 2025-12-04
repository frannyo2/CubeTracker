package CubeTracker.models;

public class Rank {
  private String rankType;
  private String personId;
  private String eventId;
  private int best;
  private RankPosition rank;

  public double getBest() {
    return (best / 100.0);
  }

  public String getEventId() {
    return eventId;
  }

  public String getPersonId() {
    return personId;
  }

  public int[] getRank() {
    int[] rankValues = { rank.getWorld(), rank.getContinent(), rank.getCountry() };
    return (rankValues);
  }

  public String getRankType() {
    return rankType;
  }

  public class RankPosition {
    private int world;
    private int continent;
    private int country;

    public int getContinent() {
      return continent;
    }

    public int getCountry() {
      return country;
    }

    public int getWorld() {
      return world;
    }
  }
}
