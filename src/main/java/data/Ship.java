package main.java.data;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    public enum ShipHitStatus { HIT, SUNK }

    private List<Square> squares = new ArrayList<>();

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

    //is coordinate part of ship
    public boolean containsCoordinate(int[] rowCol) {
        for (Square square : squares) {
            if(rowCol[0] == square.getY() && rowCol[1] == square.getX())
                return true;
        }
        return false;
    }

    //is coordinate part of ship
    public void hitCoordinate(int[] rowCol) {
        for (int i = 0; i < squares.size(); i++) {
            if(rowCol[0] == squares.get(i).getY() && rowCol[1] == squares.get(i).getX()) {
                squares.set(i, new Square(squares.get(i).getX(), squares.get(i).getY(), SquareStatus.HIT));
                return;
            }
        }
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
