package com.mbry.IronMan.RequestBody.DetailRequestBody;

import java.io.Serializable;

public class OrderApplyRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5456110924130660596L;
    private String cardId;
    private String userId;
    private String result;
    private String message;

    public OrderApplyRequest() {
    }

    public OrderApplyRequest(
            String cardId,
            String userId,
            String result,
            String message) {
        this.cardId = cardId;
        this.userId = userId;
        this.result = result;
        this.message = message;
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