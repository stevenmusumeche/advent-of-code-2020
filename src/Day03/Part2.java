package Day03;

import java.util.List;

import util.InputLoader;

public class Part2 extends Base {
  public static void main(String[] args) {
    try {
      Part2 program = new Part2();
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
    // printGrid(grid);

    int numRows = grid.size();
    int numCols = grid.get(0).size();

    int[][] slopes = { { 1, 1 }, { 3, 1 }, { 5, 1 }, { 7, 1 }, { 1, 2 } };
    int product = 1;
    for (int[] slope : slopes) {
      int rowDelta = slope[1];
      int colDelta = slope[0];
      int curRow = 0;
      int curCol = 0;
      int numTrees = 0;

      while (curRow < numRows - rowDelta) {
        curCol = (curCol + colDelta) % numCols;
        curRow += rowDelta;

        // System.out.printf("%d %d %n", curRow, curCol);
        if (isTree(curRow, curCol)) {
          numTrees++;
        }
      }

      product *= numTrees;
    }

    return product;
  }
}
