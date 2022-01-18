package main.java.input;

import main.java.data.Board;
import main.java.data.Direction;
import main.java.display.Display;
import main.java.gamelogic.Battleship;
import main.java.gamelogic.RuleSet;

import java.util.Locale;
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
                    display.printGoodbye();
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
                "Game type:",
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
                        return RuleSet.PlayerType.AI_VS_AI;
                    default:
                        display.printSelectNumber1toN(RuleSet.PlayerType.values().length);
                }
            }
        }
    }

    public RuleSet.ShipForm selectShipForm(Display display) {

        String choice;
        display.printMenu("",
                "Ship shapes allowed in the game:",
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
                        display.printSelectNumber1toN(RuleSet.ShipForm.values().length);
                }
            }
        }
    }

    public RuleSet.ShipAdjacency selectShipAdjacency(Display display) {

        String choice;
        display.printMenu("",
                "Adjacency rules between ships:",
                "1 - Adjacency not allowed",
                "2 - Adjacency allowed");


        while (true) {
            display.printCommandPrompt();
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        return RuleSet.ShipAdjacency.NOT_ALLOWED;
                    case "2":
                        return RuleSet.ShipAdjacency.ALLOWED;

                    default:
                        display.printSelectNumber1toN(RuleSet.ShipAdjacency.values().length);
                }
            }
        }
    }

    public RuleSet.ShipPlacement selectShipPlacement(Display display) {
        String choice;
        display.printMenu("",
                "Ship placement:",
                "1 - random generate placement",
                "2 - manual placement");


        while (true) {
            display.printCommandPrompt();
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        return RuleSet.ShipPlacement.RANDOM;
                    case "2":
                        return RuleSet.ShipPlacement.MANUAL;

                    default:
                        display.printSelectNumber1toN(RuleSet.ShipPlacement.values().length);
                }
            }
        }
    }

    public Battleship.MenuSelection getMainMenuInput(Display display) {

        String choice;
        display.printMenu("******* BATTLESHIP GAME V1.0 *******",
                "Please choose from the following:",
                "1 - New Game",
                "2 - High Score",
                "3 - Options",
                "4 - Quit");


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
                        return Battleship.MenuSelection.OPTIONS;
                    case "4":
                        return Battleship.MenuSelection.QUIT;
                    default:
                        display.printSelectNumber1toN(Battleship.MenuSelection.values().length);
                }
            }
        }
    }

    public void getOptionsMenuInput(Display display, RuleSet ruleSet) {
        String choice;

        while (true) {
            display.printMenu("OPTIONS MENU",
                    "Please choose from the following:",
                    "1 - Set game type       (currently: " + ruleSet.getPlayerType().toString() + ")",
                    "2 - Set ship form       (currently: " + ruleSet.getShipForm().toString() + ")",
                    "3 - Set ship adjacency  (currently: " + ruleSet.getShipAdjacency().toString() + ")",
                    "4 - Set ship placement  (currently: " + ruleSet.getShipPlacement().toString() + ")",
                    "5 - Back to Main Menu");

            display.printCommandPrompt();
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        ruleSet.setPlayerType(selectPlayerType(display));
                        break;
                    case "2":
                        ruleSet.setShipForm(selectShipForm(display));
                        break;
                    case "3":
                        ruleSet.setShipAdjacency(selectShipAdjacency(display));
                        break;
                    case "4":
                        ruleSet.setShipPlacement(selectShipPlacement(display));
                        break;
                    case "5":
                        return;
                    default:
                        display.printSelectNumber1toN(Battleship.MenuSelection.values().length);
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
        return name.trim();
    }

    public String getShipPlacementInput() {
        return scanner.nextLine();
    }

    public Direction getDirectionFromUser(Display display) {
        Direction direction = null;

        do {
            display.printGetDirectionFromPlayer();
            String inputStr = scanner.nextLine();
            if(inputStr != null && inputStr.length() == 1)
                direction = Direction.getDirectionFromCharacter(inputStr.charAt(0));
        } while(direction == null);

        return direction;
    }

    public boolean getIsShipPlacementOk(Display display) {
        int number = -1;
        do {
            number = -1;
            display.printIsShipPlacementOk();
            String inputStr = scanner.nextLine();
            if(inputStr != null && inputStr.length() == 1 && Character.isDigit(inputStr.charAt(0)))
                number = Integer.parseInt(inputStr);
        } while (!(number == 1 || number == 2));
        return number == 1;
    }
}



