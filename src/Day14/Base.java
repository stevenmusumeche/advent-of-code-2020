package Day14;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Base {
    final int BITMASK_SIZE = 36;
    Map<Integer, Character> bitmask = new HashMap<>();
    String maskRegex = "^mask = (?<mask>[X10]+)$";
    Pattern maskPattern = Pattern.compile(maskRegex);
    Map<Long, Long> memory = new HashMap<>();

    abstract void processInstruction(String line);
    abstract void buildMaskMapping(String mask);

    protected long calculateAnswer() {
        return memory.values().stream().mapToLong(x -> x).sum();
    }

    protected void setCurrentMask(String line) {
        Matcher m = maskPattern.matcher(line);
        if (!m.matches()) throw new RuntimeException("invariant");
        String mask = m.group("mask");
        buildMaskMapping(mask);
    }

    protected boolean isMask(String line) {
        return line.matches(maskRegex);
    }

    protected String padLeft(String inputString) {
        return String.format("%1$" + BITMASK_SIZE + "s", inputString).replace(' ', '0');
    }
}
