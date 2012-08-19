/**
 * 
 */
package in.xnnyygn.vcar.servlet;

/**
 * Base class of VCAR checked exception.
 * 
 * @author xnnyygn
 */
public abstract class VcarException extends Exception {

  private static final long serialVersionUID = -1538886876949297564L;

  /**
   * Constructor. 
   */
  public VcarException() {
  }

  /**
   * Constructor.
   * 
   * @param message message
   */
  public VcarException(String message) {
    super(message);
  }

  /**
   * Constructor.
   * 
   * @param cause cause
   */
  public VcarException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructor.
   * 
   * @param message message
   * @param cause cause
   */
  public VcarException(String message, Throwable cause) {
    super(message, cause);
  }

}