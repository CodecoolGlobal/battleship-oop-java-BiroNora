package main.java.gamelogic;

import main.java.data.*;
import main.java.display.Display;
import main.java.input.Input;
import main.java.utility.Sleep;

import java.util.List;

public class Game {
    private final int waitingMillisec = 1500;

    private Player player1;
    private Player player2;
    private Board player1Board;
    private Board player2Board;

    private Player currentPlayer;
    private Board currentBoard;

    public void newGame(Display display, Input input, BoardFactory boardFactory, HighScore highScore, RuleSet ruleSet) {
        if (ruleSet.getPlayerType() == RuleSet.PlayerType.PLAYER_VS_PLAYER) {
            player1 = new Player("Player 1");
            player2 = new Player("Player 2");
        } else if (ruleSet.getPlayerType() == RuleSet.PlayerType.PLAYER_VS_AI) {
            player1 = new Player("Player 1");
            player2 = new ComputerPlayer("Player 2");
        } else { //AI_VS_AI
            player1 = new ComputerPlayer("Player 1");
            player2 = new ComputerPlayer("Player 2");
        }

        List<ShipType> shipTypeList1;
        List<ShipType> shipTypeList2;
        if (ruleSet.getShipForm() == RuleSet.ShipForm.LINE_SHIPS) {
            shipTypeList1 = ShipType.getLineShipSet();
            shipTypeList2 = ShipType.getLineShipSet();
        } else {
            shipTypeList1 = ShipType.getMixedShipSet();
            shipTypeList2 = ShipType.getMixedShipSet();
        }

        boolean checkForAdjacency;
        if(ruleSet.getShipAdjacency() == RuleSet.ShipAdjacency.ALLOWED) {
            checkForAdjacency = false;
        } else {
            checkForAdjacency = true;
        }

        if(player1.isHuman() && ruleSet.getShipPlacement() == RuleSet.ShipPlacement.MANUAL) {
            player1Board = boardFactory.manualPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
                    shipTypeList1, player1, checkForAdjacency, display, input);
        } else {
            player1Board = boardFactory.randomPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
                    shipTypeList1, player1, checkForAdjacency);
        }

        if(player2.isHuman() && ruleSet.getShipPlacement() == RuleSet.ShipPlacement.MANUAL) {
            player2Board = boardFactory.manualPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
                    shipTypeList2, player2, checkForAdjacency, display, input);
        } else {
            player2Board = boardFactory.randomPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
                    shipTypeList2, player2, checkForAdjacency);
        }

        playLoop(display, input, highScore);
    }

    private Board getOpponentBoard() {
        if (currentBoard == player1Board)
            return player2Board;
        return player1Board;
    }

    private Player getOpponentPlayer() {
        if (currentPlayer == player1)
            return player2;
        return player1;
    }

    private void switchToNextPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
            currentBoard = player2Board;
        } else {
            currentPlayer = player1;
            currentBoard = player1Board;
        }
    }

    private boolean isGameOver() {
        return !currentPlayer.isAlive();
    }

    //The Game class provides different game modes which use different round flows.

    //logic which determines the flow of round
    public void round(Display display, Input input) {
        Board opponentBoard = getOpponentBoard();
        Player opponent = getOpponentPlayer();
        display.printBoards(currentBoard, opponentBoard);
        int[] rowCol = currentPlayer.selectMove(display, input, opponentBoard, opponent, currentPlayer.getName());
        currentPlayer.excecuteMove(rowCol, display, currentBoard, opponentBoard, opponent);

        Sleep.goToSleep(waitingMillisec);
    }

    public void playLoop(Display display, Input input, HighScore highScore) {
        currentPlayer = player1;
        currentBoard = player1Board;

        do {
            round(display, input);
            switchToNextPlayer();
        } while (!isGameOver());

        switchToNextPlayer();
        display.printGameOver(currentPlayer.getName());

        if (currentPlayer.isHuman()) {
            String name = input.pleaseEnterYourName(display);
            int score = getOpponentBoard().calculateScore();
            highScore.newEntry(name, score);
            highScore.writeToFile();
            display.printScoreBoard(highScore.getScoreBoard());
        } else {
            Sleep.goToSleep(waitingMillisec);
        }
    }
}
