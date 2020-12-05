package Day05;

import java.util.Arrays;
import java.util.List;

public class Part1 extends Base {
  public static void main(String[] args) {
    Part1 program = new Part1();
    int maxSeatId = program.run();
    System.out.println(maxSeatId);
  }

  private int run() {
    List<BoardingPass> boardingPasses = parseInput();
    int[] seatIds = boardingPasses.stream().mapToInt(bp -> bp.getSeatId()).toArray();
    return Arrays.stream(seatIds).max().getAsInt();
  }
}
