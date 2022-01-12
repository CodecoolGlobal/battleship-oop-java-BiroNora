package main.java.gamelogic;

import main.java.data.BoardFactory;
import main.java.display.Display;
import main.java.input.Input;

//Stores classes of the game
public class Battleship {
    private Display display = new Display();
    private Input input = new Input();
    private BoardFactory boardFactory = new BoardFactory();
    private Game game = new Game();
}
