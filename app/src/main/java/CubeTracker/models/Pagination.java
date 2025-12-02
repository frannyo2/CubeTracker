package CubeTracker.models;

public class Pagination {
  private int page;
  private int size;

  public int getPage() {
    return page;
  }

  public int getSize() {
    return size;
  }

  @Override
  public String toString() {
    return ("Page: " + page + " -- Size: " + size);
  }
}
