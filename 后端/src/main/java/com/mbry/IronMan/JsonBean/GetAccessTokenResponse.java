package com.mbry.IronMan.JsonBean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class GetAccessTokenResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7657208404221718591L;
    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "expires_in")
    private int expiresIn;

    public GetAccessTokenResponse() {
    }

    public GetAccessTokenResponse(
            String accessToken,
            int expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}