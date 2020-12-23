package Day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

enum RuleType {
  Primitive,
  Pointer
}

public class Rule {
  private final String id;
  private final RuleType type;
  private static final Pattern p = Pattern.compile("^(?<id>[\\d]+): (?<ruleData>.*?)$");
  private List<RuleSet> ruleSets = new ArrayList<>();
  private final String primitive;

  private Rule(String id, RuleType type, String primitive, List<RuleSet> ruleSets) {
    this.id = id;
    this.type = type;
    this.primitive = primitive;
    this.ruleSets = ruleSets;
  }

  public static Rule make(String raw) {
    Matcher m = p.matcher(raw);
    if (!m.matches()) throw new RuntimeException();
    RuleType type = m.group("ruleData").contains("\"") ? RuleType.Primitive : RuleType.Pointer;
    String primitiveValue = null;
    List<RuleSet> ruleSets = null;

    if (type.equals(RuleType.Primitive)) {
      primitiveValue = m.group("ruleData").substring(1, m.group("ruleData").length() - 1);
    } else {
      ruleSets =
          Arrays.stream(m.group("ruleData").split(" \\| "))
              .map(RuleSet::make)
              .collect(Collectors.toList());
    }

    Rule rule = new Rule(m.group("id"), type, primitiveValue, ruleSets);

    return rule;
  }

  public RuleType getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getPrimitive() {
    return primitive;
  }

  public List<RuleSet> getRuleSets() {
    return ruleSets;
  }
}
