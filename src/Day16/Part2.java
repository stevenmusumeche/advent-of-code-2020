package Day16;

import java.util.*;
import java.util.stream.Collectors;

public class Part2 extends Base {
  public static void main(String[] args) {
    Part2 program = new Part2();
    long answer = program.run();
    System.out.println("answer " + answer);
  }

  private long run() {
    parseInput();

    List<Ticket> validTickets = getValidTickets();
    List<List<Integer>> groupedAnswers = groupAnswersByColumn(validTickets);
    List<List<Rule>> possibilities = buildPossibilities(groupedAnswers);

    Rule[] answers = new Rule[possibilities.size()];

    long numCompletedAnswers = 0;
    while (numCompletedAnswers < answers.length) {

      for (int i = 0; i < possibilities.size(); i++) {
        if (possibilities.get(i).size() == 1) {
          Rule matchingRule = possibilities.get(i).get(0);
          answers[i] = matchingRule;
          possibilities = removeRuleFromPossibilities(possibilities, matchingRule);
          break;
        }
      }

      numCompletedAnswers = getNumCompletedAnswers(answers);
    }

    return getDepartureProduct(answers);
  }

  private long getNumCompletedAnswers(Rule[] answers) {
    long numCompletedAnswers;
    numCompletedAnswers = Arrays.stream(answers).filter(x -> !Objects.isNull(x)).count();
    return numCompletedAnswers;
  }

  private long getDepartureProduct(Rule[] answers) {
    long departureProduct = 1;
    for (int i = 0; i < myTicket.getValues().size(); i++) {
      if (answers[i].getName().startsWith("departure")) {
        departureProduct *= myTicket.getValues().get(i);
      }
    }
    return departureProduct;
  }

  private List<List<Rule>> removeRuleFromPossibilities(
      List<List<Rule>> possibilities, Rule ruleToRemove) {
    return possibilities.stream()
        .map(p -> p.stream().filter(r -> !r.equals(ruleToRemove)).collect(Collectors.toList()))
        .collect(Collectors.toList());
  }

  private List<List<Rule>> buildPossibilities(List<List<Integer>> colMappings) {
    return colMappings.stream()
        .map(
            colMapping ->
                rules.values().stream()
                    .filter(rule -> colMapping.stream().allMatch(rule::isValid))
                    .collect(Collectors.toList()))
        .collect(Collectors.toList());
  }

  private List<List<Integer>> groupAnswersByColumn(List<Ticket> validTickets) {
    List<List<Integer>> colValues = new ArrayList<>();
    for (int col = 0; col < validTickets.get(0).getValues().size(); col++) {
      int finalCol = col;
      colValues.add(
          validTickets.stream()
              .map(ticket -> ticket.getValues().get(finalCol))
              .collect(Collectors.toList()));
    }

    return colValues;
  }

  private List<Ticket> getValidTickets() {
    return nearbyTickets.stream()
        .filter(ticket -> ticket.getInvalidValues(rules.values()).size() == 0)
        .collect(Collectors.toList());
  }
}
