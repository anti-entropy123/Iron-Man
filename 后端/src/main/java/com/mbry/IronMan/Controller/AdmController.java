package com.mbry.IronMan.Controller;

import com.mbry.IronMan.RequestBody.AdmRequestBody.*;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.AdmResponseBody.*;
import com.mbry.IronMan.Service.AdmCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdmController {

    @Autowired
    AdmCardService admCardService;

    /**
     * 获得card列表
     * @param type
     * @param userId
     * @param minDate
     * @param maxDate
     * @param page
     * @return
     */
    @GetMapping("/api/adm/getCard/")
    public GetCardResponse getCards(
        @RequestParam int type, 
        @RequestParam String userId, 
        @RequestParam Long minDate,
        @RequestParam Long maxDate, 
        @RequestParam int page) {
        GetCardResponse getCardResponse = new GetCardResponse();
        try {
            getCardResponse.setData(admCardService.getCardsByRequire(
                type,
                userId,
                minDate,
                maxDate,
                page
            ));
            getCardResponse.setTotalPage(admCardService.getCardsPagesByRequire(
                type,
                userId,
                minDate,
                maxDate,
                page
            ));
            getCardResponse.setResult(1);
            getCardResponse.setMessage("mbrynb");
            return getCardResponse;
        } catch (Exception e) {
            e.printStackTrace();
            getCardResponse.setResult(0);
            getCardResponse.setMessage("server error");
            return getCardResponse;
        } 
    }

    /**
     * 删除cards
     * @param deleteCardsRequest
     * @return
     */
    @DeleteMapping("/api/adm/deleteCard/")
    public DefaultResponse deleteCards(@RequestBody DeleteCardsRequest deleteCardsRequest) {
        try {
            admCardService.deleteCards(deleteCardsRequest.getCardIds());
            DefaultResponse defaultResponse = new DefaultResponse();
            defaultResponse.setMessage("mbrynb");
            defaultResponse.setResult(1);
            return defaultResponse;
        } catch(Exception e) {
            DefaultResponse defaultResponse = new DefaultResponse(1, "server error");
            return defaultResponse;
        }
        
    }
    
}
