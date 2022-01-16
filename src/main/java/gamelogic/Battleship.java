package main.java.gamelogic;

import main.java.data.BoardFactory;
import main.java.display.Display;
import main.java.input.Input;

//Stores classes of the game
public class Battleship {
    public enum MenuSelection { NEW_GAME, HIGH_SCORE, QUIT }

    private Display display = new Display();
    private Input input = new Input();
    private BoardFactory boardFactory = new BoardFactory();
    private Game game = new Game();

    //has a loop in which the program runs.
    //displays the main menu and allows the user to a start new game, display high scores, and exit.

    public void highScore() {
        //TODO
    }

    public void mainMenu() {
        MenuSelection menuSelection = MenuSelection.NEW_GAME;
        //menuSelection = input.getMainMenuInput(display);
        if(menuSelection == MenuSelection.NEW_GAME) {
            game.newGame(display, input, boardFactory);
        } else if(menuSelection == MenuSelection.HIGH_SCORE) {
            highScore();
        }
    }
}
