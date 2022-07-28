package assembler;

public class AddrInstr extends ACode {
    private final Mnemonic mnemonic;
    private final AArg aArg;
    private final AddressingMode addr;

    public AddrInstr(Mnemonic mn, AArg aArg, AddressingMode ad) {
        mnemonic = mn;
        this.aArg = aArg;
        addr = ad;
    }

    public String generateListing() {
        return String.format("%s    %s%s\n", Maps.mnemonicStringTable.get(mnemonic), aArg.generateListing(), Maps.addrStringTable.get(addr));
    }

    public String generateCode() {
        boolean isBranch = (mnemonic == Mnemonic.M_BR) || (mnemonic == Mnemonic.M_BRLT) || (mnemonic == Mnemonic.M_BREQ) || (mnemonic == Mnemonic.M_BRLE);
        if (isBranch && addr == AddressingMode.ADDR_X) {
            return String.format("%02X %s\n", (Maps.instructionObjectCode.get(mnemonic) + 1), aArg.generateCode());
        } else {
            return String.format("%02X %s\n", (Maps.instructionObjectCode.get(mnemonic) + addr.ordinal()), aArg.generateCode());
        }
    }
}
