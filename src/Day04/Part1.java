package Day04;

import java.util.ArrayList;
import java.util.List;

import util.InputLoader;

public class Part1 {
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

  private int run() {
    List<Passport> passports = parseInput();
    return (int) passports.stream().filter(passport -> passport.isValid()).count();
  }

  protected List<Passport> parseInput() {
    List<String> input = InputLoader.loadForPackage(this.getClass().getPackageName());
    List<Passport> passports = new ArrayList<>();

    Passport currentPassport = new Passport();
    for (String line : input) {
      boolean isSeperator = line.equals("");
      if (isSeperator) {
        passports.add(currentPassport);
        currentPassport = new Passport();
      } else {
        currentPassport.addCreds(line);
      }
    }
    passports.add(currentPassport);

    return passports;
  }
}
