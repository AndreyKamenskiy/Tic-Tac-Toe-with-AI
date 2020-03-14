package tictactoe;

public class Move {
    public int x, y;

    Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isValid() {
        return x > 0 && x < 4 && y > 0 && y < 4;
    }
}
