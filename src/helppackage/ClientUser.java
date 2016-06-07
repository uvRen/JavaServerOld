package helppackage;

import javafx.beans.property.SimpleStringProperty;

public class ClientUser {
	private SimpleStringProperty username;
	private SimpleStringProperty computername;
	private SimpleStringProperty ipaddress;
	
	public ClientUser(String username) {
		this.username 		= new SimpleStringProperty(username);
		this.computername 	= new SimpleStringProperty("");
		this.ipaddress 		= new SimpleStringProperty("");
	}
	
	public ClientUser() {
		this.username 		= new SimpleStringProperty("");
		this.computername 	= new SimpleStringProperty("");
		this.ipaddress 		= new SimpleStringProperty("");
	}

	public SimpleStringProperty getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = new SimpleStringProperty(username);
	}

	public SimpleStringProperty getComputername() {
		return computername;
	}

	public void setComputername(String computername) {
		this.computername = new SimpleStringProperty(computername);
	}

	public SimpleStringProperty getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = new SimpleStringProperty(ipaddress);
	}
}
