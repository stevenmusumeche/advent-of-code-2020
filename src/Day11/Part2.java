package Day11;

import util.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Day11.Position.*;

public class Part2 extends Base {
    public static void main(String[] args) {
        Part2 program = new Part2();
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
                // answer found
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

    protected List<Position> getAdjacent(int row, int col) {
        List<Position> adj = new ArrayList<>();

        // up
        for(int r = row - 1; r >= 0; r--) {
            Position cur = prevGrid[r][col];
            if(cur.isSeat()) {
                adj.add(cur);
                break;
            }
        }

        // down
        for(int r = row + 1; r < numRows; r++) {
            Position cur = prevGrid[r][col];
            if(cur.isSeat()) {
                adj.add(cur);
                break;
            }
        }

        // left
        for(int c = col - 1; c >= 0; c--) {
            Position cur = prevGrid[row][c];
            if(cur.isSeat()) {
                adj.add(cur);
                break;
            }
        }

        // right
        for(int c = col + 1; c < numCols; c++) {
            Position cur = prevGrid[row][c];
            if(cur.isSeat()) {
                adj.add(cur);
                break;
            }
        }

        int r, c;

        // up left
        r = row - 1;
        c = col - 1;
        while(r >= 0 && c >= 0) {
            Position cur = prevGrid[r][c];
            if (cur.isSeat()) {
                adj.add(cur);
                break;
            } else {
                r--;
                c--;
            }
        }

        // up right
        r = row - 1;
        c = col + 1;
        while(r >= 0 && c < numCols) {
            Position cur = prevGrid[r][c];
            if (cur.isSeat()) {
                adj.add(cur);
                break;
            } else {
                r--;
                c++;
            }
        }

        // down left
        r = row + 1;
        c = col - 1;
        while(r < numRows && c >= 0) {
            Position cur = prevGrid[r][c];
            if (cur.isSeat()) {
                adj.add(cur);
                break;
            } else {
                r++;
                c--;
            }
        }


        // down right
        r = row + 1;
        c = col + 1;
        while(r < numRows && c < numCols) {
            Position cur = prevGrid[r][c];
            if (cur.isSeat()) {
                adj.add(cur);
                break;
            } else {
                r++;
                c++;
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
                return numAdjOccupied >= 5 ? EMPTY : OCCUPIED;
            default:
                throw new RuntimeException();
        }
    }
}
