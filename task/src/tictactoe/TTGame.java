
package tictactoe;

import static tictactoe.Condition.*;

public class TTGame {
    private TTField field;
    private Condition turn;
    private TTRobot xPlayer = null;
    private TTRobot oPlayer = null;

    private boolean silentMode = false;

    public boolean isSilentMode() {
        return silentMode;
    }



    public void setPlayers(TTRobot xPlayer, TTRobot oPlayer) {
        this.xPlayer= xPlayer;
        this.oPlayer = oPlayer;
    }

    TTGame() {
        field = new TTField("_________");
        turn = X;
    }

    public void start() {
        silentMode = false;

        //Ready to start?
        if (xPlayer == null || oPlayer == null) {
            System.out.print("Players are not initialized!");
            System.exit(1);
        }

        //main Loop
        boolean playGame = true;
        Coordinates coordinates = null;
        GameStatus status = GameStatus.X_TURN;

        while (playGame) {

            showfield();
            switch (turn) {
                case X:
                   xPlayer.makeMove();
                    break;
                case O:
                    oPlayer.makeMove();
                    break;
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

    public GameStatus silentStart() {
        silentMode = true;
        //Ready to start?
        if (xPlayer == null || oPlayer == null) {
            System.out.print("Players are not initialized!");
            System.exit(1);
        }

        //main Loop
        GameStatus status = GameStatus.X_TURN;

        while (true) {

            switch (turn) {
                case X:
                    xPlayer.makeMove();
                    break;
                case O:
                    oPlayer.makeMove();
                    break;
            }
            status = getStatus();
            switch (status) {
                case X_WIN:
                case O_WIN:
                case DRAW:
                    return status;
            }
        }
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

    public GameStatus getStatus() {
        return field.getGameStatus();
    }

    public boolean makeMove(Coordinates coordinates) {
        if (coordinates.isValid() && field.isCellEmpty(coordinates)) {
           field.setCell(coordinates, turn);
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