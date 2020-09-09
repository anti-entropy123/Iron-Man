package com.mbry.IronMan.RequestBody.BaseRequestBody;

import java.io.Serializable;

public class MobileNumber implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1333986793385116669L;
    private String number;
    private String result;
    private String message;

    public MobileNumber() {
    }

    public MobileNumber(
            String number,
            String result,
            String message) {
        this.number = number;
        this.result = result;
        this.message = message;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
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