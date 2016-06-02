package server;

import java.util.Properties;
import java.util.prefs.Preferences;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class PreferenceServerController {
	
	@FXML private BorderPane borderpane;
	@FXML private HBox contentContainer;
	@FXML private GridPane optionContainer;
	
	private TextField serverNameTextField,
					  serverPortTextField,
					  serverConnectionsTextField;
	
	private Preferences preference = Preferences.userRoot().node(Server.class.getName());
	
	/**
	 * Initialize the TreeView containing all options and by default show all
	 * options for the group 'General'
	 */
	@SuppressWarnings("unchecked")
	public void initTreeViewSettings() {
		TreeItem<String> root = new TreeItem<String>("DummyNode");
		TreeItem<String> general = new TreeItem<String>("General");
		TreeItem<String> client = new TreeItem<String>("Client");
		
		root.getChildren().addAll(general, client);
		
		TreeView<String> tree = new TreeView<String>(root);
		tree.setShowRoot(false);
		//Remove the TreeView that is added from SceneBuilder and replace it with a new one
		contentContainer.getChildren().remove(0);
		contentContainer.getChildren().add(0, tree);
		
		createOptionsForGeneral();
	}
	
	/**
	 * Add all options connected to 'General' to the GridPane
	 */
	private void createOptionsForGeneral() {
		Label serverNameLabel = new Label("Servername");
		GridPane.setConstraints(serverNameLabel, 0, 0);
		
		serverNameTextField = new TextField();
		serverNameTextField.setText(preference.get("servername", "server1"));
		GridPane.setConstraints(serverNameTextField, 1, 0);
		
		Label serverPortLabel = new Label("Port");
		GridPane.setConstraints(serverPortLabel, 0, 1);
		
		serverPortTextField = new TextField();
		serverPortTextField.setText(Integer.toString(preference.getInt("port", 9999)));
		GridPane.setConstraints(serverPortTextField, 1, 1);
		
		Label serverConnectionsLabel = new Label("Connections");
		GridPane.setConstraints(serverConnectionsLabel, 0, 2);
		
		serverConnectionsTextField = new TextField();
		serverConnectionsTextField.setText(preference.get("connections", "-1"));
		GridPane.setConstraints(serverConnectionsTextField, 1, 2);
		
		Button saveButton = new Button("Save");
		saveButton.setOnMouseClicked(e -> 
		{
			saveOptions();
		});
		GridPane.setConstraints(saveButton, 0, 14);
		
		optionContainer.getChildren().addAll(serverNameLabel, 
											 serverNameTextField,
											 saveButton,
											 serverPortLabel,
											 serverPortTextField,
											 serverConnectionsLabel,
											 serverConnectionsTextField);
	}
	
	/**
	 * Save all options that is collected from the 'Preference' window
	 */
	private void saveOptions() {
		preference.put("servername", 	serverNameTextField.getText());
		preference.putInt("port", 		Integer.parseInt(serverPortTextField.getText()));
		preference.put("connections", 	serverConnectionsTextField.getText());
	}
}
