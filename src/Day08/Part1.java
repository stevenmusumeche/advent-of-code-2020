package Day08;

import java.util.List;

public class Part1 extends Base {


    public static void main(String[] args) {
        Part1 program = new Part1();
        int answer = program.run();
        System.out.println("Answer: " + answer);
    }

    private int run() {
        List<Instruction> instructions = parseInput();

        int accumulator = 0;
        int index = 0;

        Instruction cur = instructions.get(index);
        while(!cur.isExecuted()) {
            accumulator = cur.getAccumulatorOp().apply(accumulator);
            index = cur.getIndexOp().apply(index);
            cur.setExecuted(true);
            cur = instructions.get(index);
        }

        return accumulator;
    }
}
