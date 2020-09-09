package com.mbry.IronMan.RequestBody.CommentRequestBody;

import java.io.Serializable;

public class DeleteCommentRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -4759847543301324248L;
    private String commentId;

    public DeleteCommentRequest() {
    }

    public DeleteCommentRequest(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentId() {
        return this.commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}