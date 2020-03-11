package tictactoe;

enum BITS {
    EMPTY('_', (short) 0),
    X('X', (short) 1),
    O('O', (short) 2);

    private char name;
    private short value;

    BITS (char name,short value){
        this.value = value;
        this.name = name;
    }

    public short getValue() {
        return value;
    }

    public char getName() {
        return name;
    }

    public static short getValue(char name) {
        for (BITS bits : BITS.values()) {
            if (bits.getName() == name) {
                return bits.getValue();
            }
        }
        return 0;
    }

    public static char getName(short value) {
        for (BITS bits : BITS.values()) {
            if (bits.getValue() == value) {
                return bits.getName();
            }
        }
        return 0;
    }
}
