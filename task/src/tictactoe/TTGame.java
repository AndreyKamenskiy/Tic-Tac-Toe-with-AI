
package tictactoe;

import static tictactoe.Condition.*;

public class TTGame {
    private TTField field;
    Condition turn;

    TTGame() {
        field = new TTField("_________");
        turn = X;
    }

    public void showfield() {
        Condition[][] arr = field.toArray();
        System.out.print("---------\n");
        for (int i = 2; i > -1 ; i--) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] == EMPTY ? "  " : arr[i][j]+  " ");
            }
            System.out.print("|\n");
        }
        System.out.print("---------\n");
    }

    public void setField(String stringFormat) {
        field = new TTField(stringFormat);
    }

    public TTField getField() {
        return field;
    }

    public boolean makeMoveXY(int x, int y) {
        if (!field.isCellXYEmpty(x, y)) {
            return false;
        }
        Condition condition;
        GameStatus status = field.getGameStatus();
        switch (status) {
            case X_TURN:
                condition = X;
                break;
            case O_TURN:
                condition = O;
                break;
            default:
                return false;
        }

        field.setCellXY(x, y, condition);
        return true;
    }

    public GameStatus getStatus() {
        return field.getGameStatus();
    }

    public boolean makeMove(Move move) {
        if (move.isValid() && field.isCellXYEmpty(move.x, move.y)) {
           field.setCellXY(move.x , move.y, turn);
           nextTurn();
           return true;
        }
        return false;
    }

    private void nextTurn() {
        if (turn == X) {
            turn = O;
        } else {
            turn = X;
        }
    }

    public Condition getCurrentTurn() {
        return turn == X? X : O; // protect turn field.
    }

}
