package Day08;

import java.util.*;

public class Part2 extends Base {
    public static void main(String[] args) {
        Part2 program = new Part2();
        int answer = program.run();
        System.out.println("Answer: " + answer);
    }

    private int run() {
        List<Instruction> instructions = parseInput();
        Set<Integer> tried = new HashSet<>();

        while (true) {
            List<Instruction> instructionsClone = cloneList(instructions);
            int accumulator = 0;
            int index = 0;
            boolean flipped = false;

            Instruction cur = instructionsClone.get(0);
            while (true) {
                if (isFlippable(cur) && !tried.contains(index) && !flipped) {
                    cur = flip(cur);
                    tried.add(index);
                    flipped = true;
                }
                accumulator = cur.getAccumulatorOp().apply(accumulator);
                index = cur.getIndexOp().apply(index);
                cur.setExecuted(true);

                // no more instructions, winner winner
                if(index == instructionsClone.size()) {
                    return accumulator;
                }

                cur = instructionsClone.get(index);
                if (cur.isExecuted()) {
                    // failed in this attempt, try again
                    break;
                }
            }
        }
    }

    private boolean isFlippable(Instruction i) {
        return i.getOperation().equals("nop") || i.getOperation().equals("jmp");
    }

    private Instruction flip(Instruction i) {
        if (i.getOperation().equals("nop")) {
            return new Instruction("jmp", i.getArgument());
        } else {
            return new Instruction("nop", i.getArgument());
        }
    }

    private List<Instruction> cloneList(List<Instruction> list) {
        List<Instruction> clone = new ArrayList<Instruction>(list.size());
        for (Instruction item : list) clone.add(item.clone());
        return clone;
    }
}
