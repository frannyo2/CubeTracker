package CubeTracker.models;

public class Competition {
  private String id;
  private String name;
  private String city;
  private String country;
  private CompetitionDate date;
  private boolean isCanceled;
  private String[] events;
  private ContactPerson[] wcaDelegates;
  private ContactPerson[] organisers;
  private Venue venue;
  private String information;
  private String externalWebsite;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public CompetitionDate getDate() {
    return date;
  }

  public boolean isCanceled() {
    return isCanceled;
  }

  public String[] getEvents() {
    return events;
  }

  public ContactPerson[] getWcaDelegates() {
    return wcaDelegates;
  }

  public ContactPerson[] getOrganisers() {
    return organisers;
  }

  public Venue getVenue() {
    return venue;
  }

  public String getInformation() {
    return information;
  }

  public String getExternalWebsite() {
    return externalWebsite;
  }

  @Override
  public String toString() {
    return "Competition: " + name + " (" + id + ") - " + city + ", " + country;
  }

  // Nested classes
  public static class CompetitionDate {
    private String from;
    private String till;
    private int numberOfDays;

    public String getFrom() {
      return from;
    }

    public String getTill() {
      return till;
    }

    public int getNumberOfDays() {
      return numberOfDays;
    }
  }

  public static class ContactPerson {
    private String name;
    private String email;

    public String getName() {
      return name;
    }

    public String getEmail() {
      return email;
    }
  }

  public static class Venue {
    private String name;
    private String address;
    private String details;
    private Coordinates coordinates;

    public String getName() {
      return name;
    }

    public String getAddress() {
      return address;
    }

    public String getDetails() {
      return details;
    }

    public Coordinates getCoordinates() {
      return coordinates;
    }
  }

  public static class Coordinates {
    private double latitude;
    private double longitude;

    public double getLatitude() {
      return latitude;
    }

    public double getLongitude() {
      return longitude;
    }
  }
}
