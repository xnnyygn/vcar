package in.xnnyygn.vcar.servlet;

/**
 * Throw when failed to execute command.
 *  
 * @author xnnyygn
 */
public class ExecuteCommandException extends VcarRuntimeException {

  private static final long serialVersionUID = 137200224424082383L;

  /**
   * Constructor.
   * 
   * @param message message
   * @param cause cause
   */
  public ExecuteCommandException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor.
   * 
   * @param message message
   */
  public ExecuteCommandException(String message) {
    super(message);
  }
  
}
