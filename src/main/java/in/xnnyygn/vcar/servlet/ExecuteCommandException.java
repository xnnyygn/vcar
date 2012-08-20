package in.xnnyygn.vcar.servlet;

public class ExecuteCommandException extends VcarRuntimeException {

  private static final long serialVersionUID = 137200224424082383L;

  public ExecuteCommandException(String message, Throwable cause) {
    super(message, cause);
  }

  public ExecuteCommandException(String message) {
    super(message);
  }

  
}
