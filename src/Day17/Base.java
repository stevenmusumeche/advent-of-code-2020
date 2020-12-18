package Day17;

import util.InputLoader;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

abstract class Base {
  protected enum State {
    Active,
    Inactive
  }

  protected final int NUM_CYCLES = 6;

  protected SortedMap<Integer, SortedMap<Integer, State>> buildInitialSlice() {
    List<String> lines = InputLoader.loadForPackage(this.getClass().getPackageName());

    SortedMap<Integer, SortedMap<Integer, State>> slice = new TreeMap<>();
    for (int rowIndex = 0; rowIndex < lines.size(); rowIndex++) {
      SortedMap<Integer, State> row = new TreeMap<>();
      String line = lines.get(rowIndex);

      for (int charIndex = 0; charIndex < line.length(); charIndex++) {
        State cubeState = line.charAt(charIndex) == '.' ? State.Inactive : State.Active;
        row.put(charIndex, cubeState);
      }

      slice.put(rowIndex, row);
    }

    return slice;
  }
}
