package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * A multi-threaded JavaServer that handles incoming connections and data.
 * The server has one thread that listen for incoming connection and one
 * thread for each connected client
 * @author Simon Berntsson
 *
 */
public class Server {
	
	private int port;
	private ServerSocket server;
	private ArrayList<ClientThread> clients;
	
	/**
	 * Server constructor
	 * @param port	Port that server should run on
	 */
	public Server(int port) {
		this.port 	= port;
		clients 	= new ArrayList<ClientThread>();
	}
	
	/**
	 * Start server on port that was given in constructor and start to listen for incoming connections
	 * @return	<b>True</b> if success, else <b>False</b>
	 */
	public boolean startServer() {
		try {
			//Start server on given port
			server = new ServerSocket(this.port);
			
			//Start a Thread that listen for incoming connections
			new Thread(new ListenForIncomingConnections(this, server)).start();
			
			return true;
		}
		catch(IOException e) {
			System.err.println("Server.java: Server couldn't start");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Stop server
	 * @return	<b>True</b> if success, else <b>False</b>
	 */
	public boolean stopServer() {
		try {
			server.close();
			return true;
		}
		catch(IOException e) {
			System.err.println("Server.java: Server couldn't stop");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * When a client connects to the server the ClientThread object
	 * will be added to the servers ArrayList[ClientThread] clients
	 * @param client	ClientThread object for the client that connected
	 */
	public void addClient(ClientThread client) {
		this.clients.add(client);
	}
	
	/**
	 * Gets the status of the server connection
	 * @return	<b>True</b> if server is online, else <b>False</b>
	 */
	public boolean isServerOnline() {
		return !server.isClosed();
	}
}

/**
 * A runnable class that listen for incoming connections
 * @author Simon Berntsson
 *
 */
class ListenForIncomingConnections implements Runnable {
	
	private ServerSocket socketserver;
	private Server server;
	
	public ListenForIncomingConnections(Server server, ServerSocket socketserver) {
		this.socketserver = socketserver;
		this.server = server;
	}
	
	public void run() {
		while(true) {
			try {
				//When a client connect a ClientThread object is created
				ClientThread client = new ClientThread(socketserver.accept());
				server.addClient(client);
			}
			catch(IOException e) {
				//Server shutdown
				break;
			}
		}
	}
}
