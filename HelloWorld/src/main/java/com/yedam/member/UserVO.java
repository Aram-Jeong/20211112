package com.yedam.member;

public class UserVO {
	private String userId;
	private String userName;
	private String email;
	private String phone;
	private String birth;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userName=" + userName + ", email=" + email + ", phone=" + phone
				+ ", birth=" + birth + "]";
	}
	
	

}
