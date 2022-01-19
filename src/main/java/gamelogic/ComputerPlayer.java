package main.java.gamelogic;

import main.java.data.Board;
import main.java.data.Ship;
import main.java.data.SquareStatus;
import main.java.display.Display;
import main.java.input.Input;
import main.java.utility.RandomGenerator;
import main.java.utility.Sleep;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ComputerPlayer extends Player {

    int AILevel = 1;
    boolean targetMode = false;
    private ArrayList<Ship> shipList;
    private ArrayList<int[]> targets;
    private Random rnd; //hasznald a random generator metodust ehelyett
    private int[] tagetShoot = new int[2];

    public ComputerPlayer(String name) {

        super(name);


    }

    @Override
    public int[] selectMove(Display display, Input input, Board opponentBoard, String currentPlayerName) {
        int[] rowCol = null;
        boolean isOk;
        display.printSelectMove(currentPlayerName + " (Computer)");
        Sleep.goToSleep(2000);
        do {
            isOk = true;
            rowCol = shoot(opponentBoard);
            SquareStatus squareStatus = opponentBoard.getSquareStatus(rowCol);
            if (squareStatus == null || squareStatus == SquareStatus.HIT || squareStatus == SquareStatus.MISSED) {
                isOk = false;
            }
        } while (!isOk);
       // System.out.println(Arrays.toString(rowCol));
        return rowCol;
    }


    public int[] shoot(Board opponentBoard) {
        int[] rowCol = new int[2];
        if (this.AILevel == 1) {
            return randomShot(opponentBoard);
       /* } else {
            if (!targetMode) {
                rowCol= randomShot(opponentBoard)
                if (checkShip(rowCol)) {
                    targets.add(rowCol[0] - 1, rowCol[1]);
                    targets.add(rowCol[0], rowCol[1] - 1);
                    targets.add(rowCol[0] + 1, rowCol[1]);
                    targets.add(rowCol[0], rowCol[1] + 1));
                    targetMode = true;

                }

            } else {
                targets.removeIf(rowCol[0] < 0 || rowCol[0] > 9 || rowCol[1] < 0 || rowCol[1] > 9
                        || opponentBoard == 'o');
                targetShoot = targets.get(rnd.nextInt(targets.size()));
                if (checkShip(this.targetShoot)) {
                    if (rowCol[0] == targetShoot[0]) {
              }
            }*/
        }
        return randomShot(opponentBoard);
    }

    private int[] randomShot(Board opponentBoard) {

        int[] rowCol = new int[2];

        rowCol[0] = RandomGenerator.getRandomNumber(0, opponentBoard.getWidth());
        rowCol[1] = RandomGenerator.getRandomNumber(0, opponentBoard.getHeight());


        return rowCol;
    }

    @Override
    public boolean isHuman() {
        return false;
    }
}


