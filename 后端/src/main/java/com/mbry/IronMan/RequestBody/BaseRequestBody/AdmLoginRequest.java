package com.mbry.IronMan.RequestBody.BaseRequestBody;

import java.io.Serializable;

public class AdmLoginRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2801122348455316547L;
    private String account;
    private String password;

    public AdmLoginRequest() {
    }

    public AdmLoginRequest(
            String account,
            String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
   
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}