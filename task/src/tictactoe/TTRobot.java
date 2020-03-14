
package tictactoe;

import static tictactoe.Condition.*;

abstract class TTRobot {
    TTGame ttGame;
    Condition wePlayFor;

    TTRobot(TTGame ttGame) {
        this.ttGame = ttGame;
        switch (ttGame.getStatus()) {
            case X_TURN:
                wePlayFor = X;
                break;
            case O_TURN:
                wePlayFor = O;
            default:
                System.out.print("Initializing Robot error");
                System.exit(1);
        }
    }

    public void makeMove(){
        Move move = getMove();
        if (!ttGame.makeMove(move)) {
            System.out.print("Error making move");
            System.exit(1);
        }
    }

    abstract Move getMove();
}
