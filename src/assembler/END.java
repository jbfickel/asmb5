package assembler;

public class END extends ACode {
    private final DotCommand dotcommand;

    public END(DotCommand dc) {
        dotcommand = dc;
    }

    public String generateListing() {
        return Maps.ENDStringTable.get(dotcommand) + "\n\n";
    }

    public String generateCode() {
        return "zz\n";
    }
}
