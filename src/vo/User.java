package vo;

public class User {
	private String username="unknown";
	private String password=null;
	private String authentification=null;
	
	public void setUsername(String username){
		this.username=username;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public void setAuthentification(String authentification){
		this.authentification=authentification;
	}
	
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public String getAuthentification(){
		return authentification;
	}
}