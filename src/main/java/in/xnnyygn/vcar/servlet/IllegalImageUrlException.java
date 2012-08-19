/**
 * 
 */
package in.xnnyygn.vcar.servlet;

/**
 * Throw when encounter illegal URL.
 * 
 * @author xnnyygn
 * @see java.net.URL#URL(String)
 */
public class IllegalImageUrlException extends VcarException {

  private static final long serialVersionUID = -1379355515916226808L;

  /**
   * Constructor.
   * 
   * @param message message
   * @param cause cause
   */
  public IllegalImageUrlException(String message, Throwable cause) {
    super(message, cause);
  }


}
