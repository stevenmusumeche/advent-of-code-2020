package Day16;

public class RuleRange {
  int min;
  int max;

  public RuleRange(int min, int max) {
    this.min = min;
    this.max = max;
  }

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }

  public boolean isValid(int input) {
    return input >= min && input <= max;
  }
}
