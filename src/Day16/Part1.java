package Day16;

import java.util.*;

public class Part1 extends Base {
  public static void main(String[] args) {
    Part1 program = new Part1();
    int answer = program.run();
    System.out.println("answer " + answer);
  }

  private int run() {
    parseInput();

    List<Integer> invalidValues = new ArrayList<>();
    for (Ticket ticket : nearbyTickets) {
      invalidValues.addAll(ticket.getInvalidValues(rules.values()));
    }

    return invalidValues.stream().mapToInt(Integer::intValue).sum();
  }
}
