package Day17;

import util.InputLoader;

import java.util.*;
import java.util.function.Consumer;

public class Part1 extends Base {

  protected SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>> state =
      new TreeMap<>();

  public static void main(String[] args) {
    Part1 program = new Part1();
    int answer = program.run();
    System.out.println("Answer " + answer);
  }

  private int run() {
    state.put(0, buildInitialSlice());
    state = cloneState();

    int curCycle = 0;
    while (curCycle++ < NUM_CYCLES) {
      state = runCycle();
    }

    final int[] counter = {0};
    visitAllCubes(
        (state) -> {
          if (state.equals(State.Active)) {
            counter[0] += 1;
          }
        });

    return counter[0];
  }

  protected void visitAllCubes(Consumer<State> consumer) {
    for (int zIndex = state.firstKey(); zIndex <= state.lastKey(); zIndex++) {
      for (int rowIndex = state.get(zIndex).firstKey();
          rowIndex <= state.get(zIndex).lastKey();
          rowIndex++) {
        for (int colIndex = state.get(zIndex).get(rowIndex).firstKey();
            colIndex <= state.get(zIndex).get(rowIndex).lastKey();
            colIndex++) {
          State myState = getCube(rowIndex, colIndex, zIndex);
          consumer.accept(myState);
        }
      }
    }
  }

  protected SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>> runCycle() {
    SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>> nextState = cloneState();

    for (int zIndex = state.firstKey(); zIndex <= state.lastKey(); zIndex++) {
      for (int rowIndex = state.get(zIndex).firstKey();
          rowIndex <= state.get(zIndex).lastKey();
          rowIndex++) {
        for (int colIndex = state.get(zIndex).get(rowIndex).firstKey();
            colIndex <= state.get(zIndex).get(rowIndex).lastKey();
            colIndex++) {
          State myState = getCube(rowIndex, colIndex, zIndex);
          int numActiveNeighbors = numActiveNeighbors(rowIndex, colIndex, zIndex);

          State myNextState;
          if (myState.equals(State.Active)) {
            myNextState =
                numActiveNeighbors >= 2 && numActiveNeighbors <= 3 ? State.Active : State.Inactive;
          } else {
            myNextState = numActiveNeighbors == 3 ? State.Active : State.Inactive;
          }
          nextState.get(zIndex).get(rowIndex).put(colIndex, myNextState);
        }
      }
    }

    return nextState;
  }

  protected SortedMap<Integer, State> inactiveRow(int minCols, int maxCols) {
    SortedMap<Integer, State> row = new TreeMap<>();

    for (int i = minCols - 1; i <= maxCols + 1; i++) {
      row.put(i, State.Inactive);
    }

    return row;
  }

  protected SortedMap<Integer, SortedMap<Integer, State>> inactiveSlice(
      int minRows, int maxRows, int minCols, int maxCols) {
    SortedMap<Integer, SortedMap<Integer, State>> slice = new TreeMap<>();
    for (int i = minRows - 1; i <= maxRows + 1; i++) {
      slice.put(i, inactiveRow(minCols, maxCols));
    }

    return slice;
  }

  protected State getCube(int row, int col, int z) {
    return state
        .getOrDefault(z, new TreeMap<>())
        .getOrDefault(row, new TreeMap<>())
        .getOrDefault(col, State.Inactive);
  }

  protected List<int[]> getNeighborCoords(int row, int col, int z) {
    List<int[]> neighbors = new ArrayList<>();

    for (int zIndex = z - 1; zIndex <= z + 1; zIndex++) {
      for (int rowIndex = row - 1; rowIndex <= row + 1; rowIndex++) {
        for (int colIndex = col - 1; colIndex <= col + 1; colIndex++) {
          // don't add self
          if (rowIndex == row && colIndex == col && zIndex == z) continue;

          // add neighbor
          int[] neighbor = {rowIndex, colIndex, zIndex};
          neighbors.add(neighbor);
        }
      }
    }

    return neighbors;
  }

  protected int numActiveNeighbors(int row, int col, int z) {
    return (int)
        getNeighborCoords(row, col, z).stream()
            .map(coords -> getCube(coords[0], coords[1], coords[2]))
            .filter(state -> state.equals(State.Active))
            .count();
  }

  protected SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>> cloneState() {
    int minRow = state.get(0).firstKey();
    int maxRow = state.get(0).lastKey();
    int minCol = state.get(0).get(0).firstKey();
    int maxCol = state.get(0).get(0).lastKey();

    SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>> nextState = new TreeMap<>();
    for (int zIndex : state.keySet()) {
      SortedMap<Integer, SortedMap<Integer, State>> slice = new TreeMap<>();
      for (int rowIndex : state.get(zIndex).keySet()) {
        SortedMap<Integer, State> row = new TreeMap<>();
        for (int colIndex : state.get(zIndex).get(rowIndex).keySet()) {
          row.put(colIndex, state.get(zIndex).get(rowIndex).get(colIndex));
        }
        // add empty columns
        row.put(row.firstKey() - 1, State.Inactive);
        row.put(row.lastKey() + 1, State.Inactive);
        slice.put(rowIndex, row);
      }
      slice.put(slice.firstKey() - 1, inactiveRow(minCol, maxCol));
      slice.put(slice.lastKey() + 1, inactiveRow(minCol, maxCol));
      nextState.put(zIndex, slice);
    }

    nextState.put(nextState.firstKey() - 1, inactiveSlice(minRow, maxRow, minCol, maxCol));
    nextState.put(nextState.lastKey() + 1, inactiveSlice(minRow, maxRow, minCol, maxCol));

    return nextState;
  }

  protected void print(SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>> state) {
    for (int zIndex : state.keySet()) {
      System.out.println("\nz=" + zIndex);
      for (int rowIndex : state.get(zIndex).keySet()) {
        for (int colIndex : state.get(zIndex).get(rowIndex).keySet()) {
          State myState = getCube(rowIndex, colIndex, zIndex);
          System.out.print(myState.equals(State.Inactive) ? "." : "#");
        }
        System.out.println();
      }
      System.out.println();
    }
  }
}
