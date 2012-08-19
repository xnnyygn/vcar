/**
 * 
 */
package in.xnnyygn.vcar.servlet;

/**
 * Throw when failed to open image input stream.
 * 
 * @author xnnyygn
 */
public class ImageOpenFailureException extends VcarRuntimeException {

  private static final long serialVersionUID = -5897202831390320816L;

  /**
   * Constructor.
   * 
   * @param message message 
   * @param cause cause
   */
  public ImageOpenFailureException(String message, Throwable cause) {
    super(message, cause);
  }

  
}
