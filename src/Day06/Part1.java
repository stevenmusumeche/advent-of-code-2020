package Day06;


import util.InputLoader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) {
        Part1 program = new Part1();
        int answer = program.run();
        System.out.println(answer);
    }

    private int run() {
        String input = InputLoader.loadAsString("Day06");
        String[] groups = input.split("\n\n");
        List<Set<String>> groupChars = Arrays.stream(groups).map(g -> {
            String[] nonUniqueChars = g.replace("\n", "").split("");
            return new HashSet<>(Arrays.asList(nonUniqueChars));
        }).collect(Collectors.toList());

        return groupChars.stream().mapToInt(Set::size).sum();
    }
}
