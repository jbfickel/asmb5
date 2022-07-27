package prob0719;

public class TInteger extends AToken {
    private final int intValue;

    public TInteger(int i) {
        intValue = i;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getDescription() {
        return String.format("Integer constant = %d", intValue);
    }
}
