package Day01;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;

import util.InputLoader;

public class Part2 {

  private int target = 2020;

  public static void main(String[] args) {
    try {
      Part2 program = new Part2();
      int answer = program.run();
      System.out.printf("Answer %d%n", answer);

    } catch (Exception e) {
      System.out.println("Exception!");
      System.out.println(e);
    }
  }

  private int run() throws FileNotFoundException {
    List<String> input = loadInput();

    for(int i = 0; i < input.size(); i++) {
      int first = Integer.parseInt(input.get(i));

      int localTarget = target - first;
      HashSet<Integer> found = new HashSet<>();
      
      for(int j = i + 1; j < input.size(); j++) {
        int cur = Integer.parseInt(input.get(j));
        int complement = localTarget - cur;
        if(found.contains(complement)) {
          return first * cur * complement;
        }
        found.add(cur);
      }
    }

    throw new InputMismatchException();
  }

  private List<String> loadInput() throws FileNotFoundException {
    String path = Paths.get("").toAbsolutePath().toString();
    return InputLoader.load(path + "/src/Day01/input.txt");
  }
}
