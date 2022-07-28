package assembler;

public class TAddressingMode extends AToken {
    private final String stringValue;

    public TAddressingMode(StringBuffer stringBuffer) {
        stringValue = new String(stringBuffer);
    }

    public String getStringValue() {
        return stringValue;
    }

    public String getDescription() {
        return String.format("Addressing mode = %s", stringValue);
    }
}
