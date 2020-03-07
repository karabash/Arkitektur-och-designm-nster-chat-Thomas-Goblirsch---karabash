package observerPattern;
/**
 * This interface makes a contract for recieving subscriber updates
 * @author karabash
 *
 */
public interface IObserver {
	
	 /**
	  * Update observer
	  * @param message
	  * @throws Exception 
	  */
    public void update(String message) throws Exception;

}
