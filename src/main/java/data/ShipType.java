package main.java.data;

public enum ShipType {
    CARRIER(5),
    BATTLESHIP(4),
    CRUISER(3),
    DESTROYER(2),
    SUBMARINE(1);

    final int numberOfSquares;

    ShipType(int numberOfSquares) {
        this.numberOfSquares = numberOfSquares;
    }
}
