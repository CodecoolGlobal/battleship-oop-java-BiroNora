package main.java.display;

import main.java.data.Board;
import main.java.data.Square;
import main.java.data.SquareStatus;

public class Display {

    public void printSystemErrorMessage(String error) { System.out.println(error); }

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


    public void printBoards(Board currentBoard, Board opponentBoard) {
        printBoard(currentBoard, true);
        printBoard(opponentBoard, false);
    }

    public void printSelectMove(String playerName) {
        System.out.println(playerName + " is playing. Select a move:");
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

    public void printShotHit() {
        System.out.println("Shot hit!");
    }

    public void printShipSunk() {
        System.out.println("Ship sunk!");
    }

    public void printGetCoordinateFromPlayer() {
        System.out.println("\nType in the coordinates of your ship (e.g. A1,A13) or type in QUIT to give up: ");
    }

    public void printGameOver(String winner) {
        System.out.println("The game is over");
        System.out.println(winner + " won.");
    }

    public void printFromPlayerNoInput() {
        System.out.println("You didn't type anything !");
    }

    public void printPlayerTypeMenu() {

        System.out.println("\n\tChoose from these choices");
        System.out.println("\t-------------------------");
        System.out.println("\t1 - Player versus Player");
        System.out.println("\t2 - Player versus AI");
        System.out.println("\t3 - AI versus AI\n");
    }

    public void printShipFormMenu() {

        System.out.println("\n\tChoose from these choices");
        System.out.println("\t-------------------------");
        System.out.println("\t1 - Line-shaped ships only");
        System.out.println("\t2 - Mixed-shaped ships\n");
    }

    public void printShipAdjacencyMenu() {

        System.out.println("\n\tChoose from these choices");
        System.out.println("\t-------------------------");
        System.out.println("\t1 - The ships can touch each other");
        System.out.println("\t2 - Do not touch each other the ships\n");
    }

    public void printSelectNumber1to3() {
        System.out.println("Please, select number 1 or 2 or 3");
    }

    public void printSelectNumber1to2() {
        System.out.println("Please, select number 1 or 2");
    }

    public void printCommandPrompt() {
        System.out.print("Your choice >> ");
    }

    public void printCoordinatePrompt() { System.out.print("Your coordinate >> "); }

    public void printFirstCharOnlyLetter() { System.out.println("the first char can only be a letter. (A-Z)"); }

    public void printOnlyDigits() { System.out.println("after the first char, there can only be numbers and max on 2 length.(0-9)");}

    public String convertFromRowColToString(int[] rowCol) {

        int x=rowCol[0];
        char c= (char)(x+17+'0');

        return String.valueOf(c)+String.valueOf(rowCol[1]);
    }

}
