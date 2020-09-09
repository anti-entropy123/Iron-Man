package com.mbry.IronMan.RequestBody.BaseRequestBody;

import java.io.Serializable;

public class MobileAndCheckCode implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 8155855109148976237L;
    private String checkcode;
    private String mobileNumber;

    public MobileAndCheckCode() {
    }

    public MobileAndCheckCode(
            String checkcode,
            String mobileNumber) {
        this.checkcode = checkcode;
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCheckcode() {
        return this.checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
}