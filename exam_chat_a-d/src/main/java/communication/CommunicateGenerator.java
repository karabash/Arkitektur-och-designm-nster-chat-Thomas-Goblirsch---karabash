package communication;
/**
 *  * @author Karabash

 */
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import controllers.MainWindowController;


/**
 * The communicator generates an instance of Communicator 
 * @author Karabash
 */

public class CommunicateGenerator {
	private  static CommunicateGenerator instance;
	
	private  CommunicateGenerator() {
		if (instance == null ) {
			synchronized (CommunicateGenerator.class) {
				if (instance == null) {
					instance = new CommunicateGenerator();

				}
			}
		}
	}

/**
 * Creates and returns an instance of a sub class Communicator
 * @param Only an argument, that initializes a sub class of Communicator
 * @param Helps for finding out which sub class of communicator needs by app 
 * @return A Communicator 
 * @throws Exception 
 */
public static Communicator getInstanceOfCommunicatorType(String communicatorType) throws Exception {
		Communicator com = null;
		if("UDP".equalsIgnoreCase(communicatorType)) {
			com = new UDPChatCommunicator();
			com.stopListen();
			com.startListen();

		}
		else if("WebSocket".equalsIgnoreCase(communicatorType)) {
			com =new WebSocketCommunicator();
			com.stopListen();
			com.startListen();

		}
		return com; 
	}
}
