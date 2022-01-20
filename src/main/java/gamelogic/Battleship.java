package main.java.gamelogic;

import main.java.data.BoardFactory;
import main.java.data.HighScore;
import main.java.data.RuleSet;
import main.java.display.Display;
import main.java.input.Input;

//Stores classes of the game
public class Battleship {
    public enum MenuSelection {NEW_GAME, HIGH_SCORE, OPTIONS, QUIT}

    private Display display = new Display();
    private Input input = new Input();
    private BoardFactory boardFactory = new BoardFactory();
    private Game game = new Game();
    private HighScore highScore = new HighScore();
    private RuleSet ruleSet = new RuleSet();

    public void mainMenu() {
        MenuSelection menuSelection = MenuSelection.NEW_GAME;
        do {
            menuSelection = input.getMainMenuInput(display);

            switch (menuSelection) {
                case NEW_GAME -> {
                    game.newGame(display, input, boardFactory, highScore, ruleSet);
                }
                case HIGH_SCORE -> {
                    display.printScoreBoard(highScore.getScoreBoard());
                }
                case OPTIONS -> {
                    input.getOptionsMenuInput(display, ruleSet);
                }
            }
        } while (menuSelection != MenuSelection.QUIT);
        display.printGoodbye();
    }
}
