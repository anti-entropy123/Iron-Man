package com.mbry.IronMan.RequestBody.DetailRequestBody;

import java.io.Serializable;

public class OrderTeamApplyRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -612243529517260295L;
    private String teamId;
    private String userId;
    private String result;
    private String message;

    public OrderTeamApplyRequest() {
    }

    public OrderTeamApplyRequest(
            String teamId,
            String userId,
            String result,
            String message) {
        this.teamId = teamId;
        this.userId = userId;
        this.result = result;
        this.message = message;
    }

    public String getTeamId() {
        return this.teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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