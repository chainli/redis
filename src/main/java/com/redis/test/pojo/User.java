package com.redis.test.pojo;

public class User {
	private Long id;
	private String userName;
	private String note;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userNmae) {
		this.userName = userNmae;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
