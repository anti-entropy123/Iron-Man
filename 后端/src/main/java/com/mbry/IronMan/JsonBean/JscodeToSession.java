package com.mbry.IronMan.JsonBean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class JscodeToSession implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4704342103853655924L;
    
    @JSONField(name = "open_id")
    private String openId;
    
    @JSONField(name = "session_key")
    private String sessionKey;

    public JscodeToSession() {
    }

    public JscodeToSession(
            String openId,
            String sessionKey) {
        this.openId = openId;
        this.sessionKey = sessionKey;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}