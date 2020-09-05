package com.mbry.IronMan.BusinessObject.Card;

public class RentCard extends Card{
    private String requirement; // 对租客要求
    private int unionNum;
    private double price;
    private double Square;

    public RentCard(){}
    public RentCard(
        String cardId,
        String userId,
        boolean status,
        String date,
        String title,
        String introduction,
        String location,
        boolean unitType,
        String requirement,
        int unionNum,
        double price,
        double square,
        String[] images
        ){
            super(cardId, userId, status, date, title, introduction, location, unitType, images);
            this.requirement = requirement;
            this.unionNum = unionNum;
            this.price = price;
            this.Square = square;
    }
}