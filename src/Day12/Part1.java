package Day12;

public class Part1 extends Base {
  public static void main(String[] args) {
    Part1 program = new Part1();
    int answer = program.run();
    System.out.println("answer " + answer);
  }

  private int run() {
    loadCommands();
    executeManeuvers();
    return Math.abs(ship.eastWest) + Math.abs(ship.northSouth);
  }

  private void executeManeuvers() {
    commands.forEach(this::executeManeuver);
  }

  private void executeManeuver(Command command) {
    switch (command.action) {
      case N:
        this.ship.northSouth += command.value;
        break;
      case S:
        this.ship.northSouth -= command.value;
        break;
      case E:
        this.ship.eastWest += command.value;
        break;
      case W:
        this.ship.eastWest -= command.value;
        break;
      case L:
      case R:
        this.turn(command);
        break;
      case F:
        this.moveForward(command.value);
        break;
      default:
        throw new RuntimeException();
    }
  }

  private void moveForward(int value) {
    if(ship.direction.equals(Direction.EAST)) {
      ship.eastWest += value;
    } else if (ship.direction.equals(Direction.WEST)) {
      ship.eastWest -= value;
    } else if (ship.direction.equals(Direction.NORTH)) {
      ship.northSouth += value;
    } else if (ship.direction.equals(Direction.SOUTH)) {
      ship.northSouth -= value;
    }
  }

  private void turn(Command command) {
    int numSteps = command.value / 90;
    int delta = command.action.equals(Command.Action.R) ? numSteps : -numSteps;
    int newDirectionVal =
        (Direction.numDirections + ship.direction.value + delta) % Direction.numDirections;
    ship.direction = Direction.getDirection(newDirectionVal);
  }
}
