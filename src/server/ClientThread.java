package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import helppackage.SendableData;

public class ClientThread implements Runnable {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Preferences preference;
	
	public ClientThread(Socket client) {
		preference = Preferences.userRoot().node(Server.class.getName());
		setupObjectStreams(client);
		sendStartupRequestToClient();
	}
	
	/**
	 * Listen for incoming data from client
	 */
	public void run() {
		try {
			System.out.println("Data read from client: " + ((SendableData)in.readObject()).getData());
		} 
		catch (ClassNotFoundException | IOException e) {
			System.err.println("ClientThread.run(): Failed to read inputstream");
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates streams to enable communication with server
	 * @param client	Clients socket
	 */
	private void setupObjectStreams(Socket client) {
		try {
			out = new ObjectOutputStream(client.getOutputStream());
			in	= new ObjectInputStream(client.getInputStream());
		} 
		catch (IOException e) {
			System.err.println("ClientThread: Failed to setup streams");
			e.printStackTrace();
		}
	}
	
	/**
	 * Send data to client
	 * @param data	Data to be sent
	 */
	private void sendToClient(SendableData data) {
		try {
			out.writeObject(data);
			out.flush();
		} catch (IOException e) {
			System.out.println("ClientThread.sendToClient(): Couldnät send data");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Send request of what the server wants from the client.
	 */
	private void sendStartupRequestToClient() {
		SendableData data = new SendableData();
		
		try {
			for(String key : preference.keys()) {
				if(preference.getBoolean(key, false)) {
					data.addCode(Server.sendCodes.getCode(key));
				}
			}
		} catch (BackingStoreException e) {
			System.out.println("ClientThread: Failed to read properties");
			e.printStackTrace();
		}
		
		sendToClient(data);
	}
}
