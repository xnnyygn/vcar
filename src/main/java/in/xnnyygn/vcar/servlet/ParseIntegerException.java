package in.xnnyygn.vcar.servlet;

/**
 * Throw when failed to parse integer.
 * 
 * @author xnnyygn
 * @see Integer#parseInt(String)
 */
public class ParseIntegerException extends VcarException {

  private static final long serialVersionUID = -688941753407322462L;

  /**
   * Constructor.
   * 
   * @param message message
   */
  public ParseIntegerException(String message) {
    super(message);
  }

}
