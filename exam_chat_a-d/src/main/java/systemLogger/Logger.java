package systemLogger;

import communication.Communicator;
import errorHandler.IErrorCollector;

public interface Logger {
	public abstract void logEvent(String event, String prefix, String timeStamp) throws Exception;
    public abstract void setOnOFF(boolean state);
    public abstract boolean getState();
    public abstract void update(Communicator com);

}
