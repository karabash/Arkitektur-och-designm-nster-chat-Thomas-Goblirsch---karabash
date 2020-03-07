package errorHandler;
import java.util.Collection;
import systemLogger.Logger;
public interface IErrorCollector {
	
    public Collection<IError> getErrors();
    public void addError(IError e);
    public void checkErrors();
    public void setLogger(Logger logger);
    public Logger getLogger();
}
