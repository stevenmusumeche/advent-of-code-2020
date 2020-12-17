package Day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {
  private final String name;
  private final List<RuleRange> ranges = new ArrayList<>();

  public Rule(String rawRule) {
    String regex =
        "^(?<name>.*?): ?(?<min1>[\\d]+)-(?<max1>[\\d]+) or (?<min2>[\\d]+)-(?<max2>[\\d]+)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher m = pattern.matcher(rawRule);
    if (!m.matches()) throw new RuntimeException("unable to parse rule");

    this.name = m.group("name");
    this.addRange(
        new RuleRange(Integer.parseInt(m.group("min1")), Integer.parseInt(m.group("max1"))));
    this.addRange(
        new RuleRange(Integer.parseInt(m.group("min2")), Integer.parseInt(m.group("max2"))));
  }

  public void addRange(RuleRange ruleRange) {
    this.ranges.add(ruleRange);
  }

  public List<RuleRange> getRanges() {
    return ranges;
  }

  public String getName() {
    return name;
  }

  public boolean isValid(int value) {
    return this.ranges.stream().anyMatch(range -> range.isValid(value));
  }
}
