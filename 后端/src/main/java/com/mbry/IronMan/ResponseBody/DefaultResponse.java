package com.mbry.IronMan.ResponseBody;

import java.io.Serializable;

public class DefaultResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7406970292639605091L;
    private String result;
    private String message;

    public DefaultResponse() {
    }

    public DefaultResponse(
            String result,
            String message) {
        this.result = result;
        this.message = message;
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