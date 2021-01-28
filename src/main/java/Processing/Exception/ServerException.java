package Processing.Exception;

public class ServerException extends Exception {

    public ServerException(ServerErrorCodes errorCode, String... args) {
        super(String.format(errorCode.getMessage(), args) + " Код ошибки: " + errorCode.getCode());
    }

}
