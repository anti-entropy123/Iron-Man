package com.mbry.IronMan.RequestBody.DetailRequestBody;

import java.io.Serializable;

public class ProcessApplyRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -9164712782327746807L;
    private String applyId;

    public ProcessApplyRequest() {
    }

    public ProcessApplyRequest(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyId() {
        return this.applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }
}