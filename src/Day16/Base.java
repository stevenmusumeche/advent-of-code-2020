package Day16;

import util.InputLoader;

import java.util.*;
import java.util.stream.Collectors;

abstract class Base {
  protected Map<String, Rule> rules = new HashMap<>();
  protected Ticket myTicket;
  protected List<Ticket> nearbyTickets = new ArrayList<>();

  protected void parseInput() {
    String input = InputLoader.loadAsString(this.getClass().getPackageName());
    String[] pieces = input.split("\n\n");
    String rawRules = pieces[0];
    String rawTicket = pieces[1].split("\n")[1];
    String[] rawNearbyTickets =
        Arrays.copyOfRange(pieces[2].split("\n"), 1, pieces[2].split("\n").length);
    buildRules(rawRules);
    myTicket = new Ticket(rawTicket);
    nearbyTickets = Arrays.stream(rawNearbyTickets).map(Ticket::new).collect(Collectors.toList());
  }

  private void buildRules(String rawRules) {
    Arrays.stream(rawRules.split("\n"))
        .forEach(
            rawRule -> {
              Rule rule = new Rule(rawRule);
              rules.put(rule.getName(), rule);
            });
  }
}
