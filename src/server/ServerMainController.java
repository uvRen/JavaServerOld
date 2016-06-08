package server;

import java.io.IOException;
import java.util.prefs.Preferences;

import helppackage.ClientUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ServerMainController {
	
	@FXML private MenuItem 				start_stop_server;
	@FXML private Circle 				serverStatusCircle;
	@FXML private BorderPane			borderpane;
	
	private Server 						server = null;
	private Preferences 				preference;
	
	private ObservableList<ClientUser> 	userInfo;
	private TreeView<String> 		treeviewUsers;
	private TreeItem<String> 		rootNode;
	
	public ServerMainController() {
		preference 		= Preferences.userRoot().node(Server.class.getName());
		userInfo 		= FXCollections.observableArrayList();
		
	}
	
	
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
		PreferenceServerController controller;
		
		FXMLLoader loader 	= openWindow("PreferenceServerScene.fxml", 600, 400);
		controller 			= (PreferenceServerController)loader.getController();
		
		controller.initTreeViewSettings();
	}
	
	/**
	 * When user clicks 'About' a window with settings should appear
	 */
	public void showAboutWindow() {
		openWindow("AboutServerScene.fxml", 200, 100);
	}
	
	/**
	 * Add a new user to the listview
	 * @param user	User to be added
	 */
	@SuppressWarnings("unchecked")
	public void addUserToTreeview(ClientUser user) {
		this.userInfo.add(user);
		
		TreeItem<String> newClient = new TreeItem<String>();
		
		switch(preference.get("showclientinfo", "username")) {
    	case "Username":
    		newClient.setValue((user.getUsername().getValue()));
    		break;
    	case "Computername":
    		newClient.setValue((user.getComputername().getValue()));
    		break;
    	case "IP address":
    		newClient.setValue((user.getIpaddress().getValue()));
    		break;
    	}
		
		TreeItem<String> username 		= new TreeItem<String>("username: " + user.getUsername().getValue());
		TreeItem<String> computername 	= new TreeItem<String>("computername: " + user.getComputername().getValue());
		TreeItem<String> ipaddress 		= new TreeItem<String>("ipaddress: " + user.getIpaddress().getValue());
		
		newClient.getChildren().addAll(username, computername, ipaddress);
		
		rootNode.getChildren().add(newClient);
	}
	
	/**
	 * Opens a new window with help with a FXML file
	 * @param fxmlPath	FXML filename
	 * @param width		Width of window
	 * @param height	Height of window
	 */
	private FXMLLoader openWindow(String fxmlPath, double width, double height) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = (Parent) loader.load();
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root, width, height));
			stage.show();
			
			return loader;
		}
		catch(IOException e) {
			System.err.println("ServerMainController: Failed to open window (" + fxmlPath + ")");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Start up the server at given port
	 */
	private boolean startServer() {
		if(server == null) {
			this.server = new Server();
			//setupListView();
			setupTreeView();
			return server.startServer();
		}
		else {
			return server.startServer();
		}
	}
	
	/**
	 * Initialize the ListView
	 */
	/*
	private void setupListView() {
		//Add custom CellFactory to print ClientUser object in the ListView
		listviewUsers.setCellFactory(new Callback<ListView<ClientUser>, ListCell<ClientUser>>(){
			 
            @Override
            public ListCell<ClientUser> call(ListView<ClientUser> p) {

                ListCell<ClientUser> cell = new ListCell<ClientUser>(){
                	
                    @Override
                    protected void updateItem(ClientUser t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                        	switch(preference.get("showclientinfo", "username")) {
                        	case "Username":
                        		setText(t.getUsername().getValue());
                        		break;
                        	case "Computername":
                        		setText(t.getComputername().getValue());
                        		break;
                        	case "IP address":
                        		setText(t.getIpaddress().getValue());
                        		break;
                        	}
                            
                        }
                    }
                };
                return cell;
            }
        });
		
		borderpane.setLeft(listviewUsers);
	}
	*/
	
	private void setupTreeView() {
		rootNode = new TreeItem<String>();
		
		treeviewUsers = new TreeView<String>(rootNode);
		treeviewUsers.setShowRoot(false);
		borderpane.setLeft(treeviewUsers);
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
