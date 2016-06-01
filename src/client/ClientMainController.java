package client;

import javafx.fxml.FXML;
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
	 * Adds text to the TextArea
	 * @param text	Text to add
	 */
	public void addTextToTextArea(String text) {
		textArea.setText(text + '\n' + textArea.getText());
	}
}
