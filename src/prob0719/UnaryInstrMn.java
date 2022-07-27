package prob0719;

public class UnaryInstrMn extends ACode {
    private final Mnemonic mnemonic;

    public UnaryInstrMn(Mnemonic mn) {
        mnemonic = mn;
    }

    public String generateListing() {
        return Maps.mnemonicStringTable.get(mnemonic) + "\n";
    }

    public String generateCode() {
        return String.format("%02X\n", Maps.instructionObjectCode.get(mnemonic));
    }
}
