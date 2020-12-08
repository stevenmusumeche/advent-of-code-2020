package Day07;

public class Part2 extends Base {
    public static void main(String[] args) {
        Part2 program = new Part2();
        int answer = program.run();
        System.out.println(answer);
    }

    private int run() {
        parseInput();
        return this.getCountForBag(this.bags.get("shiny gold"), 1) - 1;
    }

    private int getCountForBag(Bag bag, int numBags) {
        int count = 0;

        if (bag.getChildren().size() == 0) {
            return numBags;
        }

        for (BagMeta bagMeta : bag.getChildren()) {
            count += getCountForBag(bagMeta.getBag(), bagMeta.getCount());
        }

        return (numBags * count) + numBags;
    }
}
