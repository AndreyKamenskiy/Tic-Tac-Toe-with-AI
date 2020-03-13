package tictactoe;

public enum GameStatus {
    X_TURN("Game not finished. X turn to move. "),
    O_TURN("Game not finished. O turn to move. "),
    DRAW("Draw."),
    X_WIN("X wins. "),
    Y_WIN("O wins. ");

    private String name;

    GameStatus(String name) {
        this.name = name;
    }
}
