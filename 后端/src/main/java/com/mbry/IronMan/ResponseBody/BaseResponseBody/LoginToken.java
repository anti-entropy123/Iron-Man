package com.mbry.IronMan.ResponseBody.BaseResponseBody;

import java.io.Serializable;

public class LoginToken implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2922693761785960371L;
    private String token;
    private String result;
    private String message;

    public LoginToken() {
    }

    public LoginToken(
            String token, 
            String result,
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

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}