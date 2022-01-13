package main.java.input;

import main.java.display.Display;
import main.java.gamelogic.RuleSet;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    Scanner scanner = new Scanner(System.in);

    public int[] getCoordinateFromUser(Display display) {

        int[] arrCoordinate = new int[2];

        while (true) {

            display.printGetCoordinateFromPlayer();
            display.printCommandPrompt();

            if (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                if (command.equals("")) {
                    display.printFromPlayerNoInput();
                    continue;
                }
                if (command.toUpperCase().equals("QUIT")) {
                    break;
                } else {
                    try {
                        arrCoordinate = convertInputToCoordinate(command);
                        break;
                    } catch (IllegalArgumentException ex) {
                        display.printSystemErrorMessage(ex.getMessage());
                    }
                }
            }
        }

        return arrCoordinate == null ? null : arrCoordinate;

    }

    public RuleSet.PlayerType selectPlayerType(Display display) {

        String choice;
        display.printplayerTypeMenu();


        while (true) {
            display.printCommandPrompt();
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine();
                choice = choice.trim();

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
        display.printShipFormMenu();


        while (true) {
            display.printCommandPrompt();
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine();
                choice = choice.trim();

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
        display.printShipAdjacencyMenu();


        while (true) {
            display.printCommandPrompt();
            if (scanner.hasNextLine()) {
                choice = scanner.nextLine();
                choice = choice.trim();

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

    // example a1,A1,a12,A12
    private int[] convertInputToCoordinate(String input) {

        String coordinate = input.toUpperCase().trim();
        String secondCoordinate = coordinate.substring(1, coordinate.length());

        if (!(checkUpperCharIsAlphabetical(coordinate.charAt(0))))
            throw new IllegalArgumentException("the first char can only be a letter. (A-Z)");

        if (!(onlyDigits(secondCoordinate)) || secondCoordinate.isEmpty() || secondCoordinate.length() > 2 || secondCoordinate.equals(0))
            throw new IllegalArgumentException("after the first char, there can only be numbers and max on 2 length.(0-9)");


        int[] aCoordinate = new int[]{(int) coordinate.charAt(0) - 17, Integer.parseInt(secondCoordinate) - 1};


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

}



