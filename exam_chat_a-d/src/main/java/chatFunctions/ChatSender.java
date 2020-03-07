package chatFunctions;
import listner.*;
import java.io.IOException;

import errorHandler.IErrorCollector;

/**
 * This class contract for chat functions
 * @author karabash
 *
 */
public interface ChatSender  {
	
	 /**
     * Send the chat message to all clients.
     *
     * @param sender Name of the sender.
     * @param message Text message to send.
     * @throws java.lang.Exception
     */
	public abstract void sendChat(String sender, String message) throws Exception;
	
	
}
