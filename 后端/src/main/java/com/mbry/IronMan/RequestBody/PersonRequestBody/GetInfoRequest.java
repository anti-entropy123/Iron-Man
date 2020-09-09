package com.mbry.IronMan.RequestBody.PersonRequestBody;

import java.io.Serializable;

public class GetInfoRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7311287310288819994L;
    private String name;
    private String sex;
    private String mobile;
    private String introduce;
    private String avatarUrl;
    
    public GetInfoRequest() {
    }

    public GetInfoRequest(
            String name,
            String sex,
            String mobile,
            String introduce,
            String avatarUrl) {
        this.name = name;
        this.sex = sex;
        this.mobile = mobile;
        this.introduce = introduce;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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