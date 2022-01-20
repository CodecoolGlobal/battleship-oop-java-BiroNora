package main.java.data;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public enum NeighbourPosition {HORIZONTAL, VERTICAL, UNSPECIFIED}

    public static final int DEFAULT_SIZE = 10;

    //10x10 by default
    private Square[][] ocean;

    public Board(Square[][] ocean) {
        this.ocean = ocean;
    }

    //verifies if square is empty
    private boolean isSquareEmpty(int[] rowCol) {

        int row = rowCol[0];
        int col = rowCol[1];

        return 0 <= row && row < ocean.length &&
                0 <= col && col < ocean[0].length &&
                ocean[row][col].getStatus() == SquareStatus.EMPTY;
    }

    //verifies if neighbours of square are empty
    private boolean isSquareNeighboursEmpty(int[] rowCol) {
        for (int row = rowCol[0] - 1; row <= rowCol[0] + 1; row++) {
            for (int col = rowCol[1] - 1; col <= rowCol[1] + 1; col++) {
                if (row == rowCol[0] && col == rowCol[1])
                    continue;
                if (row < 0 || ocean.length - 1 < row)
                    continue;
                if (col < 0 || ocean[0].length - 1 < col)
                    continue;

                if (ocean[row][col].getStatus() != SquareStatus.EMPTY)
                    return false;
            }
        }
        return true;
    }

    //verifies if placement of ship is possible
    public boolean isShipPlacementPossible(Ship ship, boolean checkForAdjacency) {
        List<Square> squares = ship.getSquares();
        for (Square square : squares) {
            if (!isSquareEmpty(new int[]{square.getY(), square.getX()}))
                return false;
            if (checkForAdjacency && !isSquareNeighboursEmpty(new int[]{square.getY(), square.getX()}))
                return false;
        }
        return true;
    }

    //place ship on board
    public void placeShip(Ship ship) {
        List<Square> squares = ship.getSquares();
        for (Square square : squares) {
            ocean[square.getY()][square.getX()].setStatus(SquareStatus.SHIP);
        }
    }

    //remove ship from the board
    public void removeShip(Ship ship) {
        List<Square> squares = ship.getSquares();
        for (Square square : squares) {
            ocean[square.getY()][square.getX()].setStatus(SquareStatus.EMPTY);
        }
    }

    //get the status of a square
    public SquareStatus getSquareStatus(int[] rowCol) {
        Square square = getSquare(rowCol);
        return (square != null ? square.getStatus() : null);
    }

    public Square getSquare(int[] rowCol) {

        int row = rowCol[0];
        int col = rowCol[1];

        return (0 <= row && row < ocean.length &&
                0 <= col && col < ocean[0].length ?
                ocean[row][col] : null);
    }

    public void setSquareStatus(int[] rowCol, SquareStatus newStatus) {

        if (getSquareStatus(rowCol) == null)
            return;

        ocean[rowCol[0]][rowCol[1]].setStatus(newStatus);
    }

    public int getWidth() {
        return ocean[0].length;
    }

    public int getHeight() {
        return ocean.length;
    }

    public Square[][] getOcean() {
        return ocean;
    }

    //the more times a player misses, the fewer points they get
    public int calculateScore() {
        int score = 0;
        for (int row = 0; row < ocean.length; row++) {
            for (int col = 0; col < ocean[0].length; col++) {
                if (ocean[row][col].getStatus() != SquareStatus.MISSED) {
                    score++;
                }
            }
        }
        return score * 100;
    }

    public List<Square> getNeighbouringNonHitSquares(int[] rowCol, NeighbourPosition neigbourPosition) {
        List<Square> neighbours = new ArrayList<>();
        int row = rowCol[0];
        int col = rowCol[1];

        if (0 <= row - 1 && !ocean[row - 1][col].hasBeenShot() && neigbourPosition != NeighbourPosition.HORIZONTAL)
            neighbours.add(ocean[row - 1][col]);
        if (row + 1 < ocean.length && !ocean[row + 1][col].hasBeenShot() && neigbourPosition != NeighbourPosition.HORIZONTAL)
            neighbours.add(ocean[row + 1][col]);
        if (0 <= col - 1 && !ocean[row][col - 1].hasBeenShot() && neigbourPosition != NeighbourPosition.VERTICAL)
            neighbours.add(ocean[row][col - 1]);
        if (col + 1 < ocean[0].length && !ocean[row][col + 1].hasBeenShot() && neigbourPosition != NeighbourPosition.VERTICAL)
            neighbours.add(ocean[row][col + 1]);

        return neighbours;
    }
}
