package main.java.display;

import main.java.data.Board;
import main.java.data.ShipType;
import main.java.data.Square;
import main.java.data.SquareStatus;

public class Display {

    public void printSystemErrorMessage(String error) {
        System.out.println(error);
    }

    //isPlayer: if true, ship parts will be visible on the board
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
        System.out.println("Ship hit!");
    }

    public void printShipSunk() {
        System.out.println("Ship hit and sunk!");
    }

    public void printGetCoordinateFromPlayer(Board board) {
        int[] arrMin = new int[]{0, 1};
        int[] arrMax = new int[]{board.getWidth() - 1, board.getHeight()};
        System.out.println("\nType in the target coordinates (e.g. " +
                convertFromRowColToString(arrMin) + " - " + convertFromRowColToString(arrMax) + ") or type in QUIT to exit game: ");
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

    public void printSelectNumber1toN(int n) {
        System.out.println("Please, select number (1 - " + n + ")");
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

    public void printGoodbye() {
        System.out.println("Goodbye!");
    }

    public String convertFromRowColToString(int[] rowCol) {

        int x = rowCol[0];
        char c = (char) (x + 17 + '0');

        return String.valueOf(c) + String.valueOf(rowCol[1]);
    }

    public void printScoreBoard(String[][] scoreBoard) {
        int longestName = 0;
        int shortestName = Integer.MAX_VALUE;
        for (int row = 0; row < scoreBoard.length; row++) {
            if(longestName < scoreBoard[row][0].length())
                longestName = scoreBoard[row][0].length();
            if(scoreBoard[row][0].length() < shortestName)
                shortestName = scoreBoard[row][0].length();
        }

        String spacesMax = generateSpaces(longestName - shortestName);
        System.out.println("***HIGH SCORES:***");
        for (int row = 0; row < scoreBoard.length; row++) {
            String spaces = generateSpaces(longestName - scoreBoard[row][0].length());
            System.out.println(row + 1 + ". " + (row+1 < 10 ? " " : "") + scoreBoard[row][0] + spaces + " - " + spacesMax + scoreBoard[row][1]);
        }
    }

    public String generateSpaces(int length) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < length; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    public void printEnterYourName() {
        System.out.println("Please enter your name:");
    }

    public void printNextShipIs(String playername, ShipType shipType) {
        System.out.println(playername + ", please place the next ship on the board:");
        System.out.println(shipType.toString().toLowerCase() + " (size: " + shipType.getNumberOfSquares() + ")");
    }

    public void printGetDirectionFromPlayer() {
        System.out.println("Please enter a direction for the ship - (N)orth, (E)ast, (S)outh or (W)est:");
    }

    public void printInvalidShipPlacement() {
        System.out.println("Cannot place the ship there!");
    }

    public void printIsShipPlacementOk() {
        System.out.println("Is this placement ok?");
        System.out.println("1 - Yes, we can continue");
        System.out.println("2 - No, I want to place the ship somewhere else");
    }
}
