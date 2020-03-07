package communication;

import listner.Listner;
import chatFunctions.ChatSender;
import controllers.MainWindowController;
import errorHandler.*;
import observerPattern.IObservable;
import observerPattern.IObserver;
/**
 * 
 * @author Karabash
 *
 */
public abstract class Communicator implements IObservable, Listner, ChatSender{
	protected IObserver chat;
	public	Communicator(IObserver chat){
		this.chat = chat;
	}

	public	Communicator(){
		super();
	}
	
}
