package in.xnnyygn.vcar.servlet;

/**
 * Throw when {@code vcarId} is blank or image not found.
 * 
 * @author xnnyygn
 */
public class SourceImageNotFoundException extends VcarException {

  private static final long serialVersionUID = 6976641177102465404L;

  /**
   * Constructor.
   * 
   * @param message message
   */
  public SourceImageNotFoundException(String message) {
    super(message);
  }

}
