package assembler;

public class THexadecimal extends AToken {
    private final int intValue;

    public THexadecimal(int i) {
        intValue = i;
    }

    public int getHexValue() {
        return intValue;
    }

    public String getDescription() {
        return String.format("Hexadecimal constant = %d", intValue);
    }
}
