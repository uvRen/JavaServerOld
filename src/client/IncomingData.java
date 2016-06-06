package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import helppackage.SendableData;

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
				handle(in.readObject());
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
	
	private void sendToServer(SendableData data) {
		try {
			out.writeObject(data);
			out.flush();
		} catch (IOException e) {
			System.err.println("IncomingData.sendToServer(): Failed to send data to server");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Handle incoming data from server
	 * @param o	Incoming object
	 */
	private void handle(Object o) {
		SendableData data = (SendableData)o;
		
		for(Integer code : data.getCode()) {
			handleSendcode(data, code);
		}
		sendToServer(data);
	}
	
	private void handleSendcode(SendableData data, int code) {
		switch(code) {
		case 1000:
			break;
		case 1002:
			data.addData("Simon Berntsson");
			break;
		case 1004:
			break;
		}
	}
}
