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

    public void printGetCoordinateFromPlayer(Board board) {
        int[] arrMin = new int[]{0, 1};
        int[] arrMax = new int[]{board.getWidth() - 1, board.getHeight()};
        System.out.println("\nType in the coordinates of your ship (e.g. " +
                convertFromRowColToString(arrMin) + " - " + convertFromRowColToString(arrMax) + " ) or type in QUIT to give up: ");
    }

    public void printGameOver(String winner) {
        System.out.println("Game over.");
        System.out.println(winner + " won.");
    }

    public void printFromPlayerNoInput() {
        System.out.println("You didn't type anything !");
    }

    public void printMenu(String mainTitle, String menuTitle, String... menuItems) {

        String tabSign = "\t";
        String cRet = "\n";
        String separatorSign = "-";

        System.out.print(!mainTitle.isEmpty() ? cRet + tabSign + mainTitle + cRet : "");

        System.out.println(cRet + tabSign + menuTitle);
        System.out.println(tabSign + separatorSign.repeat(menuTitle.length()));

        for (String menuItem : menuItems) {
            System.out.println(tabSign + menuItem);
        }
        System.out.println();
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

    public void printCoordinatePrompt() {
        System.out.print("Your coordinate >> ");
    }

    public void printFirstCharOnlyLetter() {
        System.out.println("the first char can only be a letter. (A-Z)");
    }

    public void printOnlyDigits() {
        System.out.println("after the first char, there can only be numbers and max on 2 length.(0-9)");
    }

    public void printOutOfRange() {
        System.out.println("The coordinate is outside the game board.");
    }

    public void goodbye() {
        System.out.println("Goodbye!");
    }

    public String convertFromRowColToString(int[] rowCol) {

        int x = rowCol[0];
        char c = (char) (x + 17 + '0');

        return String.valueOf(c) + String.valueOf(rowCol[1]);
    }

    public void drawScoreBoard(String[][] scoreBoard) {
        System.out.println("***HIGH SCORES:***");
        for (int row = 0; row < scoreBoard.length; row++) {
            System.out.println(row + 1 + ". " + scoreBoard[row][0] + " - " + scoreBoard[row][1]);
        }
    }

    public void printEnterYourName() {
        System.out.println("Please enter your name:");
    }
}
