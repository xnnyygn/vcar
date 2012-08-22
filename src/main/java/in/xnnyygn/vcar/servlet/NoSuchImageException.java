package in.xnnyygn.vcar.servlet;

/**
 * Throw when image not found by VCAR id.
 * 
 * @author xnnyygn
 */
public class NoSuchImageException extends VcarException {

  private static final long serialVersionUID = -3602819398189294347L;

  /**
   * Constructor.
   * 
   * @param message message
   */
  public NoSuchImageException(String message) {
    super(message);
  }
  
}
