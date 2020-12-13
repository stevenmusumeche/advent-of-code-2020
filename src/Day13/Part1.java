package Day13;

import util.InputLoader;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Part1 extends Base {
  public static void main(String[] args) {
    Part1 program = new Part1();
    int answer = program.run();
    System.out.println("answer " + answer);
  }

  private int run() {
    loadInput();

    int timestampAttempt = this.earliestTimetamp;
    while (true) {
      // try
      int finalTimestampAttempt = timestampAttempt;

      Predicate<Integer> filterDepartingBusses =
          b -> {
            int mod = finalTimestampAttempt % b;
            return mod == 0;
          };

      long numMatching = this.busIds.parallelStream().filter(filterDepartingBusses).count();

      if (numMatching > 0) {
        int busId =
            this.busIds.parallelStream().filter(filterDepartingBusses).findFirst().orElseThrow();
        return (timestampAttempt - this.earliestTimetamp) * busId;
      }

      timestampAttempt++;
    }
  }

  protected void loadInput() {
    List<String> input = InputLoader.loadForPackage(this.getClass().getPackageName());
    this.earliestTimetamp = Integer.parseInt(input.get(0));
    this.busIds =
        Arrays.stream(input.get(1).split(","))
            .filter(x -> !x.equals("x"))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
  }
}
