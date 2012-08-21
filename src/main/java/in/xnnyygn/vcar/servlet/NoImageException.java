/**
 * 
 */
package in.xnnyygn.vcar.servlet;

/**
 * Throw when {@code imageUrl} is blank or not a legal URL and {@code image} is
 * empty.
 * 
 * @author xnnyygn
 */
public class NoImageException extends VcarException {

  private static final long serialVersionUID = 2076140003308534484L;

  /**
   * Constructor.
   * 
   * @param message message
   */
  public NoImageException(String message) {
    super(message);
  }


}
