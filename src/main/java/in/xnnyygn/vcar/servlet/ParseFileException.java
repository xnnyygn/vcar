package in.xnnyygn.vcar.servlet;

/**
 * Throw when failed to parse file from HTTP request.
 * 
 * @author xnnyygn
 */
public class ParseFileException extends VcarRuntimeException {

  private static final long serialVersionUID = 8826077901300141270L;

  /**
   * Constructor.
   * 
   * @param message message
   * @param cause cause
   */
  public ParseFileException(String message, Throwable cause) {
    super(message, cause);
  }

  
}
