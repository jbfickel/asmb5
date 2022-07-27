package prob0719;

public class Tokenizer {
    private final InBuffer b;

    public Tokenizer(InBuffer inBuffer) {
        b = inBuffer;
    }

    public AToken getToken() {
        char nextChar;
        StringBuffer localStringValue = new StringBuffer();
        int localIntValue = 0;
        int sign = 1;
        AToken aToken = new TEmpty();
        LexState state = LexState.LS_START;
        do {
            nextChar = b.advanceInput();
            switch (state) {
                case LS_START:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_IDENT;
                    } else if (nextChar == '-') {
                        sign = -1;
                        state = LexState.LS_SIGN;
                    } else if (nextChar == '+') {
                        state = LexState.LS_SIGN;
                    } else if (Util.isInt(nextChar)) {
                        localIntValue = nextChar - '0';
                        state = LexState.LS_INTEGER1;
                    } else if (Util.isDot(nextChar)) {
                        state = LexState.LS_DOTCOMMAND1;
                    } else if (Util.isComma(nextChar)) {
                        state = LexState.LS_ADDRMODE1;
                    } else if (nextChar == '\n') {
                        state = LexState.LS_STOP;
                    } else if (nextChar != ' ') {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_INTEGER1:
                    if (Util.isInt(nextChar)) {
                        localIntValue = 10 * localIntValue + nextChar - '0';
                        if (localIntValue > 65535 || (sign == -1 && localIntValue > 32768)) {
                            aToken = new TInvalid();
                        }
                        state = LexState.LS_INTEGER2;
                    } else if (localIntValue == 0 && ((nextChar == 'x') || (nextChar == 'X'))) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_HEXADECIMAL1;
                    } else {
                        b.backUpInput();
                        aToken = new TInteger(sign * localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_INTEGER2:
                    if (Util.isInt(nextChar)) {
                        localIntValue = 10 * localIntValue + nextChar - '0';
                        if (localIntValue > 65535 || (sign == -1 && localIntValue > 32768)) {
                            aToken = new TInvalid();
                        }
                    } else {
                        b.backUpInput();
                        aToken = new TInteger(sign * localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_SIGN:
                    if (Util.isInt(nextChar)) {
                        localIntValue = nextChar - '0';
                        state = LexState.LS_INTEGER2;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_HEXADECIMAL1:
                    if (Util.isHex(nextChar)) {
                        localStringValue.append(nextChar);
                        localIntValue = 16 * localIntValue + Util.hexValue(nextChar);
                        state = LexState.LS_HEXADECIMAL2;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_HEXADECIMAL2:
                    if (Util.isHex(nextChar)) {
                        localStringValue.append(nextChar);
                        localIntValue = 16 * localIntValue + Util.hexValue(nextChar);
                        if (localIntValue > 65535) {
                            aToken = new TInvalid();
                        }
                    } else if (nextChar < '!' || nextChar > '˜' || Util.isComma(nextChar) || Util.isDot(nextChar) || Util.isSign(nextChar)) {
                        b.backUpInput();
                        aToken = new THexadecimal(localIntValue);
                        state = LexState.LS_STOP;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_IDENT:
                    if (Util.isAlpha(nextChar) || Util.isInt(nextChar)) {
                        localStringValue.append(nextChar);
                    } else {
                        b.backUpInput();
                        aToken = new TIdentifier(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_DOTCOMMAND1:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_DOTCOMMAND2;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_DOTCOMMAND2:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                    } else {
                        b.backUpInput();
                        aToken = new TDotCommand(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_ADDRMODE1:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_ADDRMODE2;
                    } else if (Util.isSpace(nextChar)) {
                        state = LexState.LS_ADDRMODE1;
                    } else {
                        aToken = new TInvalid();
                    }
                    break;
                case LS_ADDRMODE2:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                    } else {
                        b.backUpInput();
                        aToken = new TAddressingMode(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                }
        }  while ((state != LexState.LS_STOP) && !(aToken instanceof TInvalid)) ;
        return aToken;
    }
}
