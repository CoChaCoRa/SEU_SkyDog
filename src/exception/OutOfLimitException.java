package exception;

public class OutOfLimitException extends Exception{

	private String uMsg=new String("");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return new String("OutOfLimit");
	}

	public void setMsg(String msg) {
		uMsg=msg;
	}
	
	public String getType() {
		return uMsg;
	}
}