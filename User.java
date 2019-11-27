
public class User {
	
	private int userID;
	private String userName;
	private String password;
	private int authority;
	
	public User() {}
	
	public User(int userID, String userName, String password) {
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		authority = 2;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public boolean login(String name, String passwd) {
		boolean isSuccess = false;
		return isSuccess;
	}	
}
