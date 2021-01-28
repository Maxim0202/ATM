package ATMPackage.Exception;

public class ATMException extends Exception {

    public ATMException(ATMErrorCodes errorCode, String... args) {
        super(String.format(errorCode.getMessage(), args) + " Код ошибки: " + errorCode.getCode());
    }

    public ATMException() {
    }

    public ATMException(String message) {
        super(message);
    }

    public ATMException(String message, Throwable cause) {
        super(message, cause);
    }

    public ATMException(Throwable cause) {
        super(cause);
    }

    public ATMException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
