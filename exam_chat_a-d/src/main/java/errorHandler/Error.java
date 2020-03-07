package errorHandler;

import java.time.LocalDateTime;

public class Error implements IError {
	private Exception _messageException;
    private boolean _handledMessage;
    private String _stampleOfTimeMessage;
    
    /**
     * Constructs a new Error around an exception
     *
     * @param e The Exception to wrap
     * @param handled boolean indicating if the Error has been handled or not
     * @param description The time for when the Exception occurred
     */
    public Error(Exception e, boolean handled, String description) {
            this._messageException = e;
            this._handledMessage = handled;
            this._stampleOfTimeMessage = description;
    }
    
    @Override
    public void setHandled(boolean handled) {
    	_handledMessage = handled;
    }

    @Override
    public boolean isHandled() {
            return _handledMessage;
    }

    @Override
    public Exception getException() {
            return _messageException;
    }

	@Override
	public String getTime() {
		return LocalDateTime.now().toString();
	}

	@Override
	public IErrorCollector getErrorHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

  
}
