package main.java.gamelogic;

import main.java.data.Board;
import main.java.data.Ship;
import main.java.data.Square;
import main.java.data.SquareStatus;
import main.java.display.Display;
import main.java.input.Input;
import main.java.utility.RandomGenerator;
import main.java.utility.Sleep;

import java.util.ArrayList;
import java.util.List;

public class ComputerPlayer extends Player {
    private final List<Square> targetSquares = new ArrayList<>();
    private Board.NeighbourPosition neighboursToCheckFirst = Board.NeighbourPosition.UNSPECIFIED;

    public ComputerPlayer(String name) {
        super(name);
    }

    @Override
    public int[] selectMove(Display display, Input input, Board opponentBoard, Player opponent, String currentPlayerName) {
        int[] rowCol = null;
        boolean isOk;
        display.printSelectMove(currentPlayerName + " (Computer)");
        Sleep.goToSleep(2000);
        do {
            isOk = true;
            rowCol = calculateShot(opponentBoard, opponent);
            SquareStatus squareStatus = opponentBoard.getSquareStatus(rowCol);
            if (squareStatus == null || squareStatus == SquareStatus.HIT || squareStatus == SquareStatus.MISSED) {
                isOk = false;
            } else if (squareStatus == SquareStatus.SHIP) {
                targetSquares.add(opponentBoard.getSquare(rowCol));
            }
        } while (!isOk);
        return rowCol;
    }

    public int[] calculateShot(Board opponentBoard, Player opponent) {
        int[] rowCol;
        if (targetSquares.isEmpty()) {
            rowCol = randomShot(opponentBoard);
        } else {
            rowCol = targetShip(opponentBoard, opponent);
        }

        return rowCol;
    }

    private int[] targetShip(Board opponentBoard, Player opponent) {
        for (int i = targetSquares.size() - 1; 0 <= i; i--) {
            int[] rowCol = targetSquares.get(i).getRowCol();
            if (opponent.getShipHitStatus(rowCol, false) == Ship.ShipHitStatus.SUNK) {
                targetSquares.clear();
                neighboursToCheckFirst = Board.NeighbourPosition.UNSPECIFIED;
                break;
            }

            List<Square> allNeighbours = opponentBoard.getNeighbouringNonHitSquares(rowCol, Board.NeighbourPosition.UNSPECIFIED);

            if (allNeighbours.isEmpty()) {
                targetSquares.remove(i);
                continue;
            }

            List<Square> horizontalNeighbours = opponentBoard.getNeighbouringNonHitSquares(rowCol, Board.NeighbourPosition.HORIZONTAL);
            List<Square> verticalNeighbours = opponentBoard.getNeighbouringNonHitSquares(rowCol, Board.NeighbourPosition.VERTICAL);

            if (neighboursToCheckFirst == Board.NeighbourPosition.UNSPECIFIED) {
                neighboursToCheckFirst = Board.NeighbourPosition.values()[RandomGenerator.getRandomNumber(0, 2)];
            }

            if (neighboursToCheckFirst == Board.NeighbourPosition.HORIZONTAL) {
                if (!horizontalNeighbours.isEmpty())
                    return horizontalNeighbours.get(RandomGenerator.getRandomNumber(0, horizontalNeighbours.size())).getRowCol();
                else
                    neighboursToCheckFirst = Board.NeighbourPosition.VERTICAL;
            }

            if (neighboursToCheckFirst == Board.NeighbourPosition.VERTICAL) {
                if (!verticalNeighbours.isEmpty())
                    return verticalNeighbours.get(RandomGenerator.getRandomNumber(0, verticalNeighbours.size())).getRowCol();
                else
                    neighboursToCheckFirst = Board.NeighbourPosition.HORIZONTAL;
            }

            return allNeighbours.get(RandomGenerator.getRandomNumber(0, allNeighbours.size())).getRowCol();
        }

        return randomShot(opponentBoard);
    }

    private int[] randomShot(Board opponentBoard) {

        int[] rowCol = new int[2];

        rowCol[0] = RandomGenerator.getRandomNumber(0, opponentBoard.getWidth());
        rowCol[1] = RandomGenerator.getRandomNumber(0, opponentBoard.getHeight());


        return rowCol;
    }

    @Override
    public boolean isHuman() {
        return false;
    }
}


