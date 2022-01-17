package main.java.gamelogic;

import main.java.data.BoardFactory;
import main.java.data.HighScore;
import main.java.display.Display;
import main.java.input.Input;

//Stores classes of the game
public class Battleship {
    public enum MenuSelection {NEW_GAME, HIGH_SCORE, QUIT}

    private Display display = new Display();
    private Input input = new Input();
    private BoardFactory boardFactory = new BoardFactory();
    private Game game = new Game();
    private HighScore highScore = new HighScore();

    public void mainMenu() {
        MenuSelection menuSelection = MenuSelection.NEW_GAME;
        do {
            menuSelection = input.getMainMenuInput(display);
            if (menuSelection == MenuSelection.NEW_GAME) {
                game.newGame(display, input, boardFactory, highScore);
            } else if (menuSelection == MenuSelection.HIGH_SCORE) {
                display.drawScoreBoard(highScore.getScoreBoard());
            }
        } while (menuSelection != MenuSelection.QUIT);
    }
}
