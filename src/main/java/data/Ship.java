package main.java.data;

import java.util.List;

public class Ship {
    private List<Square> squares;

    public Ship(ShipType shiptype, int[] rowCol, Direction direction) {
        switch(shiptype) {
            case CARRIER, BATTLESHIP, CRUISER, DESTROYER, SUBMARINE ->
                    placeLineShip(rowCol, shiptype.numberOfSquares, direction);
        }
    }

    //generates a line ship
    private void placeLineShip(int[] rowCol, int shipLength, Direction direction) {
        if(direction == Direction.NORTH) {
            for (int row = 0; row < shipLength; row++) {
                squares.add(new Square(rowCol[0], rowCol[1]-row, SquareStatus.SHIP));
            }
        } else if(direction == Direction.EAST) {
            for (int column = 0; column < shipLength; column++) {
                squares.add(new Square(rowCol[0]+column, rowCol[1], SquareStatus.SHIP));
            }
        } else if(direction == Direction.SOUTH) {
            for (int row = 0; row < shipLength; row++) {
                squares.add(new Square(rowCol[0], rowCol[1]+row, SquareStatus.SHIP));
            }
        } else if(direction == Direction.WEST) {
            for (int column = 0; column < shipLength; column++) {
                squares.add(new Square(rowCol[0]-column, rowCol[1], SquareStatus.SHIP));
            }
        }
    }

    public List<Square> getSquares() {
        return squares;
    }
}
