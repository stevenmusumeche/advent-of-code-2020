package Day11;

public enum Position {
    FLOOR("."),
    EMPTY("L"),
    OCCUPIED("#");

    public final String value;
    public boolean isSeat() {
        return this.equals(EMPTY) || this.equals(OCCUPIED);
    }

    Position(String value) {
        this.value = value;
    }

    public static Position fromValue(String value) {
        for (Position e : values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }

}