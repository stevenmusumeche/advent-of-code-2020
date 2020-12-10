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
        return findRuleBreaker();
    }
}
