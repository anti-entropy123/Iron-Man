package com.mbry.IronMan.ResponseBody.PublishResponseBody;

import com.mbry.IronMan.ResponseBody.DefaultResponse;

public class PublishImageResponse extends DefaultResponse{

    /**
     *
     */
    private static final long serialVersionUID = 7519579123842078719L;
    private String url;
    public PublishImageResponse(){}
    public PublishImageResponse(
        String url, 
        String result,
        String message
    ){
        super(result, message);
        this.url = url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }
}