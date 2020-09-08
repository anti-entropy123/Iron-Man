package com.mbry.IronMan.RequestBody.TeamRequestBody;

import java.io.Serializable;

public class CreateTeamRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -647753499380948703L;
    private String cardId;
    private String userId;
    private int maxNum;

    public CreateTeamRequest() {
    }

    public CreateTeamRequest(
            String cardId,
            String userId,
            int maxNum) {
        this.cardId = cardId;
        this.userId = userId;
        this.maxNum = maxNum;
    }

    public String getCardId() {
        return this.cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMaxNum() {
        return this.maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }
}