package Day15;

import util.InputLoader;

import java.util.*;
import java.util.stream.Collectors;

public class Part1 {
  List<Integer> spoken = new ArrayList<>();
  Map<Integer, List<Integer>> lastSpokenMapping = new HashMap<>();
  final int NUM_TURNS = 2020;

  public static void main(String[] args) {
    Part1 program = new Part1();
    int answer = program.run();
    System.out.println("answer " + answer);
  }

  private int run() {
    spoken = parseInput();

    // prime the map
    for (int i = 0; i < spoken.size(); i++) {
      lastSpokenMapping.put(spoken.get(i), new ArrayList<>(Arrays.asList(i)));
    }

    int lastNumberSpoken = spoken.get(spoken.size() - 1);
    int currentTurn = spoken.size();
    while (currentTurn < NUM_TURNS) {
      lastSpokenMapping.putIfAbsent(lastNumberSpoken, new ArrayList<>());
      List<Integer> curMapping = lastSpokenMapping.get(lastNumberSpoken);

      if (curMapping.size() == 1) {
        // only spoken 1 time
        lastNumberSpoken = 0;
        lastSpokenMapping.putIfAbsent(lastNumberSpoken, new ArrayList<>());
        // spoken.add(lastNumberSpoken);
        lastSpokenMapping.get(lastNumberSpoken).add(currentTurn);
      } else {
        lastNumberSpoken =
            curMapping.get(curMapping.size() - 1) - curMapping.get(curMapping.size() - 2);
        lastSpokenMapping.putIfAbsent(lastNumberSpoken, new ArrayList<>());
        // spoken.add(lastNumberSpoken);
        lastSpokenMapping.get(lastNumberSpoken).add(currentTurn);
      }
      currentTurn++;
    }
    return lastNumberSpoken;
  }

  private List<Integer> parseInput() {
    String input = InputLoader.loadAsString(this.getClass().getPackageName());

    return Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
  }
}
