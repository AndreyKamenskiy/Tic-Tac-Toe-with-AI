package tictactoe;

enum Condition {
    EMPTY('_', (short) 0),
    X('X', (short) 1),
    O('O', (short) 2);

    private char name;
    private short value;

    Condition(char name, short value){
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
        for (Condition condition : Condition.values()) {
            if (condition.getName() == name) {
                return condition.getValue();
            }
        }
        return 0;
    }

    public static char getName(short value) {
        for (Condition condition : Condition.values()) {
            if (condition.getValue() == value) {
                return condition.getName();
            }
        }
        return 0;
    }

    public static Condition getCondition(short value) {
        for (Condition condition : Condition.values()) {
            if (condition.getValue() == value) {
                return condition;
            }
        }
        return EMPTY;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
