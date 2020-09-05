package com.mbry.IronMan.BusinessObject.Card;

public class SellCard extends Card{
    private double price;
    private double square;
    private String requirement;
    
    public SellCard(){}
    public SellCard(
        String cardId,
        String userId,
        boolean status,
        String date,
        String title,
        String introduction,
        String location,
        boolean unitType,
        double price,
        double square,
        String requirement,
        String[] images
    ){
        super(cardId, userId, status, date, title, introduction, location, unitType, images);
        this.price = price;
        this.square = square;
        this.requirement = requirement;
    }
}