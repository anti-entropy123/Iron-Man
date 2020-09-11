package com.mbry.IronMan.ResponseBody.BaseResponseBody;

import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class LoginToken extends DefaultResponse {
    /**
     *
     */
    private static final long serialVersionUID = -2922693761785960371L;
    private String token;
    private Boolean firstTime;

    public LoginToken() {
    }

    public LoginToken(
            String token, 
            boolean firstTime,
            int result,
            String message) {
        super(result, message);
        this.token = token;
        this.firstTime = firstTime;
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
}