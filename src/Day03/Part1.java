package Day03;

import java.util.List;

import util.InputLoader;

public class Part1 extends Base {

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
}
