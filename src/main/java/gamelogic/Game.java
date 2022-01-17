package main.java.gamelogic;

import main.java.data.*;
import main.java.display.Display;
import main.java.input.Input;

public class Game {
    private final int waitingMillisec = 1500;

    private Player player1;
    private Player player2;
    private Board player1Board;
    private Board player2Board;
    private RuleSet ruleSet;

    private Player currentPlayer;
    private Board currentBoard;

    public void newGame(Display display, Input input, BoardFactory boardFactory, HighScore highScore) {
        RuleSet.PlayerType playerType = input.selectPlayerType(display);  // PLAYER_VS_PLAYER, PLAYER_VS_AI, AI_VS_AI
        RuleSet.ShipForm shipForm = input.selectShipForm(display);  // LINE_SHIPS, MIXED_SHIPS
        RuleSet.ShipAdjacency shipAdjacency = input.selectShipAdjacency(display);  // ALLOWED, NOT_ALLOWED
        ruleSet = new RuleSet(playerType, shipForm, shipAdjacency);

        if (playerType == RuleSet.PlayerType.PLAYER_VS_PLAYER) {
            player1 = new Player("Player 1");
            player2 = new Player("Player 2");
        } else if (playerType == RuleSet.PlayerType.PLAYER_VS_AI) {
            player1 = new Player("Player 1");
            player2 = new ComputerPlayer("Player 2");
        }

        player1Board = boardFactory.randomPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
                ShipType.getDefaultShipSet(), player1);
        player2Board = boardFactory.randomPlacement(Board.DEFAULT_SIZE, Board.DEFAULT_SIZE,
                ShipType.getDefaultShipSet(), player2);

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

        wait(waitingMillisec);
    }

    private void wait(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            // ...do nothing
        }
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
        wait(waitingMillisec);

        if (currentPlayer.isHuman()) {
            String name = input.pleaseEnterYourName(display);
            int score = getOpponentBoard().calculateScore();
            highScore.newEntry(name, score);
            highScore.writeToFile();
            display.drawScoreBoard(highScore.getScoreBoard());
        }
    }
}
