package Day02;

import java.util.List;

public class Part02 extends Base {
  public static void main(String[] args) {
    try {
      Part02 program = new Part02();
      int answer = program.run();
      System.out.printf("Answer %d%n", answer);
    } catch (Exception e) {
      System.out.println("Exception!");
      System.out.println(e);
    }
  }

  private int run() {
    List<String> input = loadInput();
    int numValid = 0;
    for (String line : input) {
      ParsedLine parsedLine = parseLine(line);
      if(parsedLine.isValidPassword()) {
        numValid++;
      }
    }

    return numValid;
  }
}
