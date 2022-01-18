package main.java.data;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    public enum ShipHitStatus { HIT, SUNK }

    private List<Square> squares = new ArrayList<>();

    public Ship(ShipType shiptype, int[] rowCol, Direction direction) {
        switch(shiptype) {
            case CARRIER, BATTLESHIP, CRUISER, DESTROYER, SUBMARINE ->
                    placeLineShip(rowCol, shiptype.getNumberOfSquares(), direction);
        }
    }

    //generates a line ship
    private void placeLineShip(int[] rowCol, int shipLength, Direction direction) {
        if(direction == Direction.NORTH) {
            for (int row = 0; row < shipLength; row++) {
                squares.add(new Square(rowCol[1], rowCol[0]-row, SquareStatus.SHIP));
            }
        } else if(direction == Direction.EAST) {
            for (int column = 0; column < shipLength; column++) {
                squares.add(new Square(rowCol[1]+column, rowCol[0], SquareStatus.SHIP));
            }
        } else if(direction == Direction.SOUTH) {
            for (int row = 0; row < shipLength; row++) {
                squares.add(new Square(rowCol[1], rowCol[0]+row, SquareStatus.SHIP));
            }
        } else if(direction == Direction.WEST) {
            for (int column = 0; column < shipLength; column++) {
                squares.add(new Square(rowCol[1]-column, rowCol[0], SquareStatus.SHIP));
            }
        }
    }

    public List<Square> getSquares() {
        return squares;
    }

    //hit the ship on coordinate
    public boolean attemptToHitCoordinate(int[] rowCol) {
        for (int i = 0; i < squares.size(); i++) {
            if(rowCol[0] == squares.get(i).getY() && rowCol[1] == squares.get(i).getX()) {
                squares.set(i, new Square(squares.get(i).getX(), squares.get(i).getY(), SquareStatus.HIT));
                return true;
            }
        }
        return false;
    }

    //check if all ship squares are hit
    public boolean isSunk() {
        for (Square square : squares) {
            if(square.getStatus() != SquareStatus.HIT) {
                return false;
            }
        }
        return true;
    }
}
