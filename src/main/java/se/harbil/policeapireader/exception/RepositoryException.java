package se.harbil.policeapireader.exception;

public class RepositoryException extends RuntimeException {

    public RepositoryException(Exception e) {
        super(e);
    }
}
