package prob0719;

public class Error extends ACode {
    private final String errorMessage;

    public Error(String errMessage) {
        errorMessage = errMessage;
    }

    public String generateListing() {
        return "ERROR: " + errorMessage + "\n\n";
    }

    public String generateCode() {
        return "";
    }
}
