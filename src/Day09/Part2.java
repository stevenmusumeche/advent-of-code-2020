package Day09;

import java.util.List;

public class Part2 extends Base {
    public static void main(String[] args) {
        Part2 program = new Part2();
        long answer = program.run();
        System.out.println("Answer " + answer);
    }

    private long run() {
        input = loadInput();
        buildPreamble();
        long ruleBreaker = findRuleBreaker();
        List<Long> sumSet = findSumSet(ruleBreaker);

        long min = sumSet.stream().mapToLong(v -> v).min().orElseThrow();
        long max = sumSet.stream().mapToLong(v -> v).max().orElseThrow();
        return min + max;
    }

    private List<Long> findSumSet(long target) {
        int left = 0;
        while(left < input.size() - 1) {
            long sum = input.get(left);
            int right = left + 1;
            while (sum < target) {
                sum += input.get(right);
                if(sum == target) {
                    // found answer, so return the subset of the list from left to right
                    return input.subList(left, right+1);
                }
                right++;
            }
            left++;
        }

        throw new RuntimeException("unable to solve");

    }

}
