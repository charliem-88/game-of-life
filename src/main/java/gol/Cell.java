package gol;

/**
 * create a cell based on x and y co-ordinates on the grid. Also set the cell to DEAD or ALIVE based on value.
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


