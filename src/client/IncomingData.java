package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IncomingData implements Runnable {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket client;
	
	public IncomingData(Socket client) {
		this.client = client;
		setupStreams();
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println("Client received: " + in.readObject());
			}
			catch(IOException e) {
				e.printStackTrace();
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
