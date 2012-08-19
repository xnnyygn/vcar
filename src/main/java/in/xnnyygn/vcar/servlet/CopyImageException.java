/**
 * 
 */
package in.xnnyygn.vcar.servlet;

/**
 * Throw when failed to copy image.
 * 
 * @author xnnyygn
 */
public class CopyImageException extends VcarRuntimeException {

  private static final long serialVersionUID = 5851956346902938495L;

  /**
   * Constructor.
   * 
   * @param message message
   * @param cause cause
   */
  public CopyImageException(String message, Throwable cause) {
    super(message, cause);
  }

}
