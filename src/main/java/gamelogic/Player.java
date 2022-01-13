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

    //calculates hitting a ship (ship is HIT or SUNK)
    public Ship.ShipHitStatus hitShip(int[] rowCol) {
        Ship.ShipHitStatus shipHitStatus = Ship.ShipHitStatus.HIT;
        for(Ship ship : ships) {
            if(ship.containsCoordinate(rowCol)) {
                ship.hitCoordinate(rowCol);
                if(ship.isSunk())
                    shipHitStatus = Ship.ShipHitStatus.SUNK;
                break;
            }
        }
        return shipHitStatus;
    }

    //player has at least one ship left alive
    public boolean isAlive() {
        int shipCount = 0;
        for(Ship ship : ships) {
            if(ship.isSunk())
                shipCount++;
        }
        if(shipCount == ships.size())
            return false;
        return true;
    }
}
