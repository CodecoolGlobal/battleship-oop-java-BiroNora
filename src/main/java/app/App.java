package main.java.app;

import main.java.data.Board;
import main.java.data.BoardFactory;
import main.java.data.ShipType;
import main.java.display.Display;
import main.java.gamelogic.Player;

public class App {
    public static void main(String[] args) {
        BoardFactory boardFactory = new BoardFactory();
        Player player = new Player();
        Board board = boardFactory.randomPlacement(10, 10, ShipType.getDefaultShipSet(), player);
        Display display = new Display();
        display.printBoard(board, true);
        display.printBoard(board, false);
    }
}
