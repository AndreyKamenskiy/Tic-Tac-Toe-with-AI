package tictactoe;

public class RobotsCombat {

    private static int[] makeBattle(String xDifficult, String oDifficult, int numberBattles) {
        int xWin = 0;
        int oWin = 0;
        int draw = 0;
        for (int i = 0; i < numberBattles; i++) {

            TTGame ttGame = new TTGame();
            TTRobot xPlayer = RobotFabrics.CreateRobot(ttGame, xDifficult);
            TTRobot oPlayer = RobotFabrics.CreateRobot(ttGame, oDifficult);

            ttGame.setPlayers(xPlayer, oPlayer);

            GameStatus status = ttGame.silentStart();

            switch (status) {
                case X_WIN:
                    xWin++;
                    break;
                case O_WIN:
                    oWin++;
                    break;
                case DRAW:
                    draw++;
            }
        }
        int[] res = {xWin,oWin,draw};
        return res;
    }

    private static void printTable(String xPlayer, String oPlayer, String xWin, String oWin, String draw) {
        StringBuilder str = new StringBuilder(150);
        for (int i = 0; i < 100; i++) {
            str.append(" ");
        }
        str.insert(0, '|');
        str.insert(2, xPlayer);
        str.insert(15, '|');
        str.insert(17, oPlayer);
        str.insert(30, '|');
        str.insert(32, xWin);
        str.insert(40, '|');
        str.insert(42, oWin);
        str.insert(50, '|');
        str.insert(52, draw);
        str.insert(60, '|');
        System.out.println(str);
    }

    final static String[][] combatTable = {
            {"hard", "hard"},
            {"hard", "medium"},
            {"medium", "hard"},
            {"hard", "easy"},
            {"easy", "hard"},
            {"medium", "medium"},
            {"medium", "easy"},
            {"easy", "medium"},
            {"easy", "easy"},
    };


    public static void main(String[] args) {
        System.out.println ();
        StringBuilder top = new StringBuilder(100);
        for (int i = 0; i < 61; i++) {
            top.append("-");
        }

        System.out.println(top);
        printTable("X difficult", "O difficult", "X win", "O win", "Draw");
        System.out.println(top);
        int numberBattles = 50;
        for (int i = 0; i < combatTable.length; i++) {
            String xDifficult = combatTable[i][0];
            String oDifficult = combatTable[i][1];
            int[] res = makeBattle(xDifficult, oDifficult, numberBattles);

            printTable(xDifficult, oDifficult, String.valueOf(res[0]), String.valueOf(res[1]), String.valueOf(res[2]));
            System.out.println(top);
        }
    }
}


