package Devonox.oktaauthentication.exception;

public class OktaServiceException extends RuntimeException {

    private final int status;

    public OktaServiceException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
