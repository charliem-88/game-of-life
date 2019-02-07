package gol;

/**
 * represents the immutable state of a board
 */
class GameState {

    private final byte[][] grid;

    public int getGridSize() {
        int gridSize = grid.length;
        return gridSize;
    }

    /**
     * create output representing the grid for printing to the console - loop over the cells and set alive cells to "♦"
     * and dead cells to "♢"
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        int size = grid.length;

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                byte cell = grid[y][x];

                if (cell == 0) {
                    sb.append("♢");
                } else {
                    sb.append("♦");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Constructs a new game state from the provided grid
     */
    public GameState(byte[][] grid) {
        this.grid = grid;
    }

    /**
     * Return the number of neighbours of a cell when passed the cell's co-ordinates. Used in the rules' functions.
     */
    public int neighbourhood(int x, int y) {

        int neighbours = 0;

        // Its neighbour above is at        grid[y - 1][x]
        // Its neighbour to the right is at grid[y][x + 1]

        if ((y > 0) && (x > 0) && (grid[y - 1][x - 1] == 1)) {
            neighbours += 1; //will check a neighbour to the top left of the cell, but not cells that are along the top and left sides of the grid
        }
        if ((y > 0) && (grid[y - 1][x] == 1)) {
            neighbours += 1; // will check for a neighbour directly above the cell, not for a cell located along the top of the grid
        }
        if ((y > 0) && (x < grid.length - 1) && (grid[y - 1][x + 1] == 1)) {
            neighbours += 1; // check for neighbour top right of the cell, not cells on top of right side of grid
        }
        if ((x > 0) && (grid[y][x - 1] == 1)) {
            neighbours += 1; // check for neighbour check neighbour to left of cell, not cells on left side of grid
        }
        if ((x < grid.length - 1) && (grid[y][x + 1] == 1)) {
            neighbours += 1; // check for neighbour right of the cell, not cells on right side of the grid
        }
        if ((y < grid.length - 1) && (x > 0) && (grid[y + 1][x - 1] == 1)) {
            neighbours += 1; // check for neighbour bottom left of cell, not cells on left or bottom row
        }
        if ((y < grid.length - 1) && (grid[y + 1][x] == 1)) {
            neighbours += 1; // check for cells under cell, not cells on bottom row
        }
        if ((y < grid.length - 1) && (x < grid.length - 1) && (grid[y + 1][x + 1] == 1)) {
            neighbours += 1; //check for cells bottom right of cell, not cells on bottom or right edge of grid
        }
        return neighbours;
    }

    /**
     * Applies the Game of Life rules to the current game state, returning a new GameState
     */
    public GameState advance() {
        int size = grid.length;
        byte[][] out = new byte[size][size];

        // Will set this if the grid needs to expand
        // grid will always expand if there is a live cell on the edge - live cells will never have less than 8
        // surrounding co-ordinates
        boolean needsExpand = false;

        // Loop over all the cells in the grid
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                byte cell = grid[y][x];

                // cell=0 means its dead, 1 means alive
                boolean cellIsAlive = cell == Cell.ALIVE;

                boolean cellShouldLive = cellIsAlive;

                int neighbours = neighbourhood(x, y);

                if (cellIsAlive) {
                    // under crowding rule
                    if (neighbours < 2) {
                        cellShouldLive = false;
                    }
                    // over population rule
                    else if (neighbours > 3) {
                        cellShouldLive = false;
                    }
                } else {
                    // creation of life rule
                    if (neighbours == 3) {
                        cellShouldLive = true;
                    }
                }
                if (cellShouldLive) {
                    // It's alive!
                    out[y][x] = Cell.ALIVE;
                    if (x == 0 || y == 0 || x == size - 1 || y == size - 1) {
                        //checks for live cells around the edge of the board in case it needs to expand
                        needsExpand = true;
                    }
                } else {
                    // Kill the cell
                    out[y][x] = Cell.DEAD;
                }
            }
        }

        // Find out if need a bigger grid then...
        if (needsExpand) {
            // Have created a cell on the very edge of the old grid, need to expand the grid
            int newSize = size + 2;

            // Create a new grid
            byte[][] expanded = new byte[newSize][newSize];

            // Copy the contents of the old grid into the new
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    expanded[x + 1][y + 1] = out[x][y];
                }
            }
            out = expanded;
        }

        // Note it produces a brand new GameState rather than modifying the original one
        return new GameState(out);
    }

    /**
     * Returns if a particular cell is alive or dead
     */
    public boolean isAlive(int x, int y) {
        return grid[y][x] == Cell.ALIVE;
    }

    /**
     * Returns the total number of alive cells.
     */
    public int aliveCount() {
        int count = 0;
        int size = grid.length;

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                byte cell = grid[y][x];

                if (cell == Cell.ALIVE) count += 1;
            }
        }

        return count;
    }
}
