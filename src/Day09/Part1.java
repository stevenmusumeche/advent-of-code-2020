package Day09;

public class Part1 extends Base {
    public static void main(String[] args) {
        Part1 program = new Part1();
        long answer = program.run();
        System.out.println("Answer " + answer);
    }

    private long run() {
        input = loadInput();
        buildPreamble();
        int i = PREAMBLE_LIMIT;
        while (i < input.size() - 1) {
            long cur = input.get(i);
            if (!isValid(cur)) {
                return cur;
            }
            updatePreamble(i);
            i++;
        }

        return -1;
    }
}
