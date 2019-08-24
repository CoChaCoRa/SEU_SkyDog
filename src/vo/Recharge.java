package vo;

import java.sql.Date;

public class Recharge {
	private String Username;
	private String Type;
	private Double Amount;
	private Date Time;
	private String state;
	
	public void setUsername(String username) {
		this.Username = username;
	}
	public void setType(String type) {
		this.Type = type;
	}
	public void setAmount(Double amount) {
		this.Amount = amount;
	}
	public void setTime(Date time) {
		this.Time = time;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getUsername() {
		return Username;
	}
	public String getType() {
		return Type;
	}
	public Double getAmount() {
		return Amount;
	}
	public Date getTime() {
		return Time;
	}
	public String getState() {
		return state;
	}
}
