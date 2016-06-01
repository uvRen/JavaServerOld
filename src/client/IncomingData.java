package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IncomingData implements Runnable {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket client;
	private ClientMainController clientMainController;
	
	public IncomingData(Socket client) {
		this.client = client;
		clientMainController = Main.getClientMainController();
		setupStreams();
	}
	
	public void run() {
		while(true) {
			try {
				//System.out.println("Client received: " + in.readObject());
				clientMainController.addTextToTextArea((String)in.readObject());
			}
			catch(IOException e) {
				//Lost connection to server
				break;
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Creates streams to communicate with server
	 */
	private void setupStreams() {
		try {
			in = new ObjectInputStream(client.getInputStream());
			out = new ObjectOutputStream(client.getOutputStream());
		}
		catch(IOException e) {
			System.err.println("IncomingData: Couldn't create stream");
			e.printStackTrace();
		}
	}
}
