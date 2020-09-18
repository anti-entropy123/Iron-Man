package com.mbry.IronMan.ResponseBody.DetailResponseBody;

import java.io.Serializable;

import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class DetailCardResponse extends DefaultResponse {
    /**
     *
     */
    private static final long serialVersionUID = -9044987965597297702L;

    public class Data implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 2076009358978521051L;
        private String cardId;
        private int type;
        private String title;
        private Double[] prices;
        private Double[] squares;
        private String location;
        private Boolean hasHouseRes;
        private String[] images;
        private String introduce;
        private String postDate;
        private String requirement;
        private int unitType;
        private Boolean status;
        private int unionNum;
        private String ownerId;
        private Double[] coordinates;
        private Integer hasApplied;

        public Data() {
        }

        public Data(
                String cardId,
                int type,
                String title,
                //String cover,
                Double[] prices,
                Double[] squares,
                String location,
                Boolean hasHouseRes,
                String[] images,
                String introduce,
                String postDate,
                String requirement,
                int unitType,
                Boolean status,
                int unionNum,
                String ownerId,
                Double[] coordinates,
                Integer hasApplied) {
            this.cardId = cardId;
            this.type = type;
            this.title = title;
            //this.cover = cover;
            this.prices = prices;
            this.squares = squares;
            this.location = location;
            this.hasHouseRes = hasHouseRes;
            this.images = images;
            this.introduce = introduce;
            this.postDate = postDate;
            this.requirement = requirement;
            this.unitType = unitType;
            this.status = status;
            this.unionNum = unionNum;
            this.ownerId = ownerId;
            this.coordinates = coordinates;
            this.setHasApplied(hasApplied);
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

        // public String getCover() {
        //     return this.cover;
        // }

        // public void setCover(String cover) {
        //     this.cover = cover;
        // }

        public Double[] getPrices() {
            return this.prices;
        }

        public void setPrices(Double[] prices) {
            this.prices = prices;
        }

        public Double[] getSquares() {
            return this.squares;
        }

        public void setSquares(Double[] squares) {
            this.squares = squares;
        }

        public String getLocation() {
            return this.location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Boolean getHasHouseRes() {
            return this.hasHouseRes;
        }

        public void setHasHouseRes(Boolean hasHouseRes) {
            this.hasHouseRes = hasHouseRes;
        }

        public String[] getImages() {
            return this.images;
        }

        public void setImages(String[] images) {
            this.images = images;
        }

        public String getIntroduce() {
            return this.introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }
        
        public String getPostDate() {
            return this.postDate;
        }

        public void setPostDate(String postDate) {
            this.postDate = postDate;
        }

        public String getRequirement() {
            return this.requirement;
        }

        public void setRequirement(String requirement) {
            this.requirement = requirement;
        }

        public int getUnitType() {
            return this.unitType;
        }

        public void setUnitType(int unitType) {
            this.unitType = unitType;
        }

        public Boolean getStatus() {
            return this.status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public int getUnionNum() {
            return this.unionNum;
        }

        public void setUnionNum(int unionNum) {
            this.unionNum = unionNum;
        }

        public String getOwnerId() {
            return this.ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        public Double[] getCoordinates() {
            return this.coordinates;
        }

        public void setCoordinates(Double[] coordinates) {
            this.coordinates = coordinates;
        }

        public Integer getHasApplied() {
            return hasApplied;
        }

        public void setHasApplied(Integer hasApplied) {
            this.hasApplied = hasApplied;
        }
    }
    private Data data;

    public DetailCardResponse() {
    }

    public DetailCardResponse(
            Data data,
            int result,
            String message) {
        super(result, message);
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}