package CubeTracker.models;

import java.util.Map;

public class Person {
  private String id;
  private String name;
  private String slug;
  private String country;
  private int numberOfCompetitions;
  private String[] competitionIds;
  private int numberOfChampionships;
  private String[] championshipIds;
  private PersonRanks rank;
  private Map<String, Map<String, Result[]>> results;
  private Medals medals;
  private Records records;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSlug() {
    return slug;
  }

  public String getCountry() {
    return country;
  }

  public int getNumberOfCompetitions() {
    return numberOfCompetitions;
  }

  public String[] getCompetitionIds() {
    return competitionIds;
  }

  public int getNumberOfChampionships() {
    return numberOfChampionships;
  }

  public String[] getChampionshipIds() {
    return championshipIds;
  }

  public PersonRanks getRank() {
    return rank;
  }

  public Map<String, Map<String, Result[]>> getResults() {
    return results;
  }

  public Medals getMedals() {
    return medals;
  }

  public Records getRecords() {
    return records;
  }

  // Nested classes
  public static class PersonRanks {
    private PersonRankEntry[] singles;
    private PersonRankEntry[] averages;

    public PersonRankEntry[] getSingles() {
      return singles;
    }

    public PersonRankEntry[] getAverages() {
      return averages;
    }
  }

  public static class PersonRankEntry {
    private String eventId;
    private int best;
    private RankPosition rank;

    public String getEventId() {
      return eventId;
    }

    public int getBest() {
      return best;
    }

    public double getBestInSeconds() {
      return best / 100.0;
    }

    public RankPosition getRank() {
      return rank;
    }
  }

  public static class RankPosition {
    private int world;
    private int continent;
    private int country;

    public int getWorld() {
      return world;
    }

    public int getContinent() {
      return continent;
    }

    public int getCountry() {
      return country;
    }
  }

  public static class Medals {
    private int gold;
    private int silver;
    private int bronze;

    public int getGold() {
      return gold;
    }

    public int getSilver() {
      return silver;
    }

    public int getBronze() {
      return bronze;
    }

    public int getTotal() {
      return gold + silver + bronze;
    }
  }

  // Records field uses Object because the API returns either:
  // - an object {"WR": 1, "CR": 2} when records exist
  // - an empty array [] when no records
  public static class Records {
    private Object single;
    private Object average;

    public Object getSingle() {
      return single;
    }

    public Object getAverage() {
      return average;
    }

    public boolean hasSingleRecords() {
      return single != null && !(single instanceof java.util.List);
    }

    public boolean hasAverageRecords() {
      return average != null && !(average instanceof java.util.List);
    }
  }
}
