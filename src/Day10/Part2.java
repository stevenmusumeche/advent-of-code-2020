package Day10;

import java.util.HashMap;
import java.util.Map;

public class Part2 extends Base {

    Map<Long, Long> memo = new HashMap<>();

    public static void main(String[] args) {
        Part2 program = new Part2();
        long answer = program.run();
        System.out.println("Answer " + answer);
    }

    long run() {
        loadInput();
        return calcNumArrangements(0);
    }

    private long calcNumArrangements(long cur) {
        if(cur == deviceRating - 3) {
            return 1;
        } else if (cur > deviceRating - 3) {
            return 0;
        }

        if(memo.containsKey(cur)) return memo.get(cur);

        long count = 0;
        if(adapters.contains(cur + 1)) {
            count += calcNumArrangements(cur + 1);
        }
        if(adapters.contains(cur + 2)) {
            count += calcNumArrangements(cur + 2);
        }
        if(adapters.contains(cur + 3)) {
            count += calcNumArrangements(cur + 3);
        }

        memo.put(cur, count);
        return count;
    }
}
