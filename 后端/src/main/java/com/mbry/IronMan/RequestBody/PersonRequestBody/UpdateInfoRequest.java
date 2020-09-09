package com.mbry.IronMan.RequestBody.PersonRequestBody;

import java.io.Serializable;

public class UpdateInfoRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5664774176283503006L;
    private String userId;
    private String name;
    private Boolean sex;
    private String introduce;
    private String avatarUrl;

    public UpdateInfoRequest() {
    }

    public UpdateInfoRequest(
            String userId,
            String name,
            Boolean sex,
            String introduce,
            String avatarUrl) {
        this.userId = userId;
        this.name = name;
        this.sex = sex;
        this.introduce = introduce;
        this.avatarUrl = avatarUrl;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return this.sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}