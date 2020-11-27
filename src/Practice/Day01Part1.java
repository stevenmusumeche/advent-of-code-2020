package Practice;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;

import util.InputLoader;

public class Day01Part1 {

  public static void main(String[] args) {
    try {
      new Day01Part1().go();
    } catch (Exception e) {
      System.out.println("Exception!");
      System.out.println(e);
    }
  }

  public void go() throws FileNotFoundException {
    ArrayList<String> input = loadInput();
    int totalFuel = 0;
    for (String line : input) {
      totalFuel += calculateRequiredFuel(Integer.parseInt(line));
    }

    System.out.println((totalFuel));
  }

  private int calculateRequiredFuel(int mass) {
    return (mass / 3) - 2;
  }

  private ArrayList<String> loadInput() throws FileNotFoundException {
    String path = Paths.get("").toAbsolutePath().toString();
    return InputLoader.load(path + "/src/Practice/input.txt");
  }
}
