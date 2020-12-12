package Day12;


import static Day12.Direction.*;

public class Ship extends Positional {
    public Ship() {
        super(0, 0);
    }

    Direction direction = EAST;
}
