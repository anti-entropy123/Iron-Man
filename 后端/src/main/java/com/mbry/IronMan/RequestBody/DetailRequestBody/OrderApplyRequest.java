package com.mbry.IronMan.RequestBody.DetailRequestBody;

import java.io.Serializable;

public class OrderApplyRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5456110924130660596L;
    private String cardId;
    private String userId;

    public OrderApplyRequest() {
    }

    public OrderApplyRequest(
            String cardId,
            String userId) {
        this.cardId = cardId;
        this.userId = userId;
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

}