package errorHandler;

public interface IError {

  
    public void setHandled(boolean handled);

    
    public boolean isHandled();

    public Exception getException();

  
    public String getTime();
    
    public abstract IErrorCollector getErrorHandler();

    public abstract String getMessage();

}
