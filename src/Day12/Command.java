package Day12;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {
    enum Action {
        N,
        S,
        E,
        W,
        L,
        R,
        F
    }

    Action action;
    int value;

    private Command(Action action, int value) {
        this.action = action;
        this.value = value;
    }

    public static Command make(String instruction) {
        Pattern p = Pattern.compile("^(?<action>[A-Z])(?<value>[\\d]+)$");
        Matcher m = p.matcher(instruction);
        if (!m.matches()) {
            throw new RuntimeException("error parsing instruction");
        }
        return new Command(
                Action.valueOf(m.group("action")),
                Integer.parseInt(m.group("value"))
        );
    }
}
