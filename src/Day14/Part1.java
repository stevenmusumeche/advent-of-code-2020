package Day14;

import util.InputLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 extends Base {
  public static void main(String[] args) {
    Part1 program = new Part1();
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

  protected void processInstruction(String line) {
    Instruction instruction = new Instruction(line);
    String valueBinary = padLeft(Integer.toBinaryString(instruction.getValue()));
    StringBuilder replaced = new StringBuilder(valueBinary);
    bitmask.forEach(replaced::setCharAt);
    memory.put((long) instruction.getMemoryAddress(), Long.parseLong(String.valueOf(replaced), 2));
  }

  protected void buildMaskMapping(String mask) {
    // reset
    bitmask = new HashMap<>();
    char[] characters = {'0', '1'};
    for (char character : characters) {
      int index = mask.indexOf(character);
      while (index >= 0) {
        bitmask.put(index, character);
        index = mask.indexOf(character, index + 1);
      }
    }
  }
}
