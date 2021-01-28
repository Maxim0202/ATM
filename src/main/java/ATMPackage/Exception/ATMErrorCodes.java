package ATMPackage.Exception;

public enum ATMErrorCodes {
    ERR_ATM_CASH_AVAIL(1, "Невозможно выдать введенную сумму %s %s."),
    ERR_SERVER(4, "Ошибка сервера [%s]");

    private final int code;
    private final String message;

    ATMErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
