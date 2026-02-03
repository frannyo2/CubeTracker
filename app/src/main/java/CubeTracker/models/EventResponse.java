package CubeTracker.models;

public class EventResponse {
  private Pagination pagination;
  private int total;
  private Event[] items;

  public Pagination getPagination() {
    return pagination;
  }

  public int getTotal() {
    return total;
  }

  public Event[] getItems() {
    return items;
  }
}
