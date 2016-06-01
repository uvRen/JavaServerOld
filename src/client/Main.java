package client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainClientScene.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(IOException e) {
			
		}
	}
	
	public static void main(String args[]) {
		launch(args);
		
		Client client = new Client(9999);
		client.connectToServer();
		System.out.println("Client main connected to server");
	}
}
