package main.java.input;

import main.java.data.Board;
import main.java.display.Display;
import main.java.gamelogic.Battleship;
import main.java.gamelogic.RuleSet;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    Scanner scanner = new Scanner(System.in);

    public int[] getCoordinateFromUser(Display display, Board board) {

        int[] arrCoordinate = new int[2];

        while (true) {

            display.printGetCoordinateFromPlayer(board);
            display.printCoordinatePrompt();

            if (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                if (command.equals("")) {
                    display.printFromPlayerNoInput();
                    continue;
                }
                if (command.toUpperCase().equals("QUIT")) {
                    display.goodbye();
                    System.exit(0);
                } else {
                    arrCoordinate = convertInputToCoordinate(command, display);

                    if (arrCoordinate == null) continue;
                    if (checkBoardOutOfRange(arrCoordinate,board,display)) continue;
                    break;
                }
            }
        }
        return arrCoordinate == null ? null : arrCoordinate;

    }

    public boolean checkBoardOutOfRange(int[] arrayInt, Board board, Display display) {

        if ((arrayInt[0]<0 || arrayInt[0] > board.getWidth()-1) || (arrayInt[1]<0 || arrayInt[1]>board.getHeight()-1)) {
            display.printOutOfRange();
            return true;
        }

        return false;
    }

    public RuleSet.PlayerType selectPlayerType(Display display) {

        String choice;
        display.printMenu("",
                "What type of game should be",
                "1 - Player versus Player",
                "2 - Player versus AI",
                "3 - AI versus AI");


        while (true) {
            display.printCommandPrompt();
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        return RuleSet.PlayerType.PLAYER_VS_PLAYER;
                    case "2":
                        return RuleSet.PlayerType.PLAYER_VS_AI;
                    case "3":
                        return RuleSet.PlayerType.PLAYER_VS_AI;
                    default:
                        display.printSelectNumber1to3();
                }
            }
        }
    }

    public RuleSet.ShipForm selectShipForm(Display display) {

        String choice;
        display.printMenu("",
                "What shape of the ships should be",
                "1 - Line-shaped ships only",
                "2 - Mixed-shaped ships");


        while (true) {
            display.printCommandPrompt();
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        return RuleSet.ShipForm.LINE_SHIPS;
                    case "2":
                        return RuleSet.ShipForm.MIXED_SHIPS;

                    default:
                        display.printSelectNumber1to2();
                }
            }
        }
    }

    public RuleSet.ShipAdjacency selectShipAdjacency(Display display) {

        String choice;
        display.printMenu("",
                "There should be adjacency between the ships",
                "1 - Be adjacency",
                "2 - Don't be adjacency");


        while (true) {
            display.printCommandPrompt();
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        return RuleSet.ShipAdjacency.ALLOWED;
                    case "2":
                        return RuleSet.ShipAdjacency.NOT_ALLOWED;

                    default:
                        display.printSelectNumber1to2();
                }
            }
        }
    }

    public Battleship.MenuSelection getMainMenuInput(Display display) {

        String choice;
        display.printMenu("******* BATTLESHIP GAME V1.0 *******",
                "Choose from these choices",
                "1 - New Game",
                "2 - High Score",
                "3 - Quit");


        while (true) {
            display.printCommandPrompt();
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        return Battleship.MenuSelection.NEW_GAME;
                    case "2":
                        return Battleship.MenuSelection.HIGH_SCORE;
                    case "3":
                        return Battleship.MenuSelection.QUIT;
                    default:
                        display.printSelectNumber1to3();
                }
            }
        }
    }

    // example a1,A1,a12,A12
    private int[] convertInputToCoordinate(String input, Display display) {

        String coordinate = input.toUpperCase().trim();
        String secondCoordinate = coordinate.substring(1, coordinate.length());

        if (!(checkUpperCharIsAlphabetical(coordinate.charAt(0)))) {
            display.printFirstCharOnlyLetter();
            return null;
        }

        if (!(onlyDigits(secondCoordinate)) || secondCoordinate.isEmpty() || secondCoordinate.length() > 2 || secondCoordinate.equals(0)) {
            display.printOnlyDigits();
            return null;
        }


        int[] aCoordinate = new int[]{(int) coordinate.charAt(0) - 'A', Integer.parseInt(secondCoordinate) - 1};


        return aCoordinate;

    }

    private boolean checkUpperCharIsAlphabetical(char c) {

        if (c >= 'A' && c <= 'Z') {
            return true;
        }

        return false;
    }

    public static boolean onlyDigits(String str) {
        // Regex to check string
        // contains only digits
        String regex = "[0-9]+";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Find match between given string
        // and regular expression
        // using Pattern.matcher()
        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }

    public String pleaseEnterYourName(Display display) {
        String name = "";
        do {
            display.printEnterYourName();
            name = scanner.nextLine();
        } while(name == null || name.equals(""));
        return name;
    }
}



