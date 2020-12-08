package Day08;

import util.InputLoader;

import java.util.List;
import java.util.stream.Collectors;

public class Base {
    protected List<Instruction> parseInput() {
        return InputLoader.loadForPackage("Day08").stream().map(Instruction::create).collect(Collectors.toList());
    }
}
