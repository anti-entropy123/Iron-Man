package com.mbry.IronMan.RequestBody.TeamRequestBody;

import java.io.Serializable;

public class JoinTeamRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -647753415980948703L;
    private String teamId;
    private String userId;

    public JoinTeamRequest() {
    }

    public JoinTeamRequest(
            String teamId,
            String userId) {
        this.teamId = teamId;
        this.userId = userId;
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
}