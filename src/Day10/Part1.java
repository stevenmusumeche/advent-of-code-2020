package Day10;

public class Part1 extends Base {
    public static void main(String[] args) {
        Part1 program = new Part1();
        long answer = program.run();
        System.out.println("Answer " + answer);
    }

    int run() {
        loadInput();
        long cur = 0;
        while(adapters.size() > 0) {
            for(int diff = 1; diff <= 3 ; diff++) {
                long attempt = cur + diff;
                if (adapters.contains(attempt)) {
                    cur = attempt;
                    diffCount.put(diff, diffCount.getOrDefault(diff, 0) + 1);
                    adapters.remove(attempt);
                    break;
                }
            }
        }

        // diff count for "1" * diff count for "3" + 1 for the actual device which is always 3 diff
        return diffCount.get(1) * (diffCount.get(3) + 1);
    }
}
