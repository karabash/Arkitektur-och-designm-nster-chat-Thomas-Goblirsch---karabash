package observerPattern;
/**
 * This interface is responsible for making a contract with subscriber
 * @author Karabash
 *
 */
public interface IObservable {
	/**
	 * Add Observer
	 * @param IObserver add an observer
	 */
    public void addObserver(IObserver observer);
    
    /**
     * Remove observer
     * @param IObserver remove an observer
     * @return
     */
    public boolean removeObserver(IObserver observer);
    
  /**
   * Notify observer
   * @param String send a message
   */
    public void notifyObserver(String send);

}
