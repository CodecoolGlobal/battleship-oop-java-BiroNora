package main.java.data;

import java.util.List;

public class Board {
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
    public boolean isShipPlacementPossible(Ship ship) {
        List<Square> squares = ship.getSquares();
        for (Square square : squares) {
            if (!isSquareEmpty(new int[]{square.getY(), square.getX()}))
                return false;
            if (!isSquareNeighboursEmpty(new int[]{square.getY(), square.getX()}))
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

    //get the status of a square
    public SquareStatus getSquareStatus(int[] rowCol) {

        int row = rowCol[0];
        int col = rowCol[1];

        return (0 <= row && row < ocean.length &&
                0 <= col && col < ocean[0].length ?
                ocean[row][col].getStatus() : null);
    }

    public void setSquareStatus(int[] rowCol, SquareStatus newStatus) {

        if(getSquareStatus(rowCol) == null)
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
}
