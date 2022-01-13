package main.java.gamelogic;

import main.java.data.Ship;

import java.util.ArrayList;
import java.util.List;

//12 ships:
//1 carrier(5)
//2 battleships(4)
//3 cruisers(3)
//4 destroyers(2)
//2 submarines(1)
public class Player {
    private List<Ship> ships = new ArrayList<>();

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public void clearShipList() {
        this.ships.clear();
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }
}
