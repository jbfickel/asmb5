package prob0719;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class Maps {
    public static final Map<String, Mnemonic> unaryMnemonicTable;
    public static final Map<String, Mnemonic> nonUnaryMnemonicTable;
    public static final Map<Mnemonic, String> mnemonicStringTable;

    public static final Map<String, DotCommand> ENDTable;
    public static final Map<DotCommand, String> ENDStringTable;

    public static final Map<String, DotCommand> BLOCKTable;
    public static final Map<DotCommand, String> BLOCKStringTable;

    public static final Map<String, AddressingMode> addrTable;
    public static final Map<AddressingMode, String> addrStringTable;

    public static final Map<Mnemonic, Integer> instructionObjectCode;

    static {
        instructionObjectCode = new EnumMap<>(Mnemonic.class);
        instructionObjectCode.put(Mnemonic.M_STOP, 0);
        instructionObjectCode.put(Mnemonic.M_ASLA, 10);
        instructionObjectCode.put(Mnemonic.M_ASRA, 12);
        instructionObjectCode.put(Mnemonic.M_BR, 18);
        instructionObjectCode.put(Mnemonic.M_BRLT, 22);
        instructionObjectCode.put(Mnemonic.M_BREQ, 24);
        instructionObjectCode.put(Mnemonic.M_BRLE, 20);
        instructionObjectCode.put(Mnemonic.M_CPWA, 160);
        instructionObjectCode.put(Mnemonic.M_DECI, 48);
        instructionObjectCode.put(Mnemonic.M_DECO, 56);
        instructionObjectCode.put(Mnemonic.M_ADDA, 96);
        instructionObjectCode.put(Mnemonic.M_SUBA, 88);
        instructionObjectCode.put(Mnemonic.M_STWA, 224);
        instructionObjectCode.put(Mnemonic.M_LDWA, 192);


        addrTable = new HashMap<>();
        addrTable.put("i", AddressingMode.ADDR_I);
        addrTable.put("d", AddressingMode.ADDR_D);
        addrTable.put("n", AddressingMode.ADDR_N);
        addrTable.put("s", AddressingMode.ADDR_S);
        addrTable.put("sf", AddressingMode.ADDR_SF);
        addrTable.put("x", AddressingMode.ADDR_X);
        addrTable.put("sx", AddressingMode.ADDR_SX);
        addrTable.put("sfx", AddressingMode.ADDR_SFX);

        addrStringTable = new EnumMap<>(AddressingMode.class);
        addrStringTable.put(AddressingMode.ADDR_I, ",i");
        addrStringTable.put(AddressingMode.ADDR_D, ",d");
        addrStringTable.put(AddressingMode.ADDR_N, ",n");
        addrStringTable.put(AddressingMode.ADDR_S, ",s");
        addrStringTable.put(AddressingMode.ADDR_SF, ",sf");
        addrStringTable.put(AddressingMode.ADDR_X, ",x");
        addrStringTable.put(AddressingMode.ADDR_SX, ",sx");
        addrStringTable.put(AddressingMode.ADDR_SFX, ",sfx");


        BLOCKTable = new HashMap<>();
        BLOCKTable.put("block", DotCommand.DOT_BLOCK);

        BLOCKStringTable = new EnumMap<>(DotCommand.class);
        BLOCKStringTable.put(DotCommand.DOT_BLOCK, ".BLOCK");


        ENDTable = new HashMap<>();
        ENDTable.put("end", DotCommand.DOT_END);

        ENDStringTable = new EnumMap<>(DotCommand.class);
        ENDStringTable.put(DotCommand.DOT_END, ".END");


        unaryMnemonicTable = new HashMap<>();
        unaryMnemonicTable.put("stop", Mnemonic.M_STOP);
        unaryMnemonicTable.put("asla", Mnemonic.M_ASLA);
        unaryMnemonicTable.put("asra", Mnemonic.M_ASRA);

        nonUnaryMnemonicTable = new HashMap<>();
        nonUnaryMnemonicTable.put("br", Mnemonic.M_BR);
        nonUnaryMnemonicTable.put("brlt", Mnemonic.M_BRLT);
        nonUnaryMnemonicTable.put("breq", Mnemonic.M_BREQ);
        nonUnaryMnemonicTable.put("brle", Mnemonic.M_BRLE);
        nonUnaryMnemonicTable.put("cpwa", Mnemonic.M_CPWA);
        nonUnaryMnemonicTable.put("deci", Mnemonic.M_DECI);
        nonUnaryMnemonicTable.put("deco", Mnemonic.M_DECO);
        nonUnaryMnemonicTable.put("adda", Mnemonic.M_ADDA);
        nonUnaryMnemonicTable.put("suba", Mnemonic.M_SUBA);
        nonUnaryMnemonicTable.put("stwa", Mnemonic.M_STWA);
        nonUnaryMnemonicTable.put("ldwa", Mnemonic.M_LDWA);

        mnemonicStringTable = new EnumMap<>(Mnemonic.class);
        mnemonicStringTable.put(Mnemonic.M_STOP, "STOP");
        mnemonicStringTable.put(Mnemonic.M_ASLA, "ASLA");
        mnemonicStringTable.put(Mnemonic.M_ASRA, "ASRA");
        mnemonicStringTable.put(Mnemonic.M_BR, "BR  ");
        mnemonicStringTable.put(Mnemonic.M_BRLT, "BRLT");
        mnemonicStringTable.put(Mnemonic.M_BREQ, "BREQ");
        mnemonicStringTable.put(Mnemonic.M_BRLE, "BRLE");
        mnemonicStringTable.put(Mnemonic.M_CPWA, "CPWA");
        mnemonicStringTable.put(Mnemonic.M_DECI, "DECI");
        mnemonicStringTable.put(Mnemonic.M_DECO, "DECO");
        mnemonicStringTable.put(Mnemonic.M_ADDA, "ADDA");
        mnemonicStringTable.put(Mnemonic.M_SUBA, "SUBA");
        mnemonicStringTable.put(Mnemonic.M_STWA, "STWA");
        mnemonicStringTable.put(Mnemonic.M_LDWA, "LDWA");
    }

}
