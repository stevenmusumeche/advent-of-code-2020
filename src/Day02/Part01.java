package Day02;

import java.util.List;

public class Part01 extends Base {
  public static void main(String[] args) {
    try {
      Part01 program = new Part01();
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
