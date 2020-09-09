package com.mbry.IronMan.RequestBody.CommentRequestBody;

import java.io.Serializable;

public class AddCommentRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6612815164836201471L;
    private String userId;
    private String cardId;
    private String content;
    private String replyToId;

    public AddCommentRequest() {
    }

    public AddCommentRequest(
            String userId,
            String cardId,
            String content,
            String replyToId) {
        this.userId = userId;
        this.cardId = cardId;
        this.content = content;
        this.replyToId = replyToId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardId() {
        return this.cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyToId() {
        return this.replyToId;
    }

    public void setReplyToId(String replyToId) {
        this.replyToId = replyToId;
    }
}