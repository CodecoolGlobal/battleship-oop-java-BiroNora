package main.java.data;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ShipType> getLineShipSet() {
        //5 ships:
        //1 carrier(5)
        //1 battleships(4)
        //2 cruisers(3)
        //1 destroyers(2)
        List<ShipType> shipTypeList = new ArrayList<>();
        shipTypeList.add(CARRIER);
        shipTypeList.add(BATTLESHIP);
        shipTypeList.add(CRUISER);
        shipTypeList.add(CRUISER);
        shipTypeList.add(DESTROYER);
        return shipTypeList;
    }

    public static List<ShipType> getMixedShipSet() {
        List<ShipType> shipTypeList = new ArrayList<>();
        //TODO
        //5 ships:
        return shipTypeList;
    }

    public int getNumberOfSquares() {
        return numberOfSquares;
    }
}
