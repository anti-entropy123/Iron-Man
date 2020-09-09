package com.mbry.IronMan.BusinessObject.Application;

public class CardApplication extends Application{
    private String cardId;
    
    public CardApplication(){}
    public CardApplication(
        String applicationId,
        String applicantId,
        boolean status,
        String date,
        String cardId
    ){
        super(applicationId, applicantId, cardId, status, date);
        this.cardId = cardId; 
    }
}