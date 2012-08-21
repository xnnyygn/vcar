package in.xnnyygn.vcar.servlet;

public interface InvocationResult {

  class Success implements InvocationResult {

    @Override
    public boolean isSuccess() {
      return true;
    }

  }

  public static final InvocationResult SUCCESS = new Success();

  public static class Failure implements InvocationResult {

    private final Exception exception;

    public Failure(Exception exception) {
      super();
      this.exception = exception;
    }

    @Override
    public boolean isSuccess() {
      return false;
    }

    public String getMessage() {
      return (exception instanceof VcarRuntimeException) ? "system error"
          : exception.getMessage();
    }

  }

  public boolean isSuccess();

}
