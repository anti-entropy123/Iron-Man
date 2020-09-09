package com.mbry.IronMan.ResponseBody;

import java.io.Serializable;

public class DefaultResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7406970292639605091L;
    private int result;
    private String message;

    public DefaultResponse() {
    }

    public DefaultResponse(
            int result,
            String message) {
        this.result = result;
        this.message = message;
    }

    public int getResult() {
        return this.result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}