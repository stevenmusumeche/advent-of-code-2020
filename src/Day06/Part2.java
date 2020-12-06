package Day06;

import util.InputLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) {
        Part2 program = new Part2();
        int answer = program.run();
        System.out.println(answer);
    }

    private int run() {
        String input = InputLoader.loadAsString("Day06");
        List<Group> groups = buildGroups(input);
        return groups.stream().mapToInt(Group::getNumConsensusAnswers).sum();
    }

    private List<Group> buildGroups(String input) {
        String[] rawGroups = input.split("\n\n");
        return Arrays.stream(rawGroups).map(rawGroup -> {
            Group group = new Group();
            for(String rawPerson : rawGroup.split("\n")) {
                Person person = new Person(rawPerson);
                group.addPerson(person);
            }
            return group;
        }).collect(Collectors.toList());
    }

}
