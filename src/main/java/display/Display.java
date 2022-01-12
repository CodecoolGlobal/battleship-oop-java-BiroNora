package main.java.display;

import main.java.data.Board;
import main.java.data.Square;
import main.java.data.SquareStatus;

public class Display {
    //isPlayer == should draw player ships or opponent ships
    public void printBoard(Board board, boolean isPlayer) {
        Square[][] ocean = board.getOcean();
        for (int row = -1; row < ocean.length; row++) {
            for (int col = -1; col < ocean[0].length; col++) {
                String square = ".";
                if(row == -1) {
                    square = "" + (-1 < col ? (col + 1) : " ");
                } else if (col == -1) {
                    square = "" + (char)('A' + row);
                } else {
                    if(ocean[row][col].getStatus() == SquareStatus.SHIP && isPlayer) {
                        square = "#";
                    } else if(ocean[row][col].getStatus() == SquareStatus.HIT) {
                        square = "X";
                    } else if(ocean[row][col].getStatus() == SquareStatus.MISSED) {
                        square = "O";
                    }
                }
                System.out.print(square + " ");
            }
            System.out.println();
        }
    }
}
