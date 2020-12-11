package Day10;

import util.InputLoader;

import java.util.*;
import java.util.stream.Collectors;

public class Base {
    List<Long> input;
    Set<Long> adapters = new HashSet<>();
    Map<Integer, Integer> diffCount = new HashMap<>();
    long deviceRating;

    protected void loadInput() {
        input = InputLoader.loadForPackage("Day10").stream().map(Long::parseLong).collect(Collectors.toList());
        adapters.addAll(input);
        deviceRating = Collections.max(input) + 3;
    }
}
