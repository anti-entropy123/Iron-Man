package com.mbry.IronMan.ResponseBody.CommentResponseBody;

import java.io.Serializable;

import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class GetRepliesResponse extends DefaultResponse{
    /**
     *
     */
    private static final long serialVersionUID = -3054505272820071476L;

    public class Data implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 7268033372438758459L;
        private String name;
        private String replyId;
        private String content;
        private String createDate;
        private String replyToUserId;
        private String replyToUserName;
        private String userId;
    
        public Data() {
        }
    
        public Data(String name) {
            this.name = name;
        }
    
        public String getName() {
            return this.name;
        }
    
        public void setName(String name) {
            this.name = name;
        }

        public String getReplyId() {
            return this.replyId;
        }
    
        public void setReplyId(String replyId) {
            this.replyId = replyId;
        }

        public String getContent() {
            return this.content;
        }
    
        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateDate() {
            return this.createDate;
        }
    
        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getReplyToUserId() {
            return this.replyToUserId;
        }
    
        public void setReplyToUserId(String replyToUserId) {
            this.replyToUserId = replyToUserId;
        }

        public String getReplyToUserName() {
            return this.replyToUserName;
        }
    
        public void setReplyToUserName(String replyToUserName) {
            this.replyToUserName = replyToUserName;
        }

        public String getUserId() {
            return this.userId;
        }
    
        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
    private Data data;

    GetRepliesResponse(){}
    GetRepliesResponse(
            Data data,
            String result,
            String message){
        super(result, message);
        this.data = data;
    }

    public Data getData(){
        return this.data;
    }

    public void setData(Data data){
        this.data = data;
    }
}