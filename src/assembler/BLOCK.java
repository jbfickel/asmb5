package assembler;

public class BLOCK extends ACode {
    private final DotCommand dotcommand;
    private final AArg aArg;

    public BLOCK(DotCommand dc, AArg aArg) {
        dotcommand = dc;
        this.aArg = aArg;
    }

    public String generateListing() {
        return String.format("%s  %s\n", Maps.BLOCKStringTable.get(dotcommand), aArg.generateListing());
    }

    public String generateCode() {
        return "00" + " 00".repeat(Math.max(1, aArg.getIntValue()) - 1) + "\n";
    }
}
