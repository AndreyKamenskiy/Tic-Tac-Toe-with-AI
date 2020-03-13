package tictactoe;

import static tictactoe.Condition.*;

class TTField {
    //String field; // like "XO___XO"
    short field; // 3^9 = 19683 so short will be enough;
    //lets take ternary system 0 = '_', 1=X ,2 = O

    TTField (String stringFormat){
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
                f[i][j] = Condition.getValue(stringFormat.charAt(counter));
//                System.out.printf("i=%d j=%d count=%d\n",i,j,counter);
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

    public Condition[][] toArray() {
        short f = this.field;
        Condition[][] res = new Condition[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = Condition.getCondition((short)(f % 3));
                f /= 3;
            }
        }
        return res;
    }

    public short getfiled() {
        return field;
    }

    /*
    * x = 1..3, y = 1..3;
     */

    public Condition getCellXY(int x, int y) {
        if ( x > 3 || x < 1 || y > 3 || y < 1) {
            System.exit(1); //todo change to Exception;
        }
        /*int q = (y - 1) * 3 + x - 1;
        var w = Math.pow(3, q);
        var e = (field / (short) w);
        var r = e % 3;*/

            //  3333

        switch ((field / (short) Math.pow(3, (y - 1) * 3 + x - 1)) % 3) {
            case 1:
                return X;
            case 2:
                return O;
        }
        return EMPTY;
    }

    public boolean isCellXYEmpty(int x, int y) {
        return getCellXY(x, y) == EMPTY;
    }

    private void setField(Condition[][] arr){
        field = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                field = (short)((field * 3) + arr[i][j].getValue());
            }
        }
    }

    public void setCellXY(int x, int y, Condition condition) {
        if ( x > 3 || x < 1 || y > 3 || y < 1) {
            System.exit(1); //todo change to Exception;
        }

        Condition[][] arr = toArray();
        arr[y-1][x-1] = condition;
        setField(arr);
    }


}
