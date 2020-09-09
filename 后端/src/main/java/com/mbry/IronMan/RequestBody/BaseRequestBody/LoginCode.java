package com.mbry.IronMan.RequestBody.BaseRequestBody;

import java.io.Serializable;

public class LoginCode implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -4914050863656550093L;
    private String code;

    public LoginCode() {
    }

    public LoginCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}