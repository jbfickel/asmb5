package assembler;

import java.util.ArrayList;

public class Translator {
    private final InBuffer b;
    private Tokenizer t;
    private ACode aCode;

    public Translator(InBuffer inBuffer) {
        b = inBuffer;
    }

    private boolean parseLine() {
        boolean terminate = false;
        AArg opIntArg = new IntArg(0);
        AArg opHexArg = new HexArg(0);
        AArg specArg = new IntArg(0);
        DotCommand localDC = DotCommand.DOT_END;
        Mnemonic localMnemonic = Mnemonic.M_STOP;
        AddressingMode localAddressingMode = AddressingMode.ADDR_I;
        AToken aToken;
        aCode = new EmptyInstr();
        ParseState state = ParseState.PS_START;
        do {
            aToken = t.getToken();
            switch (state) {
                case PS_START:
                    if (aToken instanceof TIdentifier) {
                        TIdentifier localTIdentifier = (TIdentifier) aToken;
                        String tempStr = localTIdentifier.getStringValue();
                        if (Maps.unaryMnemonicTable.containsKey(tempStr.toLowerCase())) {
                            localMnemonic = Maps.unaryMnemonicTable.get(tempStr.toLowerCase());
                            aCode = new UnaryInstrMn(localMnemonic);
                            state = ParseState.PS_UNARY_1;
                        } else if (Maps.nonUnaryMnemonicTable.containsKey(tempStr.toLowerCase())) {
                            localMnemonic = Maps.nonUnaryMnemonicTable.get(tempStr.toLowerCase());
                            state = ParseState.PS_MNEMONIC;
                        } else {
                            aCode = new Error("Invalid mnemonic.");
                        }
                    } else if (aToken instanceof TDotCommand) {
                        TDotCommand localTDotCommand = (TDotCommand) aToken;
                        String tempStr = localTDotCommand.getStringValue();
                        if (Maps.ENDTable.containsKey(tempStr.toLowerCase())) {
                            localDC = Maps.ENDTable.get(tempStr.toLowerCase());
                            aCode = new END(localDC);
                            terminate = localDC == DotCommand.DOT_END;
                            state = ParseState.PS_UNARY_2;
                        } else if (Maps.BLOCKTable.containsKey(tempStr.toLowerCase())) {
                            localDC = Maps.BLOCKTable.get(tempStr.toLowerCase());
                            state = ParseState.PS_PSEUDO_OP;
                        } else {
                            aCode = new Error("Invalid dot command.");
                        }
                    } else if (aToken instanceof TEmpty) {
                        aCode = new EmptyInstr();
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error("Invalid dot command.");
                    }
                    break;
                case PS_MNEMONIC:
                    if (aToken instanceof TInteger) {
                        TInteger localTInteger = (TInteger) aToken;
                        opIntArg = new IntArg(localTInteger.getIntValue());
                        aCode = new NonUnaryInstrMn(localMnemonic, opIntArg, localAddressingMode);
                        specArg = opIntArg;
                        state = ParseState.PS_NON_UNARY_1;
                    } else if (aToken instanceof THexadecimal) {
                        THexadecimal localTHexadecimal = (THexadecimal) aToken;
                        opHexArg = new HexArg(localTHexadecimal.getHexValue());
                        aCode = new NonUnaryInstrMn(localMnemonic, opHexArg, localAddressingMode);
                        specArg = opHexArg;
                        state = ParseState.PS_NON_UNARY_1;
                    } else if (aToken instanceof TInvalid) {
                        aCode = new Error("Invalid integer or hexadecimal constant.");
                    } else {
                        aCode = new Error("Expected mnemonic specifier.");
                    }
                    break;
                case PS_NON_UNARY_1:
                    boolean isBranch = (localMnemonic == Mnemonic.M_BR) || (localMnemonic == Mnemonic.M_BRLT) || (localMnemonic == Mnemonic.M_BREQ) || (localMnemonic == Mnemonic.M_BRLE);
                    if (aToken instanceof TAddressingMode) {
                        TAddressingMode localTAddressingMode = (TAddressingMode) aToken;
                        String tempStr = localTAddressingMode.getStringValue();
                        if (Maps.addrTable.containsKey(tempStr.toLowerCase())) {
                            localAddressingMode = Maps.addrTable.get(tempStr.toLowerCase());
                            aCode = new AddrInstr(localMnemonic, specArg, localAddressingMode);
                            if ((localAddressingMode == AddressingMode.ADDR_I) && (localMnemonic == Mnemonic.M_DECI)) {
                                aCode = new Error("Invalid addressing mode for decimal input instruction.");
                            }
                            if ((localAddressingMode == AddressingMode.ADDR_I) && (localMnemonic == Mnemonic.M_STWA)) {
                                aCode = new Error("Invalid addressing mode for store word instruction.");
                            }
                            if (!((localAddressingMode == AddressingMode.ADDR_I) || (localAddressingMode == AddressingMode.ADDR_X)) && isBranch) {
                                aCode = new Error("Invalid addressing mode for branch instruction.");
                            }
                            if ((localAddressingMode == AddressingMode.ADDR_I) && isBranch) {
                                aCode = new NonUnaryInstrMn(localMnemonic, specArg, localAddressingMode);
                            }
                            state = ParseState.PS_NON_UNARY_2;
                        } else {
                            aCode = new Error("Invalid addressing mode.");
                        }
                    } else if (aToken instanceof TEmpty) {
                        if (isBranch) {
                            state = ParseState.PS_FINISH;
                        } else {
                            aCode = new Error("Expected addressing mode.");
                        }
                    } else {
                        aCode = new Error("Invalid characters after operation specifier.");
                    }
                    break;
                case PS_PSEUDO_OP:
                    if (aToken instanceof TInteger) {
                        TInteger localTInteger = (TInteger) aToken;
                        opIntArg = new IntArg(localTInteger.getIntValue());
                        aCode = new BLOCK(localDC, opIntArg);
                        state = ParseState.PS_NON_UNARY_3;
                    } else if (aToken instanceof THexadecimal) {
                        THexadecimal localTHexadecimal = (THexadecimal) aToken;
                        opHexArg = new HexArg(localTHexadecimal.getHexValue());
                        aCode = new BLOCK(localDC, opHexArg);
                        state = ParseState.PS_NON_UNARY_3;
                    } else if (aToken instanceof TInvalid) {
                        aCode = new Error("Invalid integer or hexadecimal constant.");
                    } else {
                        aCode = new Error("Expected hexadecimal or integer specifier.");
                    }
                    break;
                case PS_NON_UNARY_2:
                case PS_NON_UNARY_3:
                case PS_UNARY_1:
                case PS_UNARY_2:
                    if (aToken instanceof TEmpty) {
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error("Invalid trailing characters.");
                    }
                    break;
            }
        } while (state != ParseState.PS_FINISH && !(aCode instanceof Error));
        return terminate;
    }

    public void translate() {
        ArrayList<ACode> codeTable = new ArrayList<>();
        int numErrors = 0;
        t = new Tokenizer(b);
        boolean terminateWithEnd = false;
        b.getLine();
        while (b.inputRemains() && !terminateWithEnd) {
            terminateWithEnd = parseLine();
            codeTable.add(aCode);
            if (aCode instanceof Error) {
                numErrors++;
            }
            b.getLine();
        }
        if (!terminateWithEnd) {
            aCode = new Error("Missing .END sentinel.");
            codeTable.add(aCode);
            numErrors++;
        }
        if (numErrors == 0) {
            System.out.printf("Object code:\n");
            for (int i = 0; i < codeTable.size(); i++) {
                System.out.printf("%s", codeTable.get(i).generateCode());
            }
        }
        if (numErrors == 1) {
            System.out.printf("One error was detected.\n");
        } else if (numErrors > 1) {
            System.out.printf("%d errors were detected.\n", numErrors);
        }
        System.out.printf("\nProgram listing:\n");
        for (int i = 0; i < codeTable.size(); i++) {
            System.out.printf("%s", codeTable.get(i).generateListing());
        }
    }
}

