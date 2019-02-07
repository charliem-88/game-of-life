package gol;

import java.util.List;

/**
 * Utility function for game state
 */
public class GameStateUtil {

    private GameStateUtil(){

    }

    /**
     * Create an initial GameState based on a list of alive Cells
     */

    public static GameState initialise(int size, List<Cell> alive) {
        byte[][] out = new byte[size][size];

        for(int i = 0; i < alive.size(); i ++) {
            Cell cell = alive.get(i);

            out[cell.getY()][cell.getX()] = Cell.ALIVE;
        }

        return new GameState(out);
    }

}
