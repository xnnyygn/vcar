package in.xnnyygn.vcar.servlet;

/**
 * Throw when file not found.
 * 
 * @author xnnyygn
 */
public class NoSuchFileException extends VcarRuntimeException {

  private static final long serialVersionUID = 2489891081824796598L;

  /**
   * Constructor.
   * 
   * @param message message
   */
  public NoSuchFileException(String message) {
    super(message);
  }
  
}
