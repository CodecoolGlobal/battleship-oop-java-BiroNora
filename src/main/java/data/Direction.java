package main.java.data;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Direction getDirectionFromNumber(int number) {
        switch (number) {
            case 1 -> {
                return NORTH;
            }
            case 2 -> {
                return EAST;
            }
            case 3 -> {
                return SOUTH;
            }
            case 4 -> {
                return WEST;
            }
        }
        return null;
    }

    public static Direction getDirectionFromCharacter(char c) {
        switch (Character.toUpperCase(c)) {
            case 'N' -> {
                return NORTH;
            }
            case 'E' -> {
                return EAST;
            }
            case 'S' -> {
                return SOUTH;
            }
            case 'W' -> {
                return WEST;
            }
        }
        return null;
    }
}
