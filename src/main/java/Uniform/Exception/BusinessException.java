package Uniform.Exception;

public class BusinessException extends Exception {

    public BusinessException(ErrorCodes errorCode, String... args) {
        super(String.format(errorCode.getMessage(), args) + " Код ошибки: " + errorCode.getCode());
    }
}