package Day01;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;

import util.InputLoader;

public class Part1 {
  
  private int target = 2020;

  public static void main(String[] args) {
    try {
      Part1 program = new Part1();
      int answer = program.run();
      System.out.printf("Answer %d%n", answer);

    } catch (Exception e) {
      System.out.println("Exception!");
      System.out.println(e);
    }
  }

  private int run() throws FileNotFoundException {
    List<String> input = loadInput();
    HashSet<Integer> entries = new HashSet<>();    
    for(String entry : input) {
      int candidate = Integer.parseInt(entry);
      int complement = target - candidate;
      if(entries.contains(complement)) {
        System.out.printf("Found %d, %d%n", candidate, complement);
        return candidate * complement;
      }
      entries.add(candidate);
    }

    throw new InputMismatchException();
  }

  private List<String> loadInput() throws FileNotFoundException {
    String path = Paths.get("").toAbsolutePath().toString();
    return InputLoader.load(path + "/src/Day01/input.txt");
  }
}
