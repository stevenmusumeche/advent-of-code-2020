package Day12;

abstract class Positional {
    int northSouth;
    int eastWest;

    public Positional(int northSouth, int eastWest) {
        this.northSouth = northSouth;
        this.eastWest = eastWest;
    }

    int getManhattanDistance() {
        return Math.abs(eastWest) + Math.abs(northSouth);
    }
}
