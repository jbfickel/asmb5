package prob0719;

public class HexArg extends AArg {
    private final int intValue;

    public HexArg(int i) {
        intValue = i;
    }

    public String generateListing() {
        return "0x" + String.format("%04X", intValue);
    }

    public String generateCode() {
        return String.format("%02X %02X", (intValue / 256), (intValue % 256));
    }

    public Integer getIntValue() {
        return intValue;
    }
}

/*      int i = intValue;

        char[] hexChars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] newHex = {'0','0','0','0'};

        for (int k = 3; i > 0; k--) {
            int j = i % 16;
            char c = hexChars[j];
            newHex[k] = c;
            i = i / 16;
        }
        String hexOut = "0x";

        for (int z = 0; z <= 3; z++) {
            hexOut = hexOut + newHex[z];
        }

        return hexOut;      */
