package in.xnnyygn.vcar.servlet;

/**
 * Throw when failed to parse float.
 * 
 * @author xnnyygn
 */
public class ParseFloatException extends VcarException {

  private static final long serialVersionUID = -688941753407322462L;

  /**
   * Constructor.
   * 
   * @param message message
   */
  public ParseFloatException(String message) {
    super(message);
  }

}
