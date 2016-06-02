package server;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ServerMainController {
	
	@FXML private MenuItem start_stop_server;
	@FXML private Circle serverStatusCircle;
	
	private Server server = null;
	
	/**
	 * Function gets called when user press the 'Start server' or 'Stop server' menuItem
	 * and determines if the server should start or stop. 
	 */
	public void startStopServer() {
		if(start_stop_server.getText().equals("Start server")) {
			if(startServer()) {
				changeTextOnMenuItem(start_stop_server, "Stop server");
				changeColorOnServerStatus(Color.GREEN);
			}
		}
		else if(start_stop_server.getText().equals("Stop server")) {
			if(stopServer()) {
				changeTextOnMenuItem(start_stop_server, "Start server");
				changeColorOnServerStatus(Color.RED);
			}
		}
	}
	
	/**
	 * When user clicks 'Preference' a window with settings should appear
	 */
	public void showPreferenceWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PreferenceServerScene.fxml"));
			Parent root = (Parent) loader.load();
			
			PreferenceServerController settingController = loader.getController();
			settingController.initTreeViewSettings();
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root, 600, 400));
			stage.show();
		}
		catch(IOException e) {
			System.err.println("SererMainController: Failed to open settings window");
			e.printStackTrace();
		}
	}
	
	/**
	 * Start up the server at given port
	 */
	private boolean startServer() {
		if(server == null) {
			return server.startServer();
		}
		else {
			return server.startServer();
		}
	}
	
	/**
	 * Stop the server
	 */
	private boolean stopServer() {
		return server.stopServer();
	}
	
	/**
	 * Change the text on a MenuItem
	 * @param item		MenuItem that should be changed
	 * @param newText	The new text to the MenuItem
	 */
	private void changeTextOnMenuItem(MenuItem item, String newText) {
		item.setText(newText);
	}
	
	/**
	 * Change color on serverStatusCircle
	 * @param color	The new color
	 */
	private void changeColorOnServerStatus(Color color) {
		serverStatusCircle.setFill(color);
	}
	
	
}
