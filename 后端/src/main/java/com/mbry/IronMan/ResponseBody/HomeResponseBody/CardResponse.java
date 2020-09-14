package com.mbry.IronMan.ResponseBody.HomeResponseBody;

import com.mbry.IronMan.BusinessObject.Card.AskRentCard;
import com.mbry.IronMan.BusinessObject.Card.AskSellCard;
import com.mbry.IronMan.BusinessObject.Card.RentCard;
import com.mbry.IronMan.BusinessObject.Card.RoomMateCard;
import com.mbry.IronMan.BusinessObject.Card.SellCard;
import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class CardResponse extends DefaultResponse {
    /**
     *
     */
    private static final long serialVersionUID = -1038013217654236746L;


    public class Data /*implements Serializable*/ {
        /**
         *
         */
      //  private static final long serialVersionUID = -5901321498254340640L;
        public class Card /*implements Serializable*/ {
            /**
             *
             */
            //private static final long serialVersionUID = -4255988525432960030L;
            private String cardId;
            private int type;
            private String title;
            private String cover;
            private double[] prices;
            private double[] squares;
            private String location;
            private Boolean hasHouseResource;
            private String introduction;
            private Double[] coordinates;

            public Card() {
            }
        
            public Card(
                    String cardId,
                    int type,
                    String title,
                    String cover,
                    double[] prices,
                    double[] squares,
                    String location,
                    Boolean hasHouseResource,
                    String introduction,
                    Double[] coordinates) {
                this.cardId = cardId;
                this.type = type;
                this.title = title;
                this.cover = cover;
                this.prices = prices;
                this.squares = squares;
                this.location = location;
                this.hasHouseResource = hasHouseResource;
                this.introduction = introduction;
                this.coordinates = coordinates;
            }
        
            public String getCardId() {
                return this.cardId;
            }
        
            public void setCardId(String cardId) {
                this.cardId = cardId;
            }
    
            public int getType() {
                return this.type;
            }
        
            public void setType(int type) {
                this.type = type;
            }
    
            public String getTitle() {
                return this.title;
            }
        
            public void setTitle(String title) {
                this.title = title;
            }
    
            public String getCover() {
                return this.cover;
            }
        
            public void setCover(String cover) {
                this.cover = cover;
            }
    
            public double[] getPrices() {
                return this.prices;
            }
        
            public void setPrices(double[] prices) {
                this.prices = prices;
            }
    
            public double[] getSquares() {
                return this.squares;
            }
        
            public void setSquares(double[] squares) {
                this.squares = squares;
            }
    
            public String getLocation() {
                return this.location;
            }
        
            public void setLocation(String location) {
                this.location = location;
            }
    
            public Boolean getHasHouseResource() {
                return this.hasHouseResource;
            }
        
            public void setHasHouseResource(Boolean hasHouseResource) {
                this.hasHouseResource = hasHouseResource;
            }
    
            public String getIntroduction() {
                return this.introduction;
            }
        
            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public Double[] getCoordinates() {
                return this.coordinates;
            }
        
            public void setCoordinates(Double[] coordinates) {
                this.coordinates = coordinates;
            }
        }

        private Card[] cards;
    
        public Data() {
        }
    
        public Data(Card[] cards) {
            this.cards = cards;
        }

        public Data(com.mbry.IronMan.BusinessObject.Card.Card[] cards){
            this.cards = new Card[cards.length];
            for(int i = 0; i < cards.length; ++i ){
                int type = 0;
                com.mbry.IronMan.BusinessObject.Card.Card _card = cards[i];
                double[] prices;
                double[] squares;
                Boolean hasHouseResource = null;
                Double[] coordinates = new Double[2];
                if(_card instanceof RentCard){
                    type = 1;
                    RentCard rc = (RentCard)_card;
                    prices = new double[1];
                    prices[0] = rc.getPrice();
                    squares = new double[1];
                    squares[0] = rc.getSquare();
                    coordinates[0] = rc.getLongitude();
                    coordinates[1] = rc.getLatitude();
                }else if(_card instanceof SellCard){
                    type = 2;
                    SellCard sc = (SellCard)_card;
                    prices = new double[1];
                    prices[0] = sc.getPrice();
                    squares = new double[1];
                    squares[0] = sc.getSquare();
                    coordinates[0] = sc.getLongitude();
                    coordinates[1] = sc.getLatitude();
                }else if(_card instanceof AskRentCard){
                    type = 3;
                    AskRentCard arc = (AskRentCard)_card;
                    prices = new double[2];
                    prices[0] = arc.getMinPrice();
                    prices[1] = arc.getMaxPrice();
                    squares = new double[2];
                    squares[0] = arc.getMinSquare();
                    squares[1] = arc.getMaxPrice();
                }else if(_card instanceof AskSellCard){
                    type = 4;
                    AskSellCard asc = (AskSellCard)_card;
                    prices = new double[2];
                    prices[0] = asc.getMinPrice();
                    prices[1] = asc.getMaxPrice();
                    squares = new double[2];
                    squares[0] = asc.getMinSquare();
                    squares[1] = asc.getMaxPrice();
                }else{
                    type = 5;
                    RoomMateCard rmc = (RoomMateCard)_card;
                    prices = new double[2];
                    prices[0] = rmc.getMinPrice();
                    prices[1] = rmc.getMaxPrice();
                    squares = new double[2];
                    squares[0] = rmc.getMinSquare();
                    squares[1] = rmc.getMaxPrice();
                    hasHouseResource = rmc.isHasHouseResource();
                }
                
                this.cards[i] = new Card(
                    _card.getCardId(), 
                    type, 
                    _card.getTitle(), 
                    _card.getImages().length > 0? _card.getImages()[0]:null,
                    prices, 
                    squares, 
                    _card.getLocation(), 
                    hasHouseResource, 
                    _card.getIntroduction(),
                    coordinates
                );
            }
        }
    
        public Card[] getCards() {
            return this.cards;
        }
    
        public void setCards(Card[] cards) {
            this.cards = cards;
        }
    }

    private Data data;

    public CardResponse() {
    }

    public CardResponse(
            Data data,
            int result,
            String message) {
        super(result, message);
        this.data = data;
    } 

    public CardResponse(
        com.mbry.IronMan.BusinessObject.Card.Card[] cards,
        int result,
        String message){
        super(result, message);
        this.data = new Data(cards);
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}