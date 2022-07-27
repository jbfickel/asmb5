package prob0719;

public class Util {

    public static boolean isInt(char ch) {
        return ('0' <= ch) && (ch <= '9');
    }

    public static boolean isAlpha(char ch) {
        return (('a' <= ch) && (ch <= 'z') || ('A' <= ch) && (ch <= 'Z'));
    }

    public static boolean isHex(char ch) {
        return (('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'f') || ('A' <= ch && ch <= 'F'));
    }

    public static int hexValue(char ch) {
        if ('0' <= ch && ch <= '9') {
            return (ch - '0');
        } else if ('a' <= ch && ch <= 'f') {
            return (ch - 'a' + 10);
        } else if ('A' <= ch && ch <= 'F') {
            return (ch - 'A' + 10);
        }
        return -1;
    }

    public static boolean isDot(char ch) {
        return ('.' == ch);
    }

    public static boolean isComma(char ch) {
        return (',' == ch);
    }

    public static boolean isSpace(char ch) {
        return (' ' == ch);
    }

    public static boolean isSign(char ch) {
        return ((ch == '+') || (ch == '-'));
    }

}
