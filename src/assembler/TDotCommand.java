package assembler;

public class TDotCommand extends AToken {
    private final String stringValue;

    public TDotCommand(StringBuffer stringBuffer) {
        stringValue = new String(stringBuffer);
    }

    public String getStringValue() {
        return stringValue;
    }

    public String getDescription() {
        return String.format("Dot command = %s", stringValue);
    }
}
