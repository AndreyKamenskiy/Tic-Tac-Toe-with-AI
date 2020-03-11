package tictactoe;

import java.util.BitSet;
import java.util.Iterator;

class TTField {
    //String field; // like "XO___XO"
    short field; // 3^9 = 19683 so short will be enough;
    // 2 bits for cell. 00 = "_", 01 = "X", 10 = "0";
    // bits 01 - Cell(1,1), bits 23 - Cell(2,1) and so on.


    TTField (String StringFormat){
        /*
        *  not logic string format
        *  [13(s0)12-13  23(s1)14-15 33(s2)15-16]
        *  [12(s3)6-7    22(s4)8-9   32(s5)10-11]
        *  [11(s6)0-1    21(s7)2-3   31(s8)4-5]
        * 12(s3)6-7 - means cell(x=1, y=2), s3 - String[4], 6-7 - bits number
        *  */
        int counter = 0;
        short[][] f = new short[3][3];
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                f[i][j] = BITS.getValue(StringFormat.charAt(counter++));
            }
        }

        field = 0;

        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                field = (short)((field << 2) + f[i][j]);
            }
        }
    }

    public int[][] toArray() {


        return new int[3][3];
    }
}
