package com.mbry.IronMan.ResponseBody.BaseResponseBody;

import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class LoginToken extends DefaultResponse {
    /**
     *
     */
    private static final long serialVersionUID = -2922693761785960371L;
    private String token;
    private Boolean firstTime;
    private String userId;

    public LoginToken() {
    }

    public LoginToken(
            String token, 
            boolean firstTime,
            int result,
            String message,
            String userId) {
        super(result, message);
        this.token = token;
        this.firstTime = firstTime;
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getFirstTime() {
        return this.firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}