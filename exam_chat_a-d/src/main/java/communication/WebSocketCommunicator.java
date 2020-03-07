package communication;

import controllers.MainWindowController;
import errorHandler.ErrorCollector;
import errorHandler.IErrorCollector;
import io.socket.client.IO;
import io.socket.client.Socket;
import observerPattern.IObserver;
import systemLogger.Logger;

import java.net.URISyntaxException;
import java.util.*;

import chatFunctions.ChatSender;
import listner.Listner;

/**
 * The communicator handles the network traffic between all chat clients.
 * Messages are sent and received via the WebSocket.
 * 
 * @author marre - karabash
 */
public class WebSocketCommunicator extends Communicator  {
	//private final IErrorCollector ERROR_HANDLER;
	// private  MainWindowController _chat;
	private final String WEB_SOCKET_ADDRESS = "http://laboration5.herokuapp.com/";
	private Socket IOSocket;
	private Set<IObserver> observerHashSet;
	private String message = "A new session start\n ";

	protected WebSocketCommunicator() {
		super();
		observerHashSet = new HashSet<>();
		//ERROR_HANDLER = new ErrorCollector();
	} 


	@Override
	public void sendChat(String sender, String message) throws Exception {
		try {
			this.IOSocket.emit("message", sender + ": " + message);
		} catch (Exception e) {
			//ERROR_HANDLER.addError(e);
			//notifyObserver("boom listen for message");    
		}
	}


	/**
	 * Start to listen for messages from other clients.
	 */
	@Override
	public void startListen() {
		try {
			this.listenForMessages();
		} catch (Exception e) {
			//ERROR_HANDLER.addError(e);
			//notifyObserver("boom listen for message");
		}
	}


	@Override
	public void stopListen() throws Exception {
		IOSocket.close();

	}

	@Override
	public void listenForMessages() throws Exception {
		try {
			IOSocket = IO.socket(WEB_SOCKET_ADDRESS);

			IOSocket.on("message", (final Object... args) -> {
				//super.chat.receiveMessage(args[0].toString());
				notifyObserver(args[0].toString());
			});
			IOSocket.connect();

		} catch (URISyntaxException e) {
			throw e;
		}
	}

	@Override
	public void addObserver(IObserver observer) {
		observerHashSet.add(observer);		
	}

	@Override
	public boolean removeObserver(IObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void notifyObserver(String message) {
		try {

			for(IObserver iObser : observerHashSet) {

				iObser.update(message);

			}
		}catch (Exception e) {
			//ERROR_HANDLER.checkErrors();
		}		
	}


}
