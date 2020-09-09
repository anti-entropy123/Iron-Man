package com.mbry.IronMan.ResponseBody.CommentResponseBody;

import java.io.Serializable;

import com.mbry.IronMan.BusinessObject.User;
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
        public class Reply implements Serializable {
            /**
             *
             */
            private static final long serialVersionUID = -5147083686244566133L;
            private String name;
            private String replyId;
            private String content;
            private String createDate;
            private String replyToUserId;
            private String replyToUserName;
            private String userId;
            public Reply() {
            }
        
            public Reply(
                    com.mbry.IronMan.BusinessObject.Comment.Reply reply,
                    User user,
                    User replyToUser) {
                this.name = user.getNickname();
                this.replyId = reply.getCommentId();
                this.content = reply.getContent();
                this.createDate = reply.getDate();
                this.replyToUserId = reply.getReplyToId();
                this.replyToUserName = replyToUser.getNickname();
                this.userId = user.getUserId(); 
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
        private static final long serialVersionUID = 7268033372438758459L;
        Reply[] replies;

        public Data() {

        }
    
        public Data(
                com.mbry.IronMan.BusinessObject.Comment.Reply[] replies,
                User[] users,
                User[] replyToUsers) {
            this.replies = new Reply[replies.length];
            for(int i = 0;i < replies.length; ++i){
                this.replies[i] = new Reply(
                    replies[i], 
                    users[i], 
                    replyToUsers[i]);
            }
        }
    }
    private Data data;

    public GetRepliesResponse(){}
    public GetRepliesResponse(
            Data data,
            int result,
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