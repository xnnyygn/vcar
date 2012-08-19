/**
 * 
 */
package in.xnnyygn.vcar.servlet;

/**
 * Throw when image not found in the HTTP request.
 * 
 * @author xnnyygn
 */
public class ImageNotFoundException extends VcarRuntimeException {

  private static final long serialVersionUID = 2076140003308534484L;

  /**
   * Constructor.
   * 
   * @param message message
   */
  public ImageNotFoundException(String message) {
    super(message);
  }


}
