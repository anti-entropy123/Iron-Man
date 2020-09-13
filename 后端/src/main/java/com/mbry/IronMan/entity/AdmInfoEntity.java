package com.mbry.IronMan.entity;

public class AdmInfoEntity {

    private String admId;
    private String password;

    public AdmInfoEntity() {}
    public AdmInfoEntity(
        String admId,
        String password) {
        this.setAdmId(admId);
        this.setPassword(password);
    }

    public String getAdmId() {
        return admId;
    }

    public void setAdmId(String admId) {
        this.admId = admId;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

}