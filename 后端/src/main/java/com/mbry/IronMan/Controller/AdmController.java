package com.mbry.IronMan.Controller;

import com.mbry.IronMan.RequestBody.AdmRequestBody.*;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.AdmResponseBody.*;
import com.mbry.IronMan.ResponseBody.HomeResponseBody.CardResponse;
import com.mbry.IronMan.Service.AdmCardService;
import com.mbry.IronMan.Service.HomeService;
import com.mbry.IronMan.RequestBody.AdmRequestBody.DeleteUserRequestBody;
import com.mbry.IronMan.ResponseBody.AdmResponseBody.GetUserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdmController {

    @Autowired
    AdmCardService admCardService;

    @Autowired
    HomeService homeService;

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
    @PreAuthorize("hasRole('super')")
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

    @GetMapping("/api/adm/deleteCard/")
    public CardResponse getCards(
        @RequestParam int type,
        @RequestParam int page,
        @RequestParam String location,
        @RequestParam double minPrice,
        @RequestParam double maxPrice,
        @RequestParam double minSquare,
        @RequestParam double maxSquare,
        @RequestParam int unitType,
        @RequestParam boolean hasHouseResource) {
        if(type == 0){
            return homeService.getALLCards(page);
        }else{
            return homeService.getCardsWithCondtion(
                type, 
                page, 
                location, 
                minPrice, 
                maxPrice, 
                minSquare, 
                maxSquare, 
                unitType, 
                hasHouseResource);
        }
    }

    @GetMapping("/api/adm/getUser/")
    public GetUserResponse getUser(
        @RequestParam String nickname,
        @RequestParam String userId,
        @RequestParam String mobileNumber,
        @RequestParam int page) {
        return admCardService.getUser(
            nickname,
            userId,
            mobileNumber,
            page);
    }

    @DeleteMapping("/api/adm/deleteUser")
    public DefaultResponse deleteUser(@RequestBody DeleteUserRequestBody deleteUserRequestBody) {
        return admCardService.deleteUser(deleteUserRequestBody.getUserIds());
    }

}
