package main.java.input;

import main.java.display.Display;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {


    public int[] getCoordinateFromUser() {
        Scanner scanner = new Scanner(System.in);
        int[] arrCoordinate = new int[2];

        while (true) {

            Display.printMessage("\nType in the coordinates of your ship (e.g. A1,A13) or type in QUIT to give up: ");
            if (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                if (command.equals("")) {
                    Display.printMessage("You didn't type anything !");
                    continue;
                }
                if (command.toUpperCase().equals("QUIT")) {
                    break;
                } else {
                    try {
                        arrCoordinate = convertInputToCoordinate(command);
                        break;
                    } catch (IllegalArgumentException ex) {
                        Display.printMessage(ex.getMessage());
                    }
                }
            }
        }

        return arrCoordinate == null ? null : arrCoordinate;

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



