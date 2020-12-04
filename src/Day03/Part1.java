package Day03;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.InputLoader;

public class Part1 {

  List<List<String>> grid;

  public static void main(String[] args) {
    try {
      Part1 program = new Part1();
      int answer = program.run();
      System.out.printf("Answer %d%n", answer);
    } catch (Exception e) {
      System.out.println("Exception!");
      System.out.println(e);
    }
  }

  private int run() {
    List<String> input = InputLoader.loadForPackage(this.getClass().getPackageName());
    grid = buildGrid(input);
    printGrid(grid);

    int numTrees = 0;
    int curRow = 0;
    int curCol = 0;
    int numRows = grid.size();
    int numCols = grid.get(0).size();

    while(curRow < numRows - 1) {
      curCol = (curCol + 3) % numCols;
      curRow += 1;
      if(isTree(curRow, curCol)) {
        numTrees++;
      }
    }
    
    return numTrees;
  }

  private boolean isTree(int row, int col) {
    return grid.get(row).get(col).equals("#");
  }

  private List<List<String>> buildGrid(List<String> lines) {
    List<List<String>> grid = new ArrayList<>();
    for (String line : lines) {
      grid.add(Arrays.asList(line.split("")));
    }
    return grid;
  }

  private void printGrid(List<List<String>> grid) {
    for (List<String> row : grid) {
      for (String col : row) {
        System.out.print(col);
      }
      System.out.print("\n");
    }
  }
}
