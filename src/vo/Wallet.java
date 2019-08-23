package vo;

public class Wallet {
	private String username;
	private String account;
	private String email;
	private String password;
	private Double money;
	
	public void setUserName(String username) {
		this.username = username;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getUserName() {
		return username;
	}
	public String getAccount() {
		return account;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public Double getMoney() {
		return money;
	}
}
