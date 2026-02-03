package CubeTracker.models;

public class CompetitionResponse {
  private Pagination pagination;
  private int total;
  private Competition[] items;

  public Pagination getPagination() {
    return pagination;
  }

  public int getTotal() {
    return total;
  }

  public Competition[] getItems() {
    return items;
  }
}
