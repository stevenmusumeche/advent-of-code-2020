package Day05;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class Part2 extends Base {
  public static void main(String[] args) {
    Part2 program = new Part2();
    int mySeat = program.run();
    System.out.println(mySeat);
    
  }

  private int run() {
    List<BoardingPass> boardingPasses = parseInput();
    int[] seatIds = boardingPasses.stream().mapToInt(bp -> bp.getSeatId()).toArray();
    Arrays.sort(seatIds);
    for(int i = 1; i < seatIds.length - 1; i++) {
      int prev = seatIds[i-1];
      int cur = seatIds[i];
      if(cur - prev > 1) {
        int possibleSeat = cur - 1;
        if(prev == possibleSeat - 1) {
          return possibleSeat;
        }
      }
    }

    throw new InputMismatchException();
  }
}
