package com.mbry.IronMan.RequestBody.AdmRequestBody;

import java.io.Serializable;

public class DeleteUserRequestBody implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String[] userIds;

    public DeleteUserRequestBody() {}
    public DeleteUserRequestBody(String[] userIds) {
        this.setUserIds(userIds);
    }

    public String[] getUserIds() {
        return userIds;
    }

    public void setUserIds(String[] userIds) {
        this.userIds = userIds;
    }
}