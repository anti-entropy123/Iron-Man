package com.mbry.IronMan.ResponseBody.OrdResponseBody;

import java.io.Serializable;

public class GetCompleteOrdRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1038013217654236746L;
    public class Data implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = -5901321498254340640L;
        public class Card implements Serializable {
            /**
             *
             */
            private static final long serialVersionUID = -4255988525432960030L;
            private String cardId;
            private int type;
            private String title;
            private String cover;
            private double[] prices;
            private double[] squares;
            private String location;
            private boolean hasHouseResource;
            private String introduction;
    
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
                    boolean hasHouseResource,
                    String introduction) {
                this.cardId = cardId;
                this.type = type;
                this.title = title;
                this.cover = cover;
                this.prices = prices;
                this.squares = squares;
                this.location = location;
                this.hasHouseResource = hasHouseResource;
                this.introduction = introduction;
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
    
            public boolean getHasHouseResource() {
                return this.hasHouseResource;
            }
        
            public void setHasHouseResource(boolean hasHouseResource) {
                this.hasHouseResource = hasHouseResource;
            }
    
            public String getIntroduction(String introduction) {
                return this.introduction;
            }
        
            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }
        }
        private Card[] cards;
    
        public Data() {
        }
    
        public Data(Card[] cards) {
            this.cards = cards;
        }
    
        public Card[] getCards() {
            return this.cards;
        }
    
        public void setCards(Card[] cards) {
            this.cards = cards;
        }
    }

    private Data data;
    private int result;
    private String message;

    public GetCompleteOrdRequest() {
    }
    public GetCompleteOrdRequest(
            Data data,
            int result,
            String message) {
        this.data = data;
        this.result = result;
        this.message = message;
    } 

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getResult() {
        return this.result;
    }

    public void setresult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}