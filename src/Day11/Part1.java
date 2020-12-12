package Day11;

import util.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static Day11.Position.*;

public class Part1 extends Base {
    Position[][] grid;
    Position[][] prevGrid;
    int numRows;
    int numCols;

    public static void main(String[] args) {
        Part1 program = new Part1();
        int answer = program.run();
        System.out.println("answer " + answer);
    }

    private int run() {
        List<String> input = InputLoader.loadForPackage(this.getClass().getPackageName());
        buildGrid(input);

        prevGrid = grid;
        grid = deepCopyGrid(prevGrid);

        while (true) {
            boolean changed = shuffle();
            if (!changed) {
                long numOccupied = Arrays.stream(grid)
                        .mapToLong(row -> Arrays.stream(row)
                                .filter(col -> col.equals(OCCUPIED))
                                .count())
                        .sum();
                return (int) numOccupied;
            }
            prevGrid = grid;
            grid = deepCopyGrid(prevGrid);
        }
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

    protected List<Position> getAdjacent(int row, int col) {
        List<Position> adj = new ArrayList<>();

        for (int dx = -1; dx <= 1; ++dx) {
            for (int dy = -1; dy <= 1; ++dy) {
                if (dx != 0 || dy != 0) {
                    int r = row + dx;
                    int c = col + dy;
                    if (r >= 0 && r < numRows && c >= 0 && c < numCols) {
                        adj.add(prevGrid[r][c]);
                    }
                }
            }
        }

        return adj;
    }

    protected Position getNextPosition(int row, int col) {
        Position cur = prevGrid[row][col];
        List<Position> adj = getAdjacent(row, col);
        long numAdjOccupied = adj.stream().filter(position -> position.equals(OCCUPIED)).count();

        switch (cur) {
            case FLOOR:
                return FLOOR;
            case EMPTY:
                return numAdjOccupied == 0 ? OCCUPIED : EMPTY;
            case OCCUPIED:
                return numAdjOccupied >= 4 ? EMPTY : OCCUPIED;
            default:
                throw new RuntimeException();
        }
    }

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
}
