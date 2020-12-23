package Day19;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import util.InputLoader;

import java.util.*;
import java.util.stream.Collectors;

public class Part1 {
  protected Map<String, Rule> rules = new HashMap<>();
  protected Map<String, Set<String>> memo = new HashMap<>();
  Set<String> allPossibleValidMessages;
  protected List<String> messages;

  public static void main(String[] args) {
    Part1 program = new Part1();
    long answer = program.run();
    System.out.println(answer);
  }

  private long run() {
    parseInput();
    allPossibleValidMessages = buildValid("0");
    return this.messages.stream().filter(this::messageIsValid).count();
  }

  protected boolean messageIsValid(String message) {
    return allPossibleValidMessages.contains(message);
  }

  protected Set<String> getCombinations(List<Set<String>> pieces) {
    Set<List<String>> combos = Sets.cartesianProduct(pieces);
    return combos.stream().map(s -> Joiner.on("").join(s)).collect(Collectors.toSet());
  }

  private Set<String> buildValid(String ruleId) {
    if (memo.containsKey(ruleId)) return memo.get(ruleId);

    Rule rule = rules.get(ruleId);
    if (rule.getType().equals(RuleType.Primitive)) {
      Set<String> answer = new HashSet<>();
      answer.add(rule.getPrimitive());
      memo.put(ruleId, answer);
      return answer;
    }

    Set<String> answers = new HashSet<>();
    for (int i = 0; i < rule.getRuleSets().size(); i++) {
      RuleSet ruleSet = rule.getRuleSets().get(i);
      List<Set<String>> pieces = new ArrayList<>();
      for (int j = 0; j < ruleSet.getData().size(); j++) {
        String ruleSetPiece = ruleSet.getData().get(j);
        Set<String> pieceAnswers = buildValid(ruleSetPiece);
        pieces.add(pieceAnswers);
      }

      // within a single ruleset, generate list of permutations between each ruleset piece
      Set<String> combinations = getCombinations(pieces);

      // append all combinations for this ruleset to the master list of answers
      answers.addAll(combinations);
    }

    memo.put(ruleId, answers);
    return answers;
  }

  protected void parseInput() {
    String rawInput = InputLoader.loadAsString(this.getClass().getPackageName());
    String[] sections = rawInput.split("\n\n");
    String[] rawRules = sections[0].split("\n");
    messages = List.of(sections[1].split("\n"));
    parseRules(rawRules);
  }

  private void parseRules(String[] rawRules) {
    for (String rawRule : rawRules) {
      Rule rule = Rule.make(rawRule);
      rules.put(rule.getId(), rule);
    }
  }
}
