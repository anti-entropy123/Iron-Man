package com.mbry.IronMan.ResponseBody.AdmResponseBody;

import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class GetUserResponse extends DefaultResponse {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public class Data {

        private String userId;
        private String nickname;
        private String avatar;
        private Boolean sex;
        private String introduction;
        private String mobileNumber;
        
        public Data() {}
        public Data(
        	String userId,
            String nickname,
            String avatar,
            Boolean sex,
            String introduction,
            String mobileNumber) {
        	this.setUserId(userId);
        	this.setNickname(nickname);
        	this.setAvatar(avatar);
        	this.setSex(sex);
        	this.setIntroduction(introduction);
        	this.setMobileNumber(mobileNumber);
        }

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
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

		public String getIntroduction() {
			return introduction;
		}

		public void setIntroduction(String introduction) {
			this.introduction = introduction;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

    }

    private Data[] data;
    private int totalPage;
    
    public GetUserResponse() {}
    public GetUserResponse(
    		Data[] data,
    		int totalPage,
    		int result,
    		String message) {
    	super(result, message);
    	this.setData(data);
    	this.setTotalPage(totalPage);
    }

	public Data[] getData() {
		return data;
	}

	public void setData(Data[] data) {
		this.data = data;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
    
    
    
}
