package main.java.display;

import main.java.data.Board;
import main.java.data.Square;
import main.java.data.SquareStatus;

public class Display {
    public void printBoard(Board board) {
        Square[][] ocean = board.getOcean();
        for (int row = 0; row < ocean.length; row++) {
            for (int col = 0; col < ocean[0].length; col++) {
                char c = '.';
                if(ocean[row][col].getStatus() == SquareStatus.SHIP) {
                    c = '#'; //ASCII code
                } else if(ocean[row][col].getStatus() == SquareStatus.HIT) {
                    c = 'X';
                } else if(ocean[row][col].getStatus() == SquareStatus.MISSED) {
                    c = 'O';
                }
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
