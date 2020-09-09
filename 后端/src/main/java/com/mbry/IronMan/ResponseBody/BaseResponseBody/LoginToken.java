package com.mbry.IronMan.ResponseBody.BaseResponseBody;

import java.io.Serializable;

public class LoginToken implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2922693761785960371L;
    private String token;
    private int result;
    private String message;

    public LoginToken() {
    }

    public LoginToken(
            String token, 
            int result,
            String message) {
        this.token = token;
        this.result = result;
        this.message = message;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getResult() {
        return this.result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}