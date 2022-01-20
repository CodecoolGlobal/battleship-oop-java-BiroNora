package main.java.data;

import main.java.display.Display;
import main.java.gamelogic.Player;
import main.java.input.Input;
import main.java.utility.RandomGenerator;
import main.java.utility.Sleep;

import java.util.List;

public class BoardFactory {
    public Board randomPlacement(int rows, int cols, List<ShipType> shipTypeList, Player player, boolean checkForAdjacency) {

        Board board;

        //number of attempts made to generate ships
        int tryCounter = 0;

        do {
            tryCounter = 0;
            board = generateEmptyBoard(rows, cols);
            int rowMax = board.getHeight();
            int colMax = board.getWidth();
            player.clearShipList();

            for (int i = 0; i < shipTypeList.size(); i++) {
                Ship ship = null;
                do {
                    if (100 < tryCounter)
                        break;
                    int x = RandomGenerator.getRandomNumber(0, colMax);
                    int y = RandomGenerator.getRandomNumber(0, rowMax);
                    Direction direction = Direction.getDirectionFromNumber(
                            RandomGenerator.getRandomNumber(1, 4 + 1));
                    ship = new Ship(shipTypeList.get(i), new int[]{y, x}, direction);
                    tryCounter++;
                } while (!board.isShipPlacementPossible(ship, checkForAdjacency));

                if (100 < tryCounter)
                    break;

                board.placeShip(ship);
                player.addShip(ship);
            }
        } while (100 < tryCounter);


        return board;
    }

    public Board manualPlacement(int rows, int cols, List<ShipType> shipTypeList, Player player, boolean checkForAdjacency, Display display, Input input) {
        Board board = generateEmptyBoard(rows, cols);

        for (int i = 0; i < shipTypeList.size(); i++) {
            boolean isOk = false;
            Ship ship = null;
            do {
                display.printBoard(board, true);
                display.printNextShipIs(player.getName(), shipTypeList.get(i));
                int[] rowCol = input.getCoordinateFromUser(display, board);
                Direction direction = input.getDirectionFromUser(display);
                ship = new Ship(shipTypeList.get(i), rowCol, direction);
                if (!board.isShipPlacementPossible(ship, checkForAdjacency)) {
                    display.printInvalidShipPlacement();
                    Sleep.goToSleep(1000);
                } else {
                    board.placeShip(ship);
                    display.printBoard(board, true);
                    isOk = input.getIsShipPlacementOk(display);
                    if (!isOk)
                        board.removeShip(ship);
                }
            } while (!isOk);

            player.addShip(ship);
        }
        return board;
    }

    //10x10 squares
    private Board generateEmptyBoard(int row, int col) {

        Square[][] ocean = new Square[row][col];

        for (int i = 0; i < ocean.length; i++) {
            for (int j = 0; j < ocean[i].length; j++) {
                ocean[i][j] = new Square(j, i, SquareStatus.EMPTY);
            }
        }

        return new Board(ocean);
    }
}
