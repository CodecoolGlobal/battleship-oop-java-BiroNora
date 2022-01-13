package main.java.display;

import main.java.data.Board;
import main.java.data.Square;
import main.java.data.SquareStatus;

public class Display {

    public void printSystemErrorMessage(String error) {

        System.out.println(error);

    }


    //isPlayer == should draw player ships or opponent ships
    public void printBoard(Board board, boolean isPlayer) {
        Square[][] ocean = board.getOcean();
        for (int row = -1; row < ocean.length; row++) {
            for (int col = -1; col < ocean[0].length; col++) {
                String square = ".";
                if (row == -1) {
                    square = "" + (-1 < col ? (col + 1) : " ");
                } else if (col == -1) {
                    square = "" + (char) ('A' + row);
                } else {
                    if (ocean[row][col].getStatus() == SquareStatus.SHIP && isPlayer) {
                        square = "#";
                    } else if (ocean[row][col].getStatus() == SquareStatus.HIT) {
                        square = "X";
                    } else if (ocean[row][col].getStatus() == SquareStatus.MISSED) {
                        square = "O";
                    }
                }
                System.out.print(square + " ");
            }
            System.out.println();
        }

    }


    public void printWrongCoordinateGiven() {
        System.out.println("The coordinates given are wrong. Try again.");
    }

    public void printAlreadyShotThere() {
        System.out.println("You have already shot there. Try a different coordinate.");
    }

    public void printShotMissed() {
        System.out.println("Shot missed.");
    }

    public void printGetCoordinateFromPlayer() {
        System.out.println("\nType in the coordinates of your ship (e.g. A1,A13) or type in QUIT to give up: ");
    }

    public void printFromPlayerNoInput() {
        System.out.println("You didn't type anything !");
    }

    public void printplayerTypeMenu() {

        System.out.println("\tChoose from these choices");
        System.out.println("\t-------------------------\n");
        System.out.println("\t1 - Player Vs Player");
        System.out.println("\t2 - Player Vs Ai");
        System.out.println("\t3 - Ai Vs Ai\n");
    }

    public void printShipFormMenu() {

        System.out.println("\tChoose from these choices");
        System.out.println("\t-------------------------\n");
        System.out.println("\t1 - Line ship");
        System.out.println("\t2 - Mixed ship\n");
    }

    public void printShipAdjacencyMenu() {

        System.out.println("\tChoose from these choices");
        System.out.println("\t-------------------------\n");
        System.out.println("\t1 - Allowed");
        System.out.println("\t2 - Not allowed\n");
    }

    public void printSelectNumber1to3() {
        System.out.println("Please, select number from 1 to 3");
    }

    public void printSelectNumber1to2() {
        System.out.println("Please, select number from 1 to 2");
    }

    public void printCommandPrompt() {
        System.out.print(">>");
    }
}
