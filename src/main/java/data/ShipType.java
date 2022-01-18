package main.java.data;

import main.java.utility.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public enum ShipType {
    CARRIER(5),
    BATTLESHIP(4),
    CRUISER(3),
    DESTROYER(2),
    SUBMARINE(1),
    CARRIER_L_SHAPE(5),
    CARRIER_T_SHAPE(5),
    BATTLESHIP_Z_SHAPE(4),
    CRUISER_L_SHAPE(3);

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
        int randNum = RandomGenerator.getRandomNumber(1, 3 + 1);
        if(randNum == 1)
            shipTypeList.add(CARRIER_L_SHAPE);
        else if(randNum == 2)
            shipTypeList.add(CARRIER_T_SHAPE);
        else
            shipTypeList.add(CARRIER);

        randNum = RandomGenerator.getRandomNumber(1, 3 + 1);
        if(randNum < 3)
            shipTypeList.add(BATTLESHIP_Z_SHAPE);
        else
            shipTypeList.add(BATTLESHIP);

        randNum = RandomGenerator.getRandomNumber(1, 3 + 1);
        if(randNum < 3)
            shipTypeList.add(CRUISER_L_SHAPE);
        else
            shipTypeList.add(CRUISER);

        randNum = RandomGenerator.getRandomNumber(1, 3 + 1);
        if(randNum < 3)
            shipTypeList.add(CRUISER_L_SHAPE);
        else
            shipTypeList.add(CRUISER);

        shipTypeList.add(DESTROYER);

        return shipTypeList;
    }

    public int getNumberOfSquares() {
        return numberOfSquares;
    }
}
