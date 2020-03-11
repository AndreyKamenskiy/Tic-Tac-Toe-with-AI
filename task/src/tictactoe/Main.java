package tictactoe;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String str =  "OOO" +
                      "_X_" +
                      "XOO";

        TTField field = new TTField(str);
        char[][] fChar = field.toArray();
        for (int i = 2; i >=0 ; i--) {
            System.out.println(Arrays.toString(fChar[i]));
        }


    }
}
