package tictactoe;

class TTField {
    //String field; // like "XO___XO"
    short field; // 3^9 = 19683 so short will be enough;
    //lets take ternary system 0 = '_', 1=X ,2 = O

    TTField (String StringFormat){
        /*
        *  not logic string format
        *  [13(s0) 23(s1) 33(s2)]
        *  [12(s3) 22(s4) 32(s5)]
        *  [11(s6) 21(s7) 31(s8)]
        *  */
        int counter = 0;
        short[][] f = new short[3][3];
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                f[i][j] = BITS.getValue(StringFormat.charAt(counter));
                System.out.printf("i=%d j=%d count=%d\n",i,j,counter);
                counter++;
            }
        }

        field = 0;

        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                field = (short)((field * 3) + f[i][j]);
            }
        }
    }

    public char[][] toArray() {
        short f = this.field;
        char[][] res = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = BITS.getName((short)(f % 3));
                f /= 3;
            }
        }
        return res;
    }

    public short getfiled() {
        return field;
    }
}
