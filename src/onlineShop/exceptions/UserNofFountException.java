package onlineShop.exceptions;

public class UserNofFountException extends Exception{
    public UserNofFountException() {
    }

    public UserNofFountException(String message) {
        super(message);
    }

    public UserNofFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNofFountException(Throwable cause) {
        super(cause);
    }

    public UserNofFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
