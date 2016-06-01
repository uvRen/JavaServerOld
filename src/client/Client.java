package client;

import java.io.IOException;
import java.net.Socket;

public class Client {
	
	private int port;
	private Socket client;
	
	public Client(int port) {
		this.port = port;
	}
	
	/**
	 * Establish a connection to the server at given port from constructor
	 * @return	<b>True</b> if success, else <b>False</b>
	 */
	public boolean connectToServer() {
		try {
			client = new Socket("localhost", port);
			//Start a Thread that listen for incoming data
			new Thread(new IncomingData(client)).start();
			return true;
		}
		catch(IOException e) {
			System.err.println("Client: Couldn't connect to server");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Disconnect from server
	 * @return	<b>True</b> if success, else <b>False</b>
	 */
	public boolean disconnectFromServer() {
		try {
			client.close();
			return true;
		}
		catch(IOException e) {
			System.err.println("Client: Couldn't disconnect from server");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Gets the status of the connection to server
	 * @return	<b>True</b> if client is connected, else <b>False</b>
	 */
	public boolean isClientConnected() {
		return !client.isClosed();
	}
	
	
}
