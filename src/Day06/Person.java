package Day06;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Person {
    private final Set<String> answers;

    public Person(String answers) {
        this.answers = new HashSet<>(Arrays.asList(answers.split("")));
    }

    public Set<String> getAnswers() {
        return answers;
    }
}
