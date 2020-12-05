package Day05;

import java.util.*;
import java.util.stream.Collectors;

import util.InputLoader;

public class Base {
  protected List<BoardingPass> parseInput() {
    List<String> input = InputLoader.loadForPackage(this.getClass().getPackageName());
    return input.stream().map(x -> new BoardingPass(x)).collect(Collectors.toList());
  }
}
