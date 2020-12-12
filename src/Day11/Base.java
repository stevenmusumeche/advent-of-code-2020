package Day11;

import java.util.Arrays;
import java.util.List;

abstract class Base {
    Position[][] grid;
    Position[][] prevGrid;
    int numRows;
    int numCols;

    abstract Position getNextPosition(int row, int col);

    protected void buildGrid(List<String> lines) {
        numRows = lines.size();
        numCols = lines.get(0).length();

        grid = new Position[numRows][numCols];

        int row = 0;
        for (String line : lines) {
            grid[row++] = Arrays.stream(line.split("")).map(Position::fromValue).toArray(Position[]::new);
        }
    }

    protected void printGrid(Position[][] grid) {
        for (Position[] row : grid) {
            for (Position col : row) {
                System.out.print(col.value);
            }
            System.out.print("\n");
        }
    }

    protected Position[][] deepCopyGrid(Position[][] originalGrid) {
        Position[][] result = new Position[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            result[r] = originalGrid[r].clone();
        }
        return result;
    }


    protected boolean shuffle() {
        boolean changed = false;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Position curPosition = prevGrid[row][col];
                Position nextPosition = getNextPosition(row, col);
                if (!nextPosition.equals(curPosition)) {
                    changed = true;
                    grid[row][col] = nextPosition;
                }
            }
        }

        return changed;
    }
}
