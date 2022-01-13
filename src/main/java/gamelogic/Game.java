package main.java.gamelogic;

import main.java.data.Board;
import main.java.data.BoardFactory;
import main.java.data.ShipType;
import main.java.display.Display;
import main.java.input.Input;

public class Game {
    private Player player1;
    private Player player2;
    private Board player1Board;
    private Board player2Board;

    //The Game class has a loop in which players make moves. (érvényes lövés, nem lövünk ugyan oda)
    //The Game class has a logic which determines the flow of round.
    // (kiértékeljük a lövést (talált, süllyedt, nem talált))
    //The Game class has a condition on which game ends.
    //The Game class provides different game modes which use different round flows.

    public void NewGame(Display display, Input input, BoardFactory boardFactory) {
        //display.printNewGameMenu();
        //int gameType = input.selectGameType(display); //ai vs player, player vs player
        //if(gameType == 1) { //player vs player
        //    player1 = new Player();
        //    player2 = new Player();
        //} else if (gameType == 2) { //player vs ai
        //    player1 = new Player();
        //    player2 = new ComputerPlayer();
        //}
        //Board boardP1 = boardFactory.randomPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
        //        ShipType.getDefaultShipSet(), player1);
        //Board boardP2 = boardFactory.randomPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
        //        ShipType.getDefaultShipSet(), player2);
    }
}
