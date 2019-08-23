package exception;

public class RecordNotFoundException extends Exception{

	private String uMsg=new String("");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return new String("RecordNotFound");
	}

	public void setMsg(String msg) {
		uMsg=msg;
	}
	
	public String getType() {
		return uMsg;
	}
}