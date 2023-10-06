package se.harbil.policeapireader.exception;

/**
 * The PoliceEventClientException class is a custom runtime exception that is used to wrap and propagate exceptions
 * related to the PoliceEventClient or issues when interacting with an external event API.
 */
public class PoliceEventClientException extends RuntimeException {

    /**
     * Constructs a new PoliceEventClientException with the specified cause.
     *
     * @param e The underlying exception that caused this exception to be thrown.
     */

    public PoliceEventClientException(final Exception e) {
        super(e);
    }
}
