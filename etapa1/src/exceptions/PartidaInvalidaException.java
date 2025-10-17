package exceptions;

public class PartidaInvalidaException extends IllegalArgumentException {
  private static final long serialVersionUID = 1L;

  public PartidaInvalidaException(String message) {
    super(message);
  }

  public PartidaInvalidaException(String message, Throwable cause) {
    super(message, cause);
  }
}
