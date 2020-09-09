package com.mbry.IronMan.ResponseBody.PersonResponseBody;

import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class GetInfoResponse extends DefaultResponse {

     /**
    *
    */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String name;
    private Boolean sex;
    private String mobile;
    private String introduce;
    private String avatarUrl;
    
    public GetInfoResponse() {
    }

    public GetInfoResponse(
            String name,
            Boolean sex,
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

    public Boolean getSex() {
        return this.sex;
    }

    public void setSex(Boolean sex) {
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
