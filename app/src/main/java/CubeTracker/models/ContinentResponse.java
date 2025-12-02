package CubeTracker.models;

public class ContinentResponse {
  private Pagination pagination;
  private int total;
  private Continent[] items;

  public Pagination getPagination() {
    return pagination;
  }

  public Continent[] getItems() {
    return items;
  }

  public int getTotal() {
    return total;
  }
}
