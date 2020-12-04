package Day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Base {
  List<List<String>> grid;

  protected boolean isTree(int row, int col) {
    return grid.get(row).get(col).equals("#");
  }

  protected List<List<String>> buildGrid(List<String> lines) {
    List<List<String>> matrix = new ArrayList<>();
    for (String line : lines) {
      matrix.add(Arrays.asList(line.split("")));
    }
    return matrix;
  }

  public static void printGrid(List<List<String>> grid) {
    for (List<String> row : grid) {
      for (String col : row) {
        System.out.print(col);
      }
      System.out.print("\n");
    }
  }
}
