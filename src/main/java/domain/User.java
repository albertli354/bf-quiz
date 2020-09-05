package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userTable")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userID")
	private int userID;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "userType")
	private int userType;
	
	@Column(name = "pass_word")
	private String password;
	
	@Column(name = "isActive")
	private int isActive;

	
	
	public User() {

	}

	public User(String userName) {
		this.userName = userName;
	}
	
	public User(String userName, String password, int userType) {
		this.userName = userName;
		this.userType = userType;
		this.password = password;
	}

	

	public int getUserID() {
		return userID;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", userType=" + userType + ", isActive=" + isActive
				+ "]";
	}
	
	
	
}
