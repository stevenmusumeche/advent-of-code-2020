package Practice;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;

import util.InputLoader;

public class Day01Part2 {

  public static void main(String[] args) {
    try {
      new Day01Part2().go();
    } catch (Exception e) {
      System.out.println("Exception!");
      System.out.println(e);
    }
  }

  public void go() throws FileNotFoundException {
    List<String> input = loadInput();
    int totalFuel = 0;
    for (String line : input) {
      int fuel = calculateRequiredFuel(Integer.parseInt(line));
      totalFuel += fuel;

      while (true) {
        fuel = calculateRequiredFuel(fuel);
        if (fuel == 0) {
          break;
        }
        totalFuel += fuel;
      }
    }

    System.out.println(totalFuel);
  }

  private int calculateRequiredFuel(int mass) {
    int calculatedMass = (mass / 3) - 2;

    return calculatedMass < 0 ? 0 : calculatedMass;
  }

  private List<String> loadInput() throws FileNotFoundException {
    String path = Paths.get("").toAbsolutePath().toString();
    return InputLoader.load(path + "/src/Practice/input.txt");
  }
}
