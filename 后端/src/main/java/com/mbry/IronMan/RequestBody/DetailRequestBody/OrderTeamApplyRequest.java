package com.mbry.IronMan.RequestBody.DetailRequestBody;

import java.io.Serializable;

public class OrderTeamApplyRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -612243529517260295L;
    private String teamId;
    private String cardId;
    // private int result;
    // private String message;

    public OrderTeamApplyRequest() {
    }

    public OrderTeamApplyRequest(
            String teamId,
            String cardId) {
        this.teamId = teamId;
        this.cardId = cardId;
        // this.result = result;
        // this.message = message;
    }

    public String getTeamId() {
        return this.teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getCardId() {
        return this.cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    // public int getResult() {
    //     return this.result;
    // }

    // public void setResult(int result) {
    //     this.result = result;
    // }

    // public String getMessage() {
    //     return this.message;
    // }

    // public void setMessage(String message) {
    //     this.message = message;
    // }
}