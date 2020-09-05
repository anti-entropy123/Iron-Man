package com.mbry.IronMan.BusinessObject.Card;

public class AskSellCard extends Card{
    private double minPrice;
    private double maxPrice;
    private double minSquare;
    private double maxSquare;

    public AskSellCard(){}
    public AskSellCard(
        String cardId,
        String userId,
        boolean status,
        String date,
        String title,
        String introduction,
        String location,
        boolean unitType,
        double minPrice,
        double maxPrice,
        double minSquare,
        double maxSquare,
        String[] images
        ){
            super(cardId, userId, status, date, title, introduction, location, unitType, images);
            this.minPrice = minPrice;
            this.maxPrice = maxPrice;
            this.minSquare = minSquare;
            this.maxSquare = maxSquare;
    }
}