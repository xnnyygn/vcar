package in.xnnyygn.vcar.servlet;

/**
 * Throw when number is not a nonnegative.
 * 
 * @author xnnyygn
 */
public class NotANonnegativeException extends VcarException {

  private static final long serialVersionUID = -6153780655783671249L;

  /**
   * Constructor.
   * 
   * @param message message
   */
  public NotANonnegativeException(String message) {
    super(message);
  }
  
}
