package gol;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This allows the game to run on the command line. Users are given 3 starting options:
 * The blinker - uses the Underpopulation and Creation of Life rules
 * The toad - uses the Creation of Life, Underpopulation, Overcrowding Survival rules
 * The glider - uses the Underpopulation, Survival and Creation of Life rules. It also causes the grid to expand.
 */

public class Main {

    public static void main(String[] args) {

        int choiceInt;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Game of Life\n" +
                "Choose a starting game:\n" +
                "--------------------------\n" +
                "1 - The Blinker.\n" +
                "2 - The Toad.\n" +
                "3 - The Glider.\n" +
                "Enter choice > ");
        choiceInt = input.nextInt();

        if (choiceInt < 1 || choiceInt > 3) {
            System.out.println("Invalid entry, try again");
        }

        try {
            switch (choiceInt) {
                case 1:
                    updateGameState(input, GameStateUtil.initialise(7, Arrays.asList(new Cell(3, 2), new Cell(3, 3), new Cell(3, 4))));
                    break;

                case 2:
                    updateGameState(input, GameStateUtil.initialise(7, Arrays.asList(new Cell(3, 2), new Cell(4, 2), new Cell(5, 2),
                            new Cell(2, 3), new Cell(3, 3), new Cell(4, 3))));
                    break;

                case 3:
                    updateGameState(input, GameStateUtil.initialise(7, Arrays.asList(new Cell(2, 1), new Cell(2, 2), new Cell(2, 3),
                            new Cell(1, 3), new Cell(0, 2))));
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong, please try again.");
        }
    }

    private static void updateGameState(Scanner input, GameState newGame) {
        System.out.println("Here is your starting grid.");
        System.out.println(newGame.toString());

        while (true) {
            System.out.println("Press 1 to view next state or any key to exit:");
            int choiceOne = input.nextInt();

            if (choiceOne == 1) {
                newGame = newGame.advance();
                System.out.println(newGame.toString());
            } else {
                break;
            }
        }
    }
}