
package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TTGame ttGame = new TTGame();
        String xDifficult = "easy";
        String oDifficult = "user";
        //String oDifficult = "easy";

        TTRobot xPlayer = RobotFabrics.CreateRobot(ttGame, xDifficult);
        TTRobot oPlayer = RobotFabrics.CreateRobot(ttGame, oDifficult);

        ttGame.setPlayers(xPlayer, oPlayer);

        ttGame.start();
    }
}
