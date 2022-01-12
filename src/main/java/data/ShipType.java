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

    public static List<ShipType> getDefaultShipSet() {
        //12 ships:
        //1 carrier(5)
        //2 battleships(4)
        //3 cruisers(3)
        //4 destroyers(2)
        //2 submarines(1)
        List<ShipType> shipTypeList = new ArrayList<>();
        shipTypeList.add(CARRIER);
        shipTypeList.add(BATTLESHIP);
        shipTypeList.add(BATTLESHIP);
        shipTypeList.add(CRUISER);
        shipTypeList.add(CRUISER);
        shipTypeList.add(CRUISER);
        shipTypeList.add(DESTROYER);
        shipTypeList.add(DESTROYER);
        shipTypeList.add(DESTROYER);
        shipTypeList.add(DESTROYER);
        shipTypeList.add(SUBMARINE);
        shipTypeList.add(SUBMARINE);
        return shipTypeList;
    }
}
