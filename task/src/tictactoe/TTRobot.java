
package tictactoe;

import java.util.ArrayList;
import java.util.Scanner;

import static tictactoe.Condition.*;

abstract class TTRobot {
    TTGame ttGame;
    Condition wePlayFor = null;

    TTRobot(TTGame ttGame) {
        this.ttGame = ttGame;

    }

    final public void makeMove(){
        Condition currentTurn = null;
        switch (ttGame.getStatus()) {
            case X_TURN:
                currentTurn = X;
                break;
            case O_TURN:
                currentTurn = O;
                break;
            default:
                System.out.print("Cannot make move");
                System.exit(1);
        }

        if (wePlayFor == null) {
            wePlayFor = currentTurn;
        } else if (wePlayFor != currentTurn) {
            System.out.print("Error making move. Players side was changing ");
            System.exit(1);
        }

        Coordinates coordinates = getMove();
        if (!ttGame.makeMove(coordinates)) {
            System.out.print("Error making move");
            System.exit(1);
        }
    }

    abstract Coordinates getMove();
}

class RobotFabrics {
    static TTRobot CreateRobot(TTGame ttGame, String difficult) {
        TTRobot robot;
        switch (difficult) {
            case "easy":
                return new RandomRobot(ttGame);
            case "user":
                return new UserPlay(ttGame);
        }
        return null;
    }
}

class RandomRobot extends TTRobot {

    private TTField field;

    RandomRobot(TTGame ttGame) {
        super(ttGame);
        field = ttGame.getField();
    }

    @Override
    Coordinates getMove() {
        ArrayList<Coordinates> emptyCoordinates = field.getEmpties();
        Coordinates res = emptyCoordinates.get((int)(Math.random() * emptyCoordinates.size()));
        System.out.println("Making move level \"easy\"");
        return res;
    }
}

class UserPlay extends TTRobot {
    TTField field;

    UserPlay(TTGame ttGame) {
        super(ttGame);
        field = ttGame.getField();
    }

    @Override
    Coordinates getMove() {
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        int y = 0;
        boolean validAnswer = false;
        do {
            System.out.print("Enter the coordinates: ");
            String coord = scanner.nextLine();
            try {
                String[] arr = coord.split("\\s+");
                x = Integer.parseInt(arr[0]);
                y = Integer.parseInt(arr[1]);

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You should enter 2 numbers!");
                continue;
            }

            if ( x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.print("Coordinates should be from 1 to 3!\n");
                continue;
            }

            if (!field.isCellEmpty(new Coordinates(x, y))) {

                System.out.print("This cell is occupied! Choose another one!\n");
                continue;
            }

            validAnswer = true;

        } while (!validAnswer);

        return new Coordinates(x, y);
    }

}


