package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class ClientMainController {
	@FXML private TextArea textArea;
	
	private Client client = null;
	
	/**
	 * Establish a connection to the server
	 * @return	<b>True</b> if success, else <b>False</b>
	 */
	public boolean connectToServer() {
		if(client == null) {
			client = new Client(9999);
			return client.connectToServer();
		}
		else {
			if(!client.isClientConnected())
				return client.connectToServer();
			return true;
		}
	}
	
	/**
	 * Disconnect client from server 
	 * @return	<b>True</b> if success, else <b>False</b>
	 */
	public boolean disconnectFromServer() {
		return client.disconnectFromServer();
	}
	
	public void showForceDisconnectionWindow() {
		Platform.runLater(new Runnable() {
			public void run() {
				client.showAlertWarning("Disconnect", "You have been disconnected from server", "");
			}
		});
		
	}
	
	/**
	 * Called when user press 'Connect to server' or 'Disconnect from server'.
	 * Client should then connect or disconnect from the server
	 * @param event	ActionEvent
	 */
	@FXML protected void menuItemConnectHandler(ActionEvent event) {
		MenuItem itemPressed = (MenuItem)event.getSource();
		System.out.println(itemPressed.getText());
		
		switch(itemPressed.getText()) {
		case "Connect to server":
			if(connectToServer()) { itemPressed.setText("Disconnect from server"); }
			break;
		case "Disconnect from server":
			if(disconnectFromServer()) { itemPressed.setText("Connect to server"); }
			break;
		}
	}
	
	/**
	 * Adds text to the TextArea
	 * @param text	Text to add
	 */
	public void addTextToTextArea(String text) {
		textArea.setText(text + '\n' + textArea.getText());
	}
}
