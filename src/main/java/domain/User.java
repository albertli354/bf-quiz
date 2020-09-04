package domain;

public class User {
	private String userName;
	private int userType;
	private String password;
	
	
	public User(String userName) {
		this.userName = userName;
	}
	
	public User(String userName, String password, int userType) {
		this.userName = userName;
		this.userType = userType;
		this.password = password;
	}

	

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getUserType() {
		return userType;
	}
	
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
