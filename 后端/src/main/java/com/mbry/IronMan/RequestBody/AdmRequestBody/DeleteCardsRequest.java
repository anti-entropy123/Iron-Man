package com.mbry.IronMan.RequestBody.AdmRequestBody;

import java.io.Serializable;

public class DeleteCardsRequest implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    
	private String[] cardIds;

    public DeleteCardsRequest() {}
    public DeleteCardsRequest(String[] cardIds) {
        this.setCardIds(cardIds);
    }

    public String[] getCardIds() {
        return cardIds;
    }

    public void setCardIds(String[] cardIds) {
        this.cardIds = cardIds;
    }
    
}
