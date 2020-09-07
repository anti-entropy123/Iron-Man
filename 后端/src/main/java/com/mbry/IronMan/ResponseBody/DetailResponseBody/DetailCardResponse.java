package com.mbry.IronMan.ResponseBody.DetailResponseBody;

import java.io.Serializable;

public class DetailCardResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -9044987965597297702L;

    private class Data implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 2076009358978521051L;
        private String cardId;
        private int type;
        private String title;
        private String cover;
        private double[] price;
        private double[] square;
        private String location;
        private boolean hasHouseRes;
        private String[] images;
        private String introduce;
        private String postDate;
        private String requirement;
        private int unitType;
        private boolean status;
        private int unionNum;
        private String ownerId;


        public Data() {
        }

        public Data(
                String cardId,
                int type,
                String title,
                String cover,
                double[] price,
                double[] square,
                String location,
                boolean hasHouseRes,
                String[] images,
                String introduce,
                String postDate,
                String requirement,
                int unitType,
                boolean status,
                int unionNum,
                String ownerId) {
            this.cardId = cardId;
            this.type = type;
            this.title = title;
            this.cover = cover;
            this.price = price;
            this.square = square;
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

        public double[] getPrice() {
            return this.price;
        }

        public void setPrice(double[] price) {
            this.price = price;
        }

        public double[] getSquare() {
            return this.square;
        }

        public void setSquare(double[] square) {
            this.square = square;
        }

        public String getLocation() {
            return this.location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public boolean getHasHouseRes() {
            return this.hasHouseRes;
        }

        public void setHasHouseRes(boolean hasHouseRes) {
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

        public boolean getStatus() {
            return this.status;
        }

        public void setStatus(boolean status) {
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
    }
    private String result;
    private String message;
    private Data data;

    public DetailCardResponse() {
    }

    public DetailCardResponse(
            Data data,
            String result,
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

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}