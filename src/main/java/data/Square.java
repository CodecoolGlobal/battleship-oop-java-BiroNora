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

    public SquareStatus getStatus() {
        return status;
    }

    public void setStatus(SquareStatus status) {
        this.status = status;
    }
}
