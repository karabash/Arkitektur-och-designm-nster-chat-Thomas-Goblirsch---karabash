package controllers;


import communication.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import listner.ClickEventsListener;
import observerPattern.IObserver;
import systemLogger.*;

/**
 * FXML Controller class
 *
 * @author marre
 */
public class MainWindowController  implements Initializable, IObserver, ClickEventsListener  {

	@FXML
	private TextArea txtAreaChat;
	@FXML
	private TextField txtMessage;
	@FXML
	private TextField txtName;
	@FXML
	private ToggleGroup gg;

	//------------- Deluppgift A test:
	//Change what line is commented to change communicator:
	private Communicator _communicator = new UDPChatCommunicator();
	// TxtLogger txt =   new TxtLogger("errorlog");
    private List<Exception> listofEx  = new ArrayList<Exception>();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		_communicator.addObserver(this);
		_communicator.startListen();
		addRadioButtonListener();
	}

	/**
	 * Receive message from user.
	 *
	 * @param message The received message.
	 */
	public void receiveMessage(String message) {
		txtAreaChat.setText(txtAreaChat.getText() + "\n" + message);
	}

	/**
	 * Inform the user that an error has occurred and exit the application.
	 *
	 * @param e
	 */
	public void error(Exception e) {
		showAlert("An error has occured and the application will close: \n" + e.getMessage(), "Error Error!");
		System.exit(1);
	}


	public void addRadioButtonListener() {
		gg.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) -> {
			RadioButton chk = (RadioButton) t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
			
				try {
					Communicator  c =  CommunicateGenerator.getInstanceOfCommunicatorType(chk.getText());
				} catch (Exception e) {
                     listofEx.add(e);
				}
			
		});

	}


	@Override 
	@FXML
	public void handleSendButton() {
		if (inputValid(txtName.getText(), txtMessage.getText())) {
			this.sendMessage(txtName.getText(), txtMessage.getText());
			txtMessage.setText("");
		}
	}

	private boolean inputValid(String name, String message) {
		if (name.length() == 0) {
			this.showAlert("Please write your name to use the chat", "Fail");
			return false;
		}
		if (message.length() == 0) {
			this.showAlert("Please write a real message.", "Fail");
			return false;
		}
		return true;
	}

	/**
	 * Send current message to all users. CHANGED
	 */
	public void sendMessage(String name, String message) {
		try {
			_communicator.sendChat(name, message);
		} catch (Exception e) {
			this.error(e);
		}
	}

	private void showAlert(String message, String title) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	@Override
	public void update(String message) throws Exception {
		receiveMessage(message);		
	}


}
