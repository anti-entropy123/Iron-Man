package com.mbry.IronMan.RequestBody.BaseRequestBody;

import java.io.Serializable;

public class MobileNumber implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1333986793385116669L;
    private String number;
    private int result;
    private String message;

    public MobileNumber() {
    }

    public MobileNumber(
            String number,
            int result,
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