package Day10;

import util.InputLoader;

import java.util.*;
import java.util.stream.Collectors;

public class Base {
    List<Integer> input;
    Set<Integer> adapters = new HashSet<>();
    Map<Integer, Integer> diffCount = new HashMap<>();

    protected void loadInput() {
        input = InputLoader.loadForPackage("Day10").stream().map(Integer::parseInt).collect(Collectors.toList());
        adapters.addAll(input);
    }
}
