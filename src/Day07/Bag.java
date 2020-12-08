package Day07;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    final private String name;
    final private List<BagMeta> children = new ArrayList<>();
    final private List<Bag> parents = new ArrayList<>();

    public Bag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addChild(int count, Bag bag) {
        this.children.add(new BagMeta(count, bag));
    }

    public List<BagMeta> getChildren() {
        return children;
    }

    public void addParent(Bag parent) {
        this.parents.add(parent);
    }

    public List<Bag> getParents() {
        return parents;
    }
}
