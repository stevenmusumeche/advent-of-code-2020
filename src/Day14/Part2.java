package Day14;

import util.InputLoader;

import java.util.*;

public class Part2 extends Base {
  public static void main(String[] args) {
    Part2 program = new Part2();
    long answer = program.run();
    System.out.println("answer " + answer);
  }

  private long run() {
    List<String> input = InputLoader.loadForPackage(this.getClass().getPackageName());
    for (String line : input) {

      if (isMask(line)) {
        setCurrentMask(line);
        continue;
      }

      processInstruction(line);
    }

    return calculateAnswer();
  }

  protected void buildMaskMapping(String mask) {
    // reset
    bitmask = new HashMap<>();
    char[] characters = {'1', 'X'};
    for (char character : characters) {
      int index = mask.indexOf(character);
      while (index >= 0) {
        bitmask.put(index, character);
        index = mask.indexOf(character, index + 1);
      }
    }
  }

  protected void processInstruction(String line) {
    Instruction instruction = new Instruction(line);
    String valueBinary = padLeft(Integer.toBinaryString(instruction.getMemoryAddress()));
    StringBuilder replaced = new StringBuilder(valueBinary);
    bitmask.forEach(replaced::setCharAt);
    Set<String> answers = buildWrites(String.valueOf(replaced));
    answers.forEach(answer -> {
      long binaryAnswer = Long.parseLong(String.valueOf(answer), 2);
      memory.put(binaryAnswer, (long) instruction.getValue());
    });
  }

  private Set<String> buildWrites(String input) {
    Set<String> answers = new HashSet<>();

    boolean hasFloater = input.contains("X");
    if (!hasFloater) {
      answers.add(input);
      return answers;
    }

    answers.addAll(buildWrites(input.replaceFirst("X", "0")));
    answers.addAll(buildWrites(input.replaceFirst("X", "1")));

    return answers;
  }
}
