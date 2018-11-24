package Exception;

public class StatementExecutionError extends RuntimeException {
    public StatementExecutionError(String message) {
        super(message);
    }
}
