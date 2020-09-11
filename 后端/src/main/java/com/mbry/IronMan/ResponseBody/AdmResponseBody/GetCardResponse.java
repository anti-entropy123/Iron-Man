package com.mbry.IronMan.ResponseBody.AdmResponseBody;

import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class GetCardResponse extends DefaultResponse {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public class Data {

        private String cardId;
        private int type;
        private String userId;
        private String date;
        private String title;

        public Data() {}

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
        
        public String getDate() {
            return this.date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }

    private Data[] data;
    private int totalPage;

    public GetCardResponse() {}
    public GetCardResponse(
        int result,
        String message,
        Data[] data,
        int totalPage) {
        super(result, message);
        this.setData(data);
        this.setTotalPage(totalPage);
    }
    
    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
}