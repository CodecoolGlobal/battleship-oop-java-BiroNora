package main.java.data;

public class Board {
    //size 10x10
    private Square[][] ocean;

    //verifies if placement of ship is possible
    private boolean isPlacementOk(int[] rowCol) {

        int row = rowCol[0];
        int col = rowCol[1];

        return  0 <= row && row < ocean.length &&
                0 <= col && col < ocean[0].length &&
                ocean[row][col].getStatus() == SquareStatus.EMPTY;
    }


}
