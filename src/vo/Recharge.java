package vo;

public class Recharge {
	private String Username;
	private String Type;
	private int Amount;
	private String Time;
	private String Statement;
	
	public void setUsername(String username) {
		this.Username = username;
	}
	public void setType(String type) {
		this.Type = type;
	}
	public void setAmount(int amount) {
		this.Amount = amount;
	}
	public void setTime(String time) {
		this.Time = time;
	}
	public void setStatement(String state) {
		this.Statement = state;
	}
	
	public String getUsername() {
		return Username;
	}
	public String getType() {
		return Type;
	}
	public int getAmount() {
		return Amount;
	}
	public String getTime() {
		return Time;
	}
	public String getState() {
		return Statement;
	}
}
