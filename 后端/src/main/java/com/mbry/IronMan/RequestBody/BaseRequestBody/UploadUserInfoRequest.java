package com.mbry.IronMan.RequestBody.BaseRequestBody;

import java.io.Serializable;

public class UploadUserInfoRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6833239592446998611L;
    private String nickName;
    private Boolean gender;
    private String avatarUrl;

    public UploadUserInfoRequest() {
    }

    public UploadUserInfoRequest(
            String nickName,
            Boolean gender,
            String avatarUrl) {
        this.nickName = nickName;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getGender() {
        return this.gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}