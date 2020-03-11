package tictactoe;

enum BITS {
    EMPTY('_', (short) 0b00),
    X('X', (short) 0b01),
    O('O', (short) 0b10);

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
}
