package com.mbry.IronMan.BusinessObject;

public class User {
    private String userId;
    private String nickname;
    private String avatar;
    private boolean sex;
    private String mobile;
    private String introduction;

    public User(){}
    public User(
        String userId,
        String nickname,
        String avatar,
        boolean sex,
        String mobile,
        String introduction
        ){
            this.userId = userId;
            this.nickname = nickname;
            this.avatar = avatar;
            this.sex = sex;
            this.mobile = mobile;
            this.introduction = introduction;
    }

    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getNickname(){
        return nickname;
    }
    public void setNickName(String nickname){
        this.nickname = nickname;
    }

    public String getAvatar(){
        return avatar;
    }
    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public boolean getSex(){
        return sex;
    }
    public void setSex(boolean sex){
        this.sex = sex;
    }

    public String getMobile(){
        return this.mobile;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public String getIntroduction(){
        return introduction;
    }
    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
}