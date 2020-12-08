package Day08;

import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instruction {
    final private String operation;
    final private int argument;
    private boolean executed = false;

    private Instruction(String operation, int argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public static Instruction create(String instruction) {
        Pattern p = Pattern.compile("^(?<operation>[\\w]{3}) (?<argument>[+\\-\\d]+)$");
        Matcher m = p.matcher(instruction);
        if (!m.find()) {
            throw new RuntimeException();
        }
        return new Instruction(m.group("operation"), Integer.parseInt(m.group("argument")));
    }

    public IntFunction<Integer> getAccumulatorOp() {
        IntFunction<Integer> accOp = (curAcc) -> curAcc + this.getArgument();
        IntFunction<Integer> noOp = (curAcc) -> curAcc;

        if ("acc".equals(this.operation)) {
            return accOp;
        } else {
            return noOp;
        }
    }

    public IntFunction<Integer> getIndexOp() {
        IntFunction<Integer> addOneOp = (curIndex) -> curIndex + 1;
        IntFunction<Integer> jumpOp = (curIndex) -> curIndex + this.getArgument();

        switch(this.operation) {
            case "acc":
            case "nop":
                return addOneOp;
            case "jmp":
                return jumpOp;
        }

        throw new RuntimeException("unknown operation");
    }

    public String getOperation() {
        return operation;
    }

    public int getArgument() {
        return argument;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }
}
