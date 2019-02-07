package gol;

/**
 * A representation of a cell's position
 */
class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static byte DEAD = 0;
    public static byte ALIVE = 1;
}


