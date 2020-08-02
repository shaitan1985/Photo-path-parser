package PhotoSort.Exceptions;

public class LaunchException extends Exception {
    public LaunchException() {
        super();
    }

    public LaunchException(String message) {
        super(message);
    }

    public LaunchException(String message, Throwable cause) {
        super(message, cause);
    }

    public LaunchException(Throwable cause) {
        super(cause);
    }
}
