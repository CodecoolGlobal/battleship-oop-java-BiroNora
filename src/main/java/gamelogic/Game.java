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
        //RuleSet.PlayerType playerType = input.selectPlayerType(display);  // PLAYER_VS_PLAYER, PLAYER_VS_AI, AI_VS_AI
        //RuleSet.ShipForm shipForm = input.selectShipForm(display);  // LINE_SHIPS, MIXED_SHIPS
        //RuleSet.ShipAdjacency shipAdjacency = input.selectShipAdjacency(display);  // ALLOWED, NOT_ALLOWED
        //RuleSet.ShipPlacement shipPlacement = input.selectShipPlacement(display);  // RANDOM, MANUAL
        //ruleSet = new RuleSet(playerType, shipForm, shipAdjacency, shipPlacement);
        //ruleSet = new RuleSet();

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

        List<ShipType> shipTypeList;
        if (ruleSet.getShipForm() == RuleSet.ShipForm.LINE_SHIPS) {
            shipTypeList = ShipType.getLineShipSet();
        } else {
            shipTypeList = ShipType.getMixedShipSet();
        }

        boolean checkForAdjacency;
        if(ruleSet.getShipAdjacency() == RuleSet.ShipAdjacency.ALLOWED) {
            checkForAdjacency = false;
        } else {
            checkForAdjacency = true;
        }

        if(player1.isHuman() && ruleSet.getShipPlacement() == RuleSet.ShipPlacement.MANUAL) {
            player1Board = boardFactory.manualPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
                    shipTypeList, player1, checkForAdjacency, display, input);
        } else {
            player1Board = boardFactory.randomPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
                    shipTypeList, player1, checkForAdjacency);
        }

        if(player2.isHuman() && ruleSet.getShipPlacement() == RuleSet.ShipPlacement.MANUAL) {
            player2Board = boardFactory.manualPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
                    ShipType.getLineShipSet(), player2, checkForAdjacency, display, input);
        } else {
            player2Board = boardFactory.randomPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
                    ShipType.getLineShipSet(), player2, checkForAdjacency);
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
        display.printBoards(currentBoard, opponentBoard);
        int[] rowCol = currentPlayer.selectMove(display, input, opponentBoard, currentPlayer.getName());
        currentPlayer.excecuteMove(rowCol, display, currentBoard, opponentBoard, getOpponentPlayer());

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
