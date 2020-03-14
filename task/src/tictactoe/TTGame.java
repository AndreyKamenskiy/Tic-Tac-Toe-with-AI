
package tictactoe;

import static tictactoe.Condition.*;

public class TTGame {
    private TTField field;
    private Condition turn;
    private TTRobot xPlayer = null;
    private TTRobot oPlayer = null;

    public void setPlayers(TTRobot xPlayer, TTRobot oPlayer) {
        this.xPlayer= xPlayer;
        this.oPlayer = oPlayer;
    }

    TTGame() {
        field = new TTField("_________");
        turn = X;
    }

    public void start() {
        //Ready to start?
        if (xPlayer == null || oPlayer == null) {
            System.out.print("Players are not initialized!");
            System.exit(1);
        }

        //main Loop
        boolean playGame = true;
        Move move = null;
        GameStatus status = GameStatus.X_TURN;

        while (playGame) {

            showfield();

            switch (turn) {
                case X:
                    move = xPlayer.getMove();
                    break;
                case O:
                    move = oPlayer.getMove();
                    break;
            }

            if (!makeMove(move)) {
                System.out.print("Error occupied when try to make move!");
                System.exit(1);
            }

            status = getStatus();
            switch (status) {
                case X_WIN:
                case O_WIN:
                case DRAW:
                    playGame = false;
                    break;
            }
        }

        showfield();
        System.out.println(status);
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
