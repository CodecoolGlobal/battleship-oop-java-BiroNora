package main.java.gamelogic;

import main.java.data.Board;
import main.java.data.BoardFactory;
import main.java.data.ShipType;
import main.java.data.SquareStatus;
import main.java.display.Display;
import main.java.input.Input;

public class Game {
    private Player player1;
    private Player player2;
    private Board player1Board;
    private Board player2Board;
    private RuleSet ruleSet;

    public void newGame(Display display, Input input, BoardFactory boardFactory) {
        //display.printNewGameMenu();
        //RuleSet.PlayerType playerType = input.selectPlayerType(display);  // PLAYER_VS_PLAYER, PLAYER_VS_AI, AI_VS_AI
        //RuleSet.ShipForm shipForm = input.selectShipForm(display);  // LINE_SHIPS, MIXED_SHIPS
        //RuleSet.ShipAdjacency shipAdjacency = input.selectShipAdjacency(display);  // ALLOWED, NOT_ALLOWED
        //ruleSet = new RuleSet(playerType, shipForm, shipAdjacency);
        //
        //if(playerType == RuleSet.PlayerType.PLAYER_VS_PLAYER) {
        //    player1 = new Player();
        //    player2 = new Player();
        //} else if (playerType == RuleSet.PlayerType.PLAYER_VS_AI) {
        //    player1 = new Player();
        //    player2 = new ComputerPlayer();
        //}
        //
        //Board boardP1 = boardFactory.randomPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
        //        ShipType.getDefaultShipSet(), player1);
        //Board boardP2 = boardFactory.randomPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
        //        ShipType.getDefaultShipSet(), player2);
    }

    //The Game class has a loop in which players make moves. (érvényes lövés, nem lövünk ugyan oda)
    //The Game class has a logic which determines the flow of round.
    // (kiértékeljük a lövést (talált, süllyedt, nem talált))
    //The Game class has a condition on which game ends.
    //The Game class provides different game modes which use different round flows.

    //select valid place to shoot
    public int[] selectMove(Display display, Input input, Board opponentBoard) {
        int[] rowCol = null;
        boolean isOk = true;
        do {
            //rowCol = input.selectPlayerMove(display);
            SquareStatus squareStatus = opponentBoard.getSquareStatus(rowCol);
            if(squareStatus == null || squareStatus == SquareStatus.HIT || squareStatus == SquareStatus.MISSED) {
                isOk = false;
                if(squareStatus == null)
                    display.printWrongCoordinateGiven();
                else
                    display.printAlreadyShotThere();
            }
        } while (!isOk);

        return rowCol;
    }

    //excecutes a shot (shot can MISS, HIT or SINK a ship)
    public void excecuteMove(int[] rowCol, Display display, Board opponentBoard) {
        SquareStatus squareStatus = opponentBoard.getSquareStatus(rowCol);
        if(squareStatus == SquareStatus.EMPTY) {
            display.printShotMissed();
        }
    }
}
