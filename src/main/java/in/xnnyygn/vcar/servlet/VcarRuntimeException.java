/**
 * 
 */
package in.xnnyygn.vcar.servlet;

/**
 * Base runtime exception for VCAR.
 * 
 * @author xnnyygn
 */
public abstract class VcarRuntimeException extends RuntimeException {

  private static final long serialVersionUID = -8810911684257550769L;

  /**
   * Constructor. 
   */
  public VcarRuntimeException() {
  }

  /**
   * Constructor.
   * 
   * @param message message
   */
  public VcarRuntimeException(String message) {
    super(message);
  }

  /**
   * Constructor.
   * 
   * @param cause cause
   */
  public VcarRuntimeException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructor.
   * 
   * @param message message
   * @param cause cause
   */
  public VcarRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

}
