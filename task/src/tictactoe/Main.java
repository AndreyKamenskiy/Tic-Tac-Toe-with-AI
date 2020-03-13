package tictactoe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str =  "OOO" +
                      "_X_" +
                      "XOO";

        TTField field = new TTField(str);
        Condition[][] f = field.toArray();
        for (int i = 2; i >=0 ; i--) {
            System.out.println(Arrays.toString(f[i]));
        }

        for (int y = 3; y > 0 ; y--) {
            for (int x = 3; x > 0 ; x--) {
                System.out.print(field.getCellXY(x,y).getName() + " ");
            }
            System.out.print("\n");
        }

        for (int y = 3; y > 0 ; y--) {
            for (int x = 3; x > 0 ; x--) {
                field.setCellXY(x, y, Condition.X);
            }
        }
        field.setCellXY(2, 2, Condition.O);

        for (int y = 3; y > 0 ; y--) {
            for (int x = 3; x > 0 ; x--) {
                System.out.print(field.getCellXY(x,y).getName() + " ");
            }
            System.out.print("\n");
        }

    }
}
