package com.mbry.IronMan.BusinessObject.Application;

public class CardApplication extends Application{
	
    private String cardId;
    
    public CardApplication(){}
    public CardApplication(
        String applicationId,
        String applicantId,
        Boolean status,
        String date,
        String cardId
    ){
        super(applicationId, applicantId, cardId, status, date);
        this.setCardId(cardId); 
    }
    
	public String getCardId() {
		return cardId;
	}
	
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
}