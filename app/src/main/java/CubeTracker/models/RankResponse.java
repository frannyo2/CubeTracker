package CubeTracker.models;

public class RankResponse {
  private Pagination pagination;
  private int total;
  private Rank[] items;

  public Pagination getPagination() {
    return pagination;
  }

  public int getTotal() {
    return total;
  }

  public Rank[] getItems() {
    return items;
  }
}
