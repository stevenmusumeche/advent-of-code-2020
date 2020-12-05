package Day04;

import java.util.List;

public class Part2 extends Base {
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

  private int run() {
    List<Passport> passports = parseInput();
    return (int) passports.stream().filter(passport -> passport.isValid()).count();
  }
}
