package CubeTracker.models;

public class CountryResponse {
  private Pagination pagination;
  private int total;
  private Country[] items;

  public Pagination getPagination() {
    return pagination;
  }

  public int getTotal() {
    return total;
  }

  public Country[] getItems() {
    return items;
  }
}
