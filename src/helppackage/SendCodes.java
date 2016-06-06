package helppackage;

import java.util.ArrayList;

public class SendCodes {
	private ArrayList<Tuple> tuple;
	
	public SendCodes(String name, int code) {
		tuple = new ArrayList<Tuple>();
	}
	
	/**
	 * Add a new sendcode to the list
	 * @param name	Name of code
	 * @param code	Integer of code
	 */
	public void addSendCode(String name, int code) {
		tuple.add(new Tuple(name, code));
	}
	
	/**
	 * Get the sendcode of given option
	 * @param name	Name of option
	 * @return		SendCode of option
	 */
	public int getCode(String name) {
		for(Tuple t : tuple) {
			if(t.getName().equals(name))
				return t.getCode();
		}
		return -1;
	}
}

class Tuple {
	
	private String 	name;
	private int		code;
	
	public Tuple(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
