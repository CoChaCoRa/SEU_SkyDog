package vo;

public class BVO {
	private String username;
	private String name;
	private String email;
	private String phone;
	
	public void setUserName(String username) {
		this.username = username;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserName() {
		return username;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
}
