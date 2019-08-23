package vo;

public class Parameter {
	private String Key = null;
	private int Value;
	private String Declare = null;
	
	public void setKey(String key) {
		this.Key = key;
	}
	public void setValue(int value) {
		this.Value = value;
	}
	public void setDeclare(String declare) {
		this.Declare = declare;
	}
	
	public String getKey() {
		return Key;
	}
	public int getValue() {
		return Value;
	}
	public String getDeclare() {
		return Declare;
	}
}
