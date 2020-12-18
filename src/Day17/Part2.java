package Day17;

import java.util.*;
import java.util.function.Consumer;

public class Part2 extends Base {

  protected SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>>>
      state = new TreeMap<>();

  public static void main(String[] args) {
    Part2 program = new Part2();
    int answer = program.run();
    System.out.println("Answer " + answer);
  }

  public Part2() {
    state.put(0, new TreeMap<>());
  }

  private int run() {
    state.get(0).put(0, buildInitialSlice());
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
    for (int wIndex = state.firstKey(); wIndex <= state.lastKey(); wIndex++) {
      for (int zIndex = state.get(wIndex).firstKey();
          zIndex <= state.get(wIndex).lastKey();
          zIndex++) {
        for (int rowIndex = state.get(wIndex).get(zIndex).firstKey();
            rowIndex <= state.get(wIndex).get(zIndex).lastKey();
            rowIndex++) {
          for (int colIndex = state.get(wIndex).get(zIndex).get(rowIndex).firstKey();
              colIndex <= state.get(wIndex).get(zIndex).get(rowIndex).lastKey();
              colIndex++) {
            State myState = getCube(rowIndex, colIndex, zIndex, wIndex);
            consumer.accept(myState);
          }
        }
      }
    }
  }

  protected SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>>>
      runCycle() {
    SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>>>
        nextState = cloneState();

    for (int wIndex = state.firstKey(); wIndex <= state.lastKey(); wIndex++) {
      for (int zIndex = state.get(wIndex).firstKey();
          zIndex <= state.get(wIndex).lastKey();
          zIndex++) {
        for (int rowIndex = state.get(wIndex).get(zIndex).firstKey();
            rowIndex <= state.get(wIndex).get(zIndex).lastKey();
            rowIndex++) {
          for (int colIndex = state.get(wIndex).get(zIndex).get(rowIndex).firstKey();
              colIndex <= state.get(wIndex).get(zIndex).get(rowIndex).lastKey();
              colIndex++) {
            State myState = getCube(rowIndex, colIndex, zIndex, wIndex);
            int numActiveNeighbors = numActiveNeighbors(rowIndex, colIndex, zIndex, wIndex);

            State myNextState;
            if (myState.equals(State.Active)) {
              myNextState =
                  numActiveNeighbors >= 2 && numActiveNeighbors <= 3
                      ? State.Active
                      : State.Inactive;
            } else {
              myNextState = numActiveNeighbors == 3 ? State.Active : State.Inactive;
            }
            nextState.get(wIndex).get(zIndex).get(rowIndex).put(colIndex, myNextState);
          }
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

  protected SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>> inactiveSuperSlice(
      int minZ, int maxZ, int minRows, int maxRows, int minCols, int maxCols) {
    SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>> superSlice = new TreeMap<>();
    for (int i = minZ - 1; i <= maxZ + 1; i++) {
      superSlice.put(i, inactiveSlice(minRows, maxRows, minCols, maxCols));
    }

    return superSlice;
  }

  protected State getCube(int row, int col, int z, int w) {
    return state
        .getOrDefault(w, new TreeMap<>())
        .getOrDefault(z, new TreeMap<>())
        .getOrDefault(row, new TreeMap<>())
        .getOrDefault(col, State.Inactive);
  }

  protected List<int[]> getNeighborCoords(int row, int col, int z, int w) {
    List<int[]> neighbors = new ArrayList<>();

    for (int wIndex = w - 1; wIndex <= w + 1; wIndex++) {
      for (int zIndex = z - 1; zIndex <= z + 1; zIndex++) {
        for (int rowIndex = row - 1; rowIndex <= row + 1; rowIndex++) {
          for (int colIndex = col - 1; colIndex <= col + 1; colIndex++) {
            // don't add self
            if (rowIndex == row && colIndex == col && zIndex == z && wIndex == w) continue;

            // add neighbor
            int[] neighbor = {rowIndex, colIndex, zIndex, wIndex};
            neighbors.add(neighbor);
          }
        }
      }
    }

    return neighbors;
  }

  protected int numActiveNeighbors(int row, int col, int z, int w) {
    return (int)
        getNeighborCoords(row, col, z, w).stream()
            .map(coords -> getCube(coords[0], coords[1], coords[2], coords[3]))
            .filter(state -> state.equals(State.Active))
            .count();
  }

  protected SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>>>
      cloneState() {
    int minZ = state.get(0).firstKey();
    int maxZ = state.get(0).lastKey();
    int minRow = state.get(0).get(0).firstKey();
    int maxRow = state.get(0).get(0).lastKey();
    int minCol = state.get(0).get(0).get(0).firstKey();
    int maxCol = state.get(0).get(0).get(0).lastKey();

    SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>>>
        nextState = new TreeMap<>();

    for (int wIndex : state.keySet()) {

      SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>> superSlice =
          new TreeMap<>();

      for (int zIndex : state.get(wIndex).keySet()) {

        SortedMap<Integer, SortedMap<Integer, State>> slice = new TreeMap<>();
        for (int rowIndex : state.get(wIndex).get(zIndex).keySet()) {
          SortedMap<Integer, State> row = new TreeMap<>();
          for (int colIndex : state.get(wIndex).get(zIndex).get(rowIndex).keySet()) {
            row.put(colIndex, state.get(wIndex).get(zIndex).get(rowIndex).get(colIndex));
          }
          // add empty columns
          row.put(row.firstKey() - 1, State.Inactive);
          row.put(row.lastKey() + 1, State.Inactive);
          slice.put(rowIndex, row);
        }
        slice.put(slice.firstKey() - 1, inactiveRow(minCol, maxCol));
        slice.put(slice.lastKey() + 1, inactiveRow(minCol, maxCol));
        superSlice.put(zIndex, slice);
      }
      superSlice.put(superSlice.firstKey() - 1, inactiveSlice(minRow, maxRow, minCol, maxCol));
      superSlice.put(superSlice.lastKey() + 1, inactiveSlice(minRow, maxRow, minCol, maxCol));
      nextState.put(wIndex, superSlice);
    }

    nextState.put(
        nextState.firstKey() - 1, inactiveSuperSlice(minZ, maxZ, minRow, maxRow, minCol, maxCol));
    nextState.put(
        nextState.lastKey() + 1, inactiveSuperSlice(minZ, maxZ, minRow, maxRow, minCol, maxCol));

    return nextState;
  }

  protected void print(
      SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, SortedMap<Integer, State>>>> state) {
    for (int wIndex : state.keySet()) {
      for (int zIndex : state.get(wIndex).keySet()) {
        System.out.println("\nz=" + zIndex + " w=" + wIndex);
        for (int rowIndex : state.get(wIndex).get(zIndex).keySet()) {
          for (int colIndex : state.get(wIndex).get(zIndex).get(rowIndex).keySet()) {
            State myState = getCube(rowIndex, colIndex, zIndex, wIndex);
            System.out.print(myState.equals(State.Inactive) ? "." : "#");
          }
          System.out.println();
        }
        System.out.println();
      }
    }
  }
}
