package Day12;

public class Part2 extends Base {
  Waypoint waypoint = new Waypoint(1, 10);

  public static void main(String[] args) {
    Part2 program = new Part2();
    int answer = program.run();
    System.out.println("answer " + answer);
  }

  private int run() {
    loadCommands();
    executeManeuvers();
    return ship.getManhattanDistance();
  }

  private void executeManeuvers() {
    commands.forEach(this::executeManeuver);
  }

  private void executeManeuver(Command command) {
    switch (command.action) {
      case N:
        this.waypoint.northSouth += command.value;
        break;
      case S:
        this.waypoint.northSouth -= command.value;
        break;
      case E:
        this.waypoint.eastWest += command.value;
        break;
      case W:
        this.waypoint.eastWest -= command.value;
        break;
      case L:
      case R:
        this.rotate(command);
        break;
      case F:
        this.moveForward(command.value);
        break;
      default:
        throw new RuntimeException();
    }
  }

  private void moveForward(int value) {
    int shipEastWestDelta = (waypoint.eastWest - ship.eastWest) * value;
    int shipNorthSouthDelta = (waypoint.northSouth - ship.northSouth) * value;
    waypoint.eastWest += shipEastWestDelta;
    waypoint.northSouth += shipNorthSouthDelta;
    ship.northSouth += shipNorthSouthDelta;
    ship.eastWest += shipEastWestDelta;
  }

  private void rotate(Command command) {
    int numSteps = command.value / 90;

    for (int i = 0; i < numSteps; i++) {
      int eastWestDelta = waypoint.eastWest - ship.eastWest;
      int northSouthDelta = waypoint.northSouth - ship.northSouth;
      waypoint.eastWest =
          ship.eastWest
              + (command.action.equals(Command.Action.R) ? northSouthDelta : -northSouthDelta);

      waypoint.northSouth =
          ship.northSouth
              + (command.action.equals(Command.Action.R) ? -eastWestDelta : eastWestDelta);
    }
  }
}
