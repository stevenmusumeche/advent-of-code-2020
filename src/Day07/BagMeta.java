package Day07;

public class BagMeta {
    private final int count;
    private final Bag bag;

    public BagMeta(int count, Bag bag) {
        this.count = count;
        this.bag = bag;
    }

    public int getCount() {
        return count;
    }

    public Bag getBag() {
        return bag;
    }
}
