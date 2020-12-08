package Day07;

import util.InputLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base {
    protected Map<String, Bag> bags = new HashMap<>();

    protected void parseInput() {
        List<String> input = InputLoader.loadForPackage("Day07");

        Pattern pattern = Pattern.compile("(?<count>[\\d]+|no) (?<color>.*?) bags?");
        input.forEach(line -> {
            String[] pieces = line.split(" contain ");
            String color = pieces[0].substring(0, pieces[0].length() - 5);
            Bag parentBag = this.bags.getOrDefault(color, new Bag(color));
            this.bags.put(color, parentBag);

            Matcher matcher = pattern.matcher(pieces[1]);
            while (matcher.find()) {
                if(!matcher.group("count").equals("no")) {
                    Bag childBag = this.bags.getOrDefault(matcher.group("color"), new Bag(matcher.group("color")));
                    this.bags.put(childBag.getName(), childBag);
                    childBag.addParent(parentBag);
                    parentBag.addChild(Integer.parseInt(matcher.group("count")), childBag);
                }
            }
        });
    }
}
