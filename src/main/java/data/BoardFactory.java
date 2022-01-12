package main.java.data;

import main.java.gamelogic.Player;
import main.java.utility.RandomGenerator;

import java.util.List;

public class BoardFactory {
    //12 ships:
    //1 carrier(5)
    //2 battleships(4)
    //3 cruisers(3)
    //4 destroyers(2)
    //2 submarines(1)
    public Board randomPlacement(int rows, int cols, List<ShipType> shipTypeList, Player player) {

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
                } while (!board.isShipPlacementPossible(ship));

                if (100 < tryCounter)
                    break;

                board.placeShip(ship);
                player.addShip(ship);
            }
        } while (100 < tryCounter);


        return board;
    }

    public Board manualPlacement(int row, int col, List<ShipType> shipTypeList) {
        return new Board(null);
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
