package listner;

public interface Listner {
	/**
	 * Start to listen for messages from other clients.
	 */
	public abstract void startListen();
	
	/**
	 * Disconnect a socket for swaping Communicator 
	 * @throws Exception
	 */
	public abstract void stopListen() throws Exception;
	
	/**
	 * Listen for messages from other clients.
	 *
	 * @throws Exception If there is an IO error.
	 */
	public void listenForMessages() throws Exception;

}
