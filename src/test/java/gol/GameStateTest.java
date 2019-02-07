package gol;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class GameStateTest {
    @org.junit.Test
    public void initialise() {
        GameState initialState = GameStateUtil.initialise(5, Arrays.asList(new Cell(1, 2)));

        assertTrue(initialState.isAlive(1, 2));
        assertFalse(initialState.isAlive(0, 1));
        assertEquals(1, initialState.aliveCount());
    }

    public void checkSoloCellDies(int x, int y) {

        GameState initialState = GameStateUtil.initialise(5, Arrays.asList(new Cell(x, y)));

        GameState nextState = initialState.advance();

        assertEquals(0, nextState.aliveCount());
    }

    @Test
    public void cellOnItsOwnWillDie() {
        checkSoloCellDies(3, 3);

    }

    // add more tests for the other rules

    @Test
    public void soloCellTopLeftCornerShouldDie() {

        checkSoloCellDies(0, 0);
    }

    @Test
    public void soloCellTopRightCornerShouldDie() {
        checkSoloCellDies(0, 4);
    }

    @Test
    public void soloCellBottomLeftShouldDie() {
        checkSoloCellDies(0, 4);
    }

    @Test
    public void soloCellBottomRightShouldDie() {
        checkSoloCellDies(4, 4);
    }

    public void hasNeighboursShouldDie(int x, int y, int a, int b) {
        GameState initialState = GameStateUtil.initialise(5, Arrays.asList(new Cell(x, y), new Cell(a, b)));
        GameState nextState = initialState.advance();
        assertEquals(0, nextState.aliveCount());
    }

    @Test
    public void cellsWithOneNeighbourShouldDie() {
        hasNeighboursShouldDie(3, 3, 3, 4);
    }

    public void hasFourNeighboursShouldDie(int x, int y, int a, int b, int c, int d, int f, int g, int h, int i) {
        GameState initialState = GameStateUtil.initialise(100, Arrays.asList(new Cell(x, y), new Cell(a, b),
                new Cell(c, d), new Cell(f, g), new Cell(h, i)));
        GameState nextState = initialState.advance();
        assertFalse(nextState.isAlive(x, y));
    }

    @Test
    public void cellsWithFourNeighboursShouldDie() {
        hasFourNeighboursShouldDie(3, 3, 2, 3, 4, 3, 4, 4, 2, 2);
    }

    public void threeNeighboursShouldLive(int x, int y, int a, int b, int c, int d, int e, int f) {
        GameState initialState = GameStateUtil.initialise(100, Arrays.asList(new Cell(x, y), new Cell(a, b),
                new Cell(c, d), new Cell(e, f)));
        GameState nextState = initialState.advance();
        assertTrue(nextState.isAlive(x, y));
    }

    @Test
    public void cellWithThreeNeighboursStayAlive() {
        threeNeighboursShouldLive(3, 3, 2, 2, 4, 2, 3, 4);
    }

    @Test
    public void gridPlusTwo() {
        GameState initialState = GameStateUtil.initialise(5, Arrays.asList(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0), new Cell(1, 1)));
        assertEquals(5, initialState.getGridSize());
        GameState newState = initialState.advance();
        assertEquals(7, newState.getGridSize());
        assertTrue(newState.isAlive(2, 2));
        assertFalse(newState.isAlive(0, 0));
    }

    @Test
    public void noInteractions() {
        GameState initialState = GameStateUtil.initialise(5, Arrays.asList());
        GameState newState = initialState.advance();
        assertEquals(0, newState.aliveCount());
    }

    @Test
    public void testNeighbourhood() {
        GameState initialState = GameStateUtil.initialise(5, Arrays.asList(new Cell(0, 0), new Cell(0, 1), new Cell(1, 0), new Cell(1, 1)));
        assertEquals(3, initialState.neighbourhood(0, 1));
    }

}