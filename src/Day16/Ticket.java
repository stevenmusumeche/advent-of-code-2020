package Day16;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Ticket {
  private final List<Integer> values;

  public Ticket(String rawTicket) {
    values =
        Arrays.stream(rawTicket.split(",")).map(Integer::parseInt).collect(Collectors.toList());
  }

  public List<Integer> getInvalidValues(Collection<Rule> rules) {
    return values.stream()
        .filter(value -> rules.stream().noneMatch(rule -> rule.isValid(value)))
        .collect(Collectors.toList());
  }

  public List<Integer> getValues() {
    return values;
  }
}
