package errorHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import systemLogger.Logger;

public  class ErrorCollector implements  IErrorCollector{

	  private final Collection<IError> ERRORS = new ArrayList<>();

      private Logger messageLogger;

     
      public ErrorCollector(Logger logger) {
    	  messageLogger = logger;
      }

       public ErrorCollector() {
           super();
      }

      @Override
      public Collection<IError> getErrors() {
              return ERRORS;
      }

   
     
      @Override
      public void checkErrors() {
           
              
      }

      @Override
      public void setLogger(Logger logger) {
    	  messageLogger = logger;
      }

      @Override
      public Logger getLogger() {
              return messageLogger;
      }

	@Override
	public void addError(IError e) {
		ERRORS.add(e);
		
	}
}
