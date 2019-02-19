package user;

import java.util.List;


public class User {
	private int userId;
    private String userName,password;
    private  boolean success=false;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	//get¡¢set·½·¨
    public void setUserName(String userName){
    	this.userName=userName;
    }
    public void setPassword(String password){
    	this.password=password;
    }
    public void setUserId(int userId){
    	this.userId=userId;
    }
    public int getUserId() {
    	return userId;
    }
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}   
}
