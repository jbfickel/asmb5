package assembler;

public class IntArg extends AArg {
    private final int intValue;

    public IntArg(int i) {
        intValue = i;
    }

    public String generateListing() {
        return String.format("%d", intValue);
    }

    public String generateCode() {
        if (intValue >= 0) {
            return String.format("%02X %02X", (intValue / 256), (intValue % 256));
        } else {
            String negativeInt = String.format("%X", intValue);
            return String.format("%s %s", negativeInt.substring(4, 6), negativeInt.substring(6, 8));
        }
    }

    public Integer getIntValue() {
        return intValue;
    }
}
