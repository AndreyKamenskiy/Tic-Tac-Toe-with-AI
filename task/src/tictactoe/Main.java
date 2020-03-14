
package tictactoe;

import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TTGame ttGame = new TTGame();
        String difficult = "easy";

        TTRobot xPlayer = RobotFabrics.CreateRobot(ttGame, "user");
        TTRobot oPlayer = RobotFabrics.CreateRobot(ttGame, difficult);

        ttGame.setPlayers(xPlayer, oPlayer);

        ttGame.start();
    }
}
