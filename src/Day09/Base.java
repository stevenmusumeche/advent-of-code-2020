package Day09;

import util.InputLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Base {
    final static int PREAMBLE_LIMIT = 25;
    Map<Long, Integer> preamble = new HashMap<>();
    List<Long> input;

    protected List<Long> loadInput() {
        return InputLoader.loadForPackage("Day09").stream().map(Long::parseUnsignedLong).collect(Collectors.toList());
    }

    protected void updatePreamble(int i) {
        // remove furthest back
        long toBeRemoved = input.get(i - PREAMBLE_LIMIT);
        int val = preamble.get(toBeRemoved);
        if (val == 1) {
            preamble.remove(toBeRemoved);
        } else {
            preamble.put(toBeRemoved, val - 1);
        }

        // add newest
        long toBeAdded = input.get(i);
        val = preamble.getOrDefault(toBeAdded, 0);
        preamble.put(toBeAdded, val + 1);
    }

    protected void buildPreamble() {
        for (int i = 0; i < PREAMBLE_LIMIT; i++) {
            long cur = input.get(i);
            preamble.put(cur, preamble.getOrDefault(cur, 0) + 1);
        }
    }

    protected boolean isValid(long target) {
        for(long key: preamble.keySet()) {
            long complement = target - key;
            if(complement == key) {
                continue;
            }
            if(preamble.containsKey(complement)) return true;
        }
        return false;
    }
}
