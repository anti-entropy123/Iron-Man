package com.mbry.IronMan.ResponseBody.CommentResponseBody;

import java.io.Serializable;

import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class GetCommentResponse extends DefaultResponse {
    /**
     *
     */
    private static final long serialVersionUID = 6836011095510807725L;
    
    public class Data implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 5803875297322361460L;
        public class Comment implements Serializable {
            /**
             *
             */
            private static final long serialVersionUID = 2567641040264631274L;
            private String commentId;
            private String content;
            private String userId;
            private String name;
            private String avatarUrl;
            private String createDate;
            private int replyNum;

        
            public Comment() {
            }
        
            public Comment(
                    String commentId,
                    String content,
                    String userId,
                    String name,
                    String avatarUrl,
                    String createDate,
                    int replyNum) {
                this.commentId = commentId;
                this.content = content;
                this.userId = userId;
                this.name = name;
                this.avatarUrl = avatarUrl;
                this.createDate = createDate;
                this.replyNum = replyNum;
            }
        
            public String getCommentId() {
                return this.commentId;
            }
        
            public void setCommentId(String commentId) {
                this.commentId = commentId;
            }

            public String getContent() {
                return this.content;
            }
        
            public void setContent(String content) {
                this.content = content;
            }

            public String getUserId() {
                return this.userId;
            }
        
            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getName() {
                return this.name;
            }
        
            public void setName(String name) {
                this.name = name;
            }

            public String getAvatarUrl() {
                return this.avatarUrl;
            }
        
            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getCreateDate() {
                return this.createDate;
            }
        
            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getReplyNum() {
                return this.replyNum;
            }
        
            public void setReplyNum(int replyNum) {
                this.replyNum = replyNum;
            }
        }
        public Comment[] comments;

        public Data() {
        }
    
        public Data(
                Comment[] comments) {
            this.comments = comments;
        }
    
        public Comment[] getComments() {
            return this.comments;
        }
    
        public void setComments(Comment[] comments) {
            this.comments = comments;
        }
    }
    
    private Data data;

    public GetCommentResponse() {
    }

    public GetCommentResponse(
            String result,
            String message,
            Data data) {
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