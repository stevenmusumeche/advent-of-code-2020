package Day07;

import java.util.*;

public class Part1 extends Base {
    public static void main(String[] args) {
        Part1 program = new Part1();
        int answer = program.run();
        System.out.println(answer);
    }

    private int run() {
        parseInput();
        Queue<Bag> queue = new LinkedList<>();
        Set<Bag> answer = new HashSet<>();
        queue.add(this.bags.get("shiny gold"));
        while(queue.size() > 0) {
            Bag cur = queue.poll();
            cur.getParents().forEach(parent -> {
                answer.add(parent);
                queue.add(parent);
            });
        }
        return answer.size();
    }
}
