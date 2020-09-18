package com.mbry.IronMan.ResponseBody.MessageResponseBody;

import java.io.Serializable;

import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class GetAllMessageResponse extends DefaultResponse{
    /**
     *
     */
    private static final long serialVersionUID = -2240409270396643036L;

    public class Data implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 2163699239802788154L;

        public class Message implements Serializable {
            /**
             *
             */
            private static final long serialVersionUID = 2714927760673572607L;
            private int type;
            private String cardId;
            private String applyId;
            private String userId;
            private int logId;
            private Boolean status;
            private String avatarUrl;
            private String nickname;

            public Message() {
            }
        
            public Message(
                    int type,
                    String cardId,
                    String applyId,
                    String userId,
                    int logId,
                    Boolean status,
                    String avatarUrl,
                    String nickname) {
                this.type = type;
                this.cardId = cardId;
                this.applyId = applyId;
                this.userId = userId;
                this.logId = logId;
                this.status = status;
                this.avatarUrl = avatarUrl;
                this.nickname = nickname;
            }
        
            public int getType() {
                return this.type;
            }
        
            public void setType(int type) {
                this.type = type;
            }

            public String getCardId() {
                return this.cardId;
            }
        
            public void setCardId(String cardId) {
                this.cardId = cardId;
            }

            public String getApplyId() {
                return this.applyId;
            }
        
            public void setApplyId(String applyId) {
                this.applyId = applyId;
            }

            public String getUserId() {
                return this.userId;
            }
        
            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getLogId() {
                return this.logId;
            }

            public void setLogId(int logId) {
                this.logId = logId;
            }

            public Boolean getStatus() {
                return this.status;
            }

            public void setStatus(Boolean status) {
                this.status = status;
            }

            public String getAvatarUrl() {
                return this.avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getNickname() {
                return this.nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }

        private Message[] messages;
    
        public Data() {
        }
    
        public Data(Message[] messages) {
            this.messages = messages;
        }
    
        public Message[] getMessages() {
            return this.messages;
        }
    
        public void setMessages(Message[] messages) {
            this.messages = messages;
        }
    }
    private Data data;

    public GetAllMessageResponse(){}
    public GetAllMessageResponse(
            Data data,
            int result,
            String message){
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