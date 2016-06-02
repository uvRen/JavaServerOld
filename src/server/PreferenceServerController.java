package server;

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
	
	/**
	 * Initialize the TreeView containing all options and by default show all
	 * options for the group 'General'
	 */
	public void initTreeViewSettings() {
		TreeItem<String> root = new TreeItem<String>("General");
		
		TreeView<String> tree = new TreeView<String>(root);
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
		
		TextField serverNameTextField = new TextField();
		GridPane.setConstraints(serverNameTextField, 1, 0);
		
		Label serverPortLabel = new Label("Port");
		GridPane.setConstraints(serverPortLabel, 0, 1);
		
		TextField serverPortTextField = new TextField();
		GridPane.setConstraints(serverPortTextField, 1, 1);
		
		Button saveButton = new Button("Save");
		GridPane.setConstraints(saveButton, 0, 14);
		
		optionContainer.getChildren().addAll(serverNameLabel, 
											 serverNameTextField,
											 saveButton,
											 serverPortLabel,
											 serverPortTextField);
	}
}
