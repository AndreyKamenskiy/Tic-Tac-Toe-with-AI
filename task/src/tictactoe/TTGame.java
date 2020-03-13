
package tictactoe;

public class TTGame {
    private TTField field;
    TTGame() {
        field = new TTField("_________");
    }


    public void showfield() {
        Condition[][] arr = field.toArray();
        System.out.print("---------\n");
        for (int i = 2; i > -1 ; i--) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] + " ");
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
}
