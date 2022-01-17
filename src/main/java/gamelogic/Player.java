package main.java.gamelogic;

import main.java.data.Board;
import main.java.data.Ship;
import main.java.data.SquareStatus;
import main.java.display.Display;
import main.java.input.Input;

import java.util.ArrayList;
import java.util.List;

//12 ships:
//1 carrier(5)
//2 battleships(4)
//3 cruisers(3)
//4 destroyers(2)
//2 submarines(1)
public class Player {
    private String name;
    private List<Ship> ships = new ArrayList<>();
    private int score = 0;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        this.score++;
    }

    //calculates hitting a ship (ship is HIT or SUNK)
    public Ship.ShipHitStatus hitShip(int[] rowCol) {
        Ship.ShipHitStatus shipHitStatus = Ship.ShipHitStatus.HIT;
        for(Ship ship : ships) {
            if(ship.attemptToHitCoordinate(rowCol)) {
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

    //select valid place to shoot
    public int[] selectMove(Display display, Input input, Board opponentBoard, String currentPlayerName) {
        int[] rowCol = null;
        boolean isOk = true;
        do {
            display.printSelectMove(currentPlayerName);
            rowCol = input.getCoordinateFromUser(display,opponentBoard);
            SquareStatus squareStatus = opponentBoard.getSquareStatus(rowCol);
            if(squareStatus == null || squareStatus == SquareStatus.HIT || squareStatus == SquareStatus.MISSED) {
                isOk = false;
                if(squareStatus == null)
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

        if(squareStatus == SquareStatus.SHIP) {
            newSquareStatus = SquareStatus.HIT;
            shipHitStatus = opponent.hitShip(rowCol);
            incrementScore();
        }

        opponentBoard.setSquareStatus(rowCol, newSquareStatus);

        display.printBoards(currentBoard, opponentBoard);

        if(squareStatus == SquareStatus.EMPTY) {
            display.printShotMissed();
        } else if(squareStatus == SquareStatus.SHIP && shipHitStatus != Ship.ShipHitStatus.SUNK) {
            display.printShotHit();
        }else if(shipHitStatus == Ship.ShipHitStatus.SUNK) {
            display.printShipSunk();
        }
    }
}
