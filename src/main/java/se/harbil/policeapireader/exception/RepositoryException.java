package se.harbil.policeapireader.exception;

/**
 * The RepositoryException class is a custom runtime exception that is used to wrap and propagate exceptions
 * related to database repository operations, such as saving or retrieving data from a database repository.
 */

public class RepositoryException extends RuntimeException {

    /**
     * Constructs a new RepositoryException with the specified cause.
     *
     * @param e The underlying exception that caused this exception to be thrown.
     */

    public RepositoryException(final Exception e) {
        super(e);
    }
}
