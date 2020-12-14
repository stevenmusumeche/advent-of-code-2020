package Day13;

import util.InputLoader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Part2 extends Base {
  public static void main(String[] args) {
    Part2 program = new Part2();
    long answer = program.run();
    System.out.println("answer " + answer);
  }

  private long run() {
    loadInput();

    long leastCommonMultiple = -1;
    long time = -1;
    int index = 0;
    while (true) {
      int curBus = busIds.get(index);

      // skip "x" buses
      if (curBus == -1) {
        index++;
        continue;
      }

      // initialize LCM
      if (leastCommonMultiple == -1) {
        leastCommonMultiple = curBus;
        time = curBus - index;
        index++;
        continue;
      }

      if((time + index) % curBus == 0) {
        index++;

        // have we checked all buses?
        if(index >= busIds.size()) {
          return time;
        }

        leastCommonMultiple *= curBus;
        continue;
      }

      time += leastCommonMultiple;

    }

    //    int largest = busIds.stream().filter(Objects::nonNull).max(Integer::compare).orElse(-1);
    //    int largestIndex = busIds.indexOf(largest);
    //    long cur = largest - largestIndex;
    //    while (true) {
    //      boolean allGood = true;
    //      for (int i = 0; i < busIds.size(); i++) {
    //        int curBus = busIds.get(i);
    //        if (curBus == -1) continue;
    //        long mod = (cur + i) % curBus;
    //        if (mod != 0) {
    //          allGood = false;
    //          break;
    //        }
    //      }
    //      if (allGood) return cur;
    //      cur += largest;
    //    }
  }

  protected void loadInput() {
    List<String> input = InputLoader.loadForPackage(this.getClass().getPackageName());
    this.busIds =
        Arrays.stream(input.get(1).split(","))
            .map(x -> x.equals("x") ? -1 : Integer.parseInt(x))
            .collect(Collectors.toList());
  }

  public static long lcm_of_array_elements(int[] element_array) {
    long lcm_of_array_elements = 1;
    int divisor = 2;

    while (true) {
      int counter = 0;
      boolean divisible = false;

      for (int i = 0; i < element_array.length; i++) {

        // lcm_of_array_elements (n1, n2, ... 0) = 0.
        // For negative number we convert into
        // positive and calculate lcm_of_array_elements.

        if (element_array[i] == 0) {
          return 0;
        } else if (element_array[i] < 0) {
          element_array[i] = element_array[i] * (-1);
        }
        if (element_array[i] == 1) {
          counter++;
        }

        // Divide element_array by devisor if complete
        // division i.e. without remainder then replace
        // number with quotient; used for find next factor
        if (element_array[i] % divisor == 0) {
          divisible = true;
          element_array[i] = element_array[i] / divisor;
        }
      }

      // If divisor able to completely divide any number
      // from array multiply with lcm_of_array_elements
      // and store into lcm_of_array_elements and continue
      // to same divisor for next factor finding.
      // else increment divisor
      if (divisible) {
        lcm_of_array_elements = lcm_of_array_elements * divisor;
      } else {
        divisor++;
      }

      // Check if all element_array is 1 indicate
      // we found all factors and terminate while loop.
      if (counter == element_array.length) {
        return lcm_of_array_elements;
      }
    }
  }
}
