package gameStore.store.exceptions;

public class ConfirmedPasswordIsWrongException extends RuntimeException {

    public ConfirmedPasswordIsWrongException(String message) {
        super(message);
    }

}
