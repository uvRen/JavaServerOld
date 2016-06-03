package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.prefs.Preferences;

public class ClientThread {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Preferences preference;
	
	public ClientThread(Socket client) {
		preference = Preferences.userRoot().node(Server.class.getName());
		setupObjectStreams(client);
		sendStartupRequestToClient();
	}
	
	/**
	 * Creates streams to enable communication with server
	 * @param client	Clients socket
	 */
	private void setupObjectStreams(Socket client) {
		try {
			out = new ObjectOutputStream(client.getOutputStream());
			out.writeObject("Who the fuck are you?!");
			out.flush();
			System.out.println("Server sent message");
		} 
		catch (IOException e) {
			System.err.println("ClientThread: Failed to setup streams");
			e.printStackTrace();
		}
	}
	
	private void sendStartupRequestToClient() {
		
	}
}
