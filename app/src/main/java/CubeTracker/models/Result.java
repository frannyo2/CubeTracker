package CubeTracker.models;

public class Result {
  private String round;
  private int position;
  private int best;
  private int average;
  private String format;
  private int[] solves;

  public String getRound() {
    return round;
  }

  public int getPosition() {
    return position;
  }

  public int getBest() {
    return best;
  }

  public double getBestInSeconds() {
    return best / 100.0;
  }

  public int getAverage() {
    return average;
  }

  public double getAverageInSeconds() {
    return average / 100.0;
  }

  public String getFormat() {
    return format;
  }

  public int[] getSolves() {
    return solves;
  }

  @Override
  public String toString() {
    return String.format("Round: %s, Position: %d, Best: %.2fs, Average: %.2fs",
        round, position, getBestInSeconds(), getAverageInSeconds());
  }
}
