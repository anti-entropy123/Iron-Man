package com.mbry.IronMan.RequestBody.BaseRequestBody;

import java.io.Serializable;

public class MobileAndCheckCode implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8155855109148976237L;
    private String mobile;
    private String result;
    private String message;

    public MobileAndCheckCode() {
    }

    public MobileAndCheckCode(
            String mobile,
            String result,
            String message) {
        this.mobile = mobile;
        this.result = result;
        this.message = message;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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