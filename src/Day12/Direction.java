package Day12;

public enum Direction {
  EAST(0),
  SOUTH(1),
  WEST(2),
  NORTH(3);

  public final int value;

  Direction(int value) {
    this.value = value;
  }

  public static Direction getDirection(int value) {
    for (Direction d : Direction.values()) {
      if (d.value == value) return d;
    }
    throw new IllegalArgumentException("Direction not found");
  }

  public final static int numDirections = 4;
}
