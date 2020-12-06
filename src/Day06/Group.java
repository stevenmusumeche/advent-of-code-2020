package Day06;

import java.util.*;

public class Group {
    final private List<Person> people = new ArrayList<>();

    public void addPerson(Person person) {
        people.add(person);
    }

    public int getNumConsensusAnswers() {
        Set<String> allAnswers = buildAnswerList();
        int numWithConsensus = 0;
        for(String answer : allAnswers) {
            if(allPeopleHaveAnswer(answer)) {
                numWithConsensus++;
            }
        }

        return numWithConsensus;
    }

    private boolean allPeopleHaveAnswer(String answer) {
        return people.parallelStream().allMatch(person -> person.getAnswers().contains(answer));
    }

    private Set<String> buildAnswerList() {
        Set<String> allAnswers = new HashSet<>();
        for(Person person : people) {
            allAnswers.addAll(person.getAnswers());
        }

        return allAnswers;
    }
}
