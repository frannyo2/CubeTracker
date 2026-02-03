package CubeTracker.models;

public class ChampionshipResponse {
  private Pagination pagination;
  private int total;
  private Championship[] items;

  public Pagination getPagination() {
    return pagination;
  }

  public int getTotal() {
    return total;
  }

  public Championship[] getItems() {
    return items;
  }
}
