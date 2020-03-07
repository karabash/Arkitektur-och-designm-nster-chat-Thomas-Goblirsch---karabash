package communication;

import controllers.MainWindowController;
import errorHandler.ErrorCollector;
import errorHandler.IErrorCollector;
import listner.Listner;
import observerPattern.IObserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.*;

import chatFunctions.ChatSender;
import systemLogger.Logger;

/**
 * The communicator handles the network traffic between all chat clients.
 * Messages are sent and received via the UDP protocol which may lead to
 * messages being lost.
 *
 * @author Thomas Ejnefjäll - Karabash
 */
public class UDPChatCommunicator  extends Communicator implements Runnable {
	//private MainWindowController _chat;
	//private final IErrorCollector ERROR_HANDLER;
	private final int DATAGRAM_LENGTH = 100;
	private final int PORT = 6789;
	private final String MULTICAST_ADDRESS = "228.28.28.28";
	private MulticastSocket _socket = null;
	private Set<IObserver> observerHashSet;
	private String startMessage = "<<--session starts here-->>";
	static int i = 0;

	public UDPChatCommunicator( ) {
		super();
		observerHashSet = new HashSet<>();
		System.setProperty("java.net.preferIPv4Stack", "true");
		//ERROR_HANDLER = new ErrorCollector();
	} 

	@Override
	public void sendChat(String sender, String message) throws Exception {
		try (DatagramSocket socket = new DatagramSocket()) {
			String toSend = sender + ": " + message;
			byte[] b = toSend.getBytes();

			DatagramPacket datagram = new DatagramPacket(b, b.length, InetAddress.getByName(MULTICAST_ADDRESS), PORT);

			socket.send(datagram);
			socket.close();
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public void startListen() {
		new Thread(this).start();
	}

	@Override
	public void listenForMessages() throws Exception {
		byte[] b = new byte[DATAGRAM_LENGTH];
		DatagramPacket datagram = new DatagramPacket(b, b.length);

		_socket = new MulticastSocket(PORT);
		_socket.joinGroup(InetAddress.getByName(MULTICAST_ADDRESS));

		while (true) {
			_socket.receive(datagram);
			String message = new String(datagram.getData());
			message = message.substring(0, datagram.getLength());
			//super.chat.receiveMessage(message);
			notifyObserver(message);
			datagram.setLength(b.length);
		}

	}

	@Override
	public void stopListen() throws Exception {
		_socket.leaveGroup(InetAddress.getByName(MULTICAST_ADDRESS));
		_socket.disconnect();


	}

	@Override
	public void run() {
		try {
			this.listenForMessages();

		} catch (Exception e) {
			//ERROR_HANDLER.addError(e);
		//	notifyObserver(" catched quantity is "+ i);
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
	public void notifyObserver(String message)  {
		try {

			for(IObserver iObser : observerHashSet) {

				iObser.update(message);

			}

		}catch (Exception e) {

		}		
	}


}




