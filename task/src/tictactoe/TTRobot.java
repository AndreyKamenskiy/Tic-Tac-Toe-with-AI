
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

    abstract protected Coordinates getMove();
}

class RobotFabrics {
    static TTRobot CreateRobot(TTGame ttGame, String difficult) {
        TTRobot robot;
        switch (difficult) {
            case "easy":
                return new RandomRobot(ttGame);
            case "user":
                return new UserPlay(ttGame);
            case "medium":
                return new MediumRobot(ttGame);
            case "hard":
                return new MiniMaxRobot(ttGame);
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
    protected Coordinates getMove() {
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
    protected Coordinates getMove() {
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

class MediumRobot extends TTRobot {

    TTField field;

    public MediumRobot(TTGame ttGame) {
        super(ttGame);
        field = ttGame.getField();
    }

    @Override
    protected Coordinates getMove() {
        Condition enemyPlayFor = wePlayFor == X ? O : X;
        System.out.println("Making move level \"medium\"");
        Coordinates enemysWinnerMove = null;
        //Get possible moves
        ArrayList<Coordinates> emptyCoordinates = field.getEmpties();
        for (Coordinates currentMove : emptyCoordinates) {
            //check for we wins
            TTField newField = new TTField(field.getfiled());
            newField.setCell(currentMove, wePlayFor);
            //if we can winn - do it now
            if (newField.getWinner() == wePlayFor) {
                return currentMove;
            }
            //check for enemy wins if made this move
            newField.setCell(currentMove, enemyPlayFor);
            //if enemy could win making this move store this move to protect from him
            // don't break the loop because we can win at next move
            if (newField.getWinner() == enemyPlayFor) {
                enemysWinnerMove = currentMove;
            }
        }

        if (enemysWinnerMove != null) {
            return enemysWinnerMove;
        }

        //if we can't win and enemy can't win at moment will make random move;
        return emptyCoordinates.get((int)(Math.random() * emptyCoordinates.size()));

    }
}

class MiniMaxRobot extends TTRobot {

    TTField field;
    Condition enemyPlayFor;

    public MiniMaxRobot(TTGame ttGame) {
        super(ttGame);
        field = ttGame.getField();
    }

    private int miniMax(TTField checkedField, Condition turn, boolean findMax) {
        //End of the game?
        Condition winner = checkedField.getWinner();
        if (winner == wePlayFor) {
            return 1; // if we won return 1 score;
        } else if (winner == enemyPlayFor) {
            return -1; // enemy won
        } else if (!checkedField.haveEmpties()) {
            return 0; // have't empty moves, have't winner - it is a tie.
        }

        // if this not the end of the game.
        int score = findMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        //Get possible moves
        ArrayList<Coordinates> emptyCoordinates = checkedField.getEmpties();
        for (Coordinates currentMove : emptyCoordinates) {
            //make new field;
            TTField newField = new TTField(checkedField.getfiled());

            //make new move;
            newField.setCell(currentMove, turn);

            int currentScore = miniMax(newField, turn == X ? O : X, !findMax);

            if (findMax && currentScore > score || !findMax && currentScore < score) {
                score = currentScore;
            }

        }
        return score;
    }

    @Override
    protected Coordinates getMove() {
        enemyPlayFor = wePlayFor == X ? O : X;
        System.out.println("Making move level \"hard\"");
        Coordinates move = null;

        // max score move will be the best.
        int score = Integer.MIN_VALUE;
        Coordinates res = null;

        //Get possible moves
        ArrayList<Coordinates> emptyCoordinates = field.getEmpties();
        for (Coordinates currentMove : emptyCoordinates) {
            //make new field;
            TTField newField = new TTField(field.getfiled());
            //make new move;
            newField.setCell(currentMove, wePlayFor);

            //start minimax for our current move. enemy will play, he will find the minimum score. findMax = false;
            int currentScore = miniMax(newField, enemyPlayFor, false);

            if (currentScore > score) {
                score = currentScore;
                res = currentMove;
            }

        }

        return res;
    }

}

