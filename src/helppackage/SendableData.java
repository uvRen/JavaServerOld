package helppackage;

import java.io.Serializable;
import java.util.ArrayList;

public class SendableData implements Serializable {

	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Integer> code;
	private ArrayList<Object> data;
	
	public SendableData() {
		code = new ArrayList<Integer>();
		data = new ArrayList<Object>();
	}

	public ArrayList<Integer> getCode() {
		return code;
	}

	public void setCode(ArrayList<Integer> code) {
		this.code = code;
	}

	public ArrayList<Object> getData() {
		return data;
	}

	public void setData(ArrayList<Object> data) {
		this.data = data;
	}
	
}
