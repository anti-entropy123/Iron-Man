package com.mbry.IronMan.BusinessObject.Card;

// 该类具有五个子类
// rent 出租
// sell 出售
// Ask for rent 求租
// Ask for sale 求售
// Find a roommate 求室友
public class Card {
    private String cardId;
    private String userId;
    private boolean status;
    private String date;
    private String title;
    private String introduction;
    private String location;
    private boolean unitType;
    private String[] images;

    public Card(){}
    // super(cardId, userId, status, date, title, introduction, location, unitType);
    public Card(
        String cardId,
        String userId,
        boolean status,
        String date,
        String title,
        String introduction,
        String location,
        boolean unitType,
        String[] images
        ){
            this.cardId = cardId;
            this.userId = userId;
            this.status = status;
            this.date = date;
            this.title = title;
            this.introduction = introduction;
            this.location = location;
            this.unitType = unitType;
            this.images = images;
    }
}