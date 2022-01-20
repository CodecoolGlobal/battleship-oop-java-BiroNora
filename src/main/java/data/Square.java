package main.java.data;

public class Square {
    private int x;
    private int y;
    private SquareStatus status = SquareStatus.EMPTY;

    public Square(int x, int y, SquareStatus status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getRowCol() {
        return new int[]{y, x};
    }

    public SquareStatus getStatus() {
        return status;
    }

    public boolean hasBeenShot() {
        return status == SquareStatus.HIT || status == SquareStatus.MISSED;
    }

    public void setStatus(SquareStatus status) {
        this.status = status;
    }
}
