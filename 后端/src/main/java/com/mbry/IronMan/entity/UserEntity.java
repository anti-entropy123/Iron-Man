package com.mbry.IronMan.entity;

public class UserEntity {

	private String userId;
	private String nickName;
	private String avatar;
	private Boolean sex;
	private String mobile;
	private String introduction;
	
	public UserEntity() {}
	public UserEntity(
			String userId,
			String nickName,
			String avatar,
			Boolean sex,
			String mobile,
			String introduction) {
		this.setUserId(userId);
		this.setNickName(nickName);
		this.setAvatar(avatar);
		this.setSex(sex);
		this.setMobile(mobile);
		this.setIntroduction(introduction);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}
