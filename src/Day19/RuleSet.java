package Day19;

import java.util.Arrays;
import java.util.List;

public class RuleSet {

  List<String> data;

  private RuleSet(String[] data) {
    this.data = Arrays.asList(data);
  }

  public static RuleSet make(String raw) {
    return new RuleSet(raw.split(" "));
  }

  public List<String> getData() {
    return data;
  }
}
