package assembler;

public class EmptyInstr extends ACode {
    public String generateListing() {
        return "\n";
    }

    public String generateCode() {
        return "";
    }
}
