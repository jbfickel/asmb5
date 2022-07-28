package assembler;

public class NonUnaryInstrMn extends ACode {
    private final Mnemonic mnemonic;
    private final AArg aArg;

    public NonUnaryInstrMn(Mnemonic mn, AArg aArg, AddressingMode ad) {
        mnemonic = mn;
        this.aArg = aArg;
    }

    public String generateListing() {
        return String.format("%s    %s\n", Maps.mnemonicStringTable.get(mnemonic), aArg.generateListing());
    }

    public String generateCode() {
        return String.format("%02X %s\n", (Maps.instructionObjectCode.get(mnemonic)), aArg.generateCode());
    }
}
