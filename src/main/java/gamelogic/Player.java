package main.java.gamelogic;

import main.java.data.Board;
import main.java.data.Ship;
import main.java.data.SquareStatus;
import main.java.display.Display;
import main.java.input.Input;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Ship> ships = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //checking ship hit status (also hits ship if allowed)
    public Ship.ShipHitStatus getShipHitStatus(int[] rowCol, boolean attemptToHit) {
        Ship.ShipHitStatus shipHitStatus = Ship.ShipHitStatus.HIT;
        for (Ship ship : ships) {
            if (ship.isShipOnCoordinate(rowCol, attemptToHit)) {
                if (ship.isSunk())
                    shipHitStatus = Ship.ShipHitStatus.SUNK;
                break;
            }
        }
        return shipHitStatus;
    }

    //player has at least one ship left alive
    public boolean isAlive() {
        int shipCount = 0;
        for (Ship ship : ships) {
            if (ship.isSunk())
                shipCount++;
        }
        if (shipCount == ships.size())
            return false;
        return true;
    }

    //select valid place to shoot
    public int[] selectMove(Display display, Input input, Board opponentBoard, Player opponent, String currentPlayerName) {
        int[] rowCol = null;
        boolean isOk;
        display.printSelectMove(currentPlayerName);
        do {
            isOk = true;
            rowCol = input.getCoordinateFromUser(display, opponentBoard);
            SquareStatus squareStatus = opponentBoard.getSquareStatus(rowCol);
            if (squareStatus == null || squareStatus == SquareStatus.HIT || squareStatus == SquareStatus.MISSED) {
                isOk = false;
                if (squareStatus == null)
                    display.printWrongCoordinateGiven();
                else
                    display.printAlreadyShotThere();
            }
        } while (!isOk);

        return rowCol;
    }

    //excecutes a shot (shot can MISS, HIT or SINK a ship)
    public void excecuteMove(int[] rowCol, Display display, Board currentBoard, Board opponentBoard, Player opponent) {
        SquareStatus squareStatus = opponentBoard.getSquareStatus(rowCol);
        SquareStatus newSquareStatus = SquareStatus.MISSED;
        Ship.ShipHitStatus shipHitStatus = Ship.ShipHitStatus.HIT;

        if (squareStatus == SquareStatus.SHIP) {
            newSquareStatus = SquareStatus.HIT;
            shipHitStatus = opponent.getShipHitStatus(rowCol, true);
        }

        opponentBoard.setSquareStatus(rowCol, newSquareStatus);

        display.printBoards(currentBoard, opponentBoard);

        display.printCoordinate(rowCol);

        if (squareStatus == SquareStatus.EMPTY) {
            display.printShotMissed();
        } else if (squareStatus == SquareStatus.SHIP && shipHitStatus != Ship.ShipHitStatus.SUNK) {
            display.printShotHit();
        } else if (shipHitStatus == Ship.ShipHitStatus.SUNK) {
            display.printShipSunk();
        }
    }

    public boolean isHuman() {
        return true;
    }
}
