package Day12;

import util.InputLoader;

import java.util.List;
import java.util.stream.Collectors;

abstract class Base {
  List<Command> commands;
  Ship ship = new Ship();

  protected void loadCommands() {
    List<String> input = InputLoader.loadForPackage(this.getClass().getPackageName());
    commands = input.parallelStream().map(Command::make).collect(Collectors.toList());
  }
}
