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
}
