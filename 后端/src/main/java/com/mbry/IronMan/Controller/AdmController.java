package com.mbry.IronMan.Controller;

import com.mbry.IronMan.RequestBody.AdmRequestBody.DeleteCardsRequest;
import com.mbry.IronMan.RequestBody.AdmRequestBody.DeleteUserRequestBody;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.AdmResponseBody.GetCardResponse;
import com.mbry.IronMan.Service.AdmCardService;
import com.mbry.IronMan.Service.DetailService;
import com.mbry.IronMan.Service.HomeService;
import com.mbry.IronMan.ResponseBody.AdmResponseBody.GetUserResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.DetailCardResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdmController {

    @Autowired
    AdmCardService admCardService;

    @Autowired
    HomeService homeService;

    @Autowired
    DetailService detailService;

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
        @RequestParam(required = false) String userId, 
        @RequestParam(required = false) Long minDate,
        @RequestParam(required = false) Long maxDate, 
        @RequestParam int page) {
        if (userId == "") {
            userId = null;
        }
        GetCardResponse getCardResponse = new GetCardResponse();
        try {
            getCardResponse.setData(admCardService.getCardsByRequire(
                type,
                userId,
                minDate==null?0:minDate,
                maxDate==null?0:maxDate,
                page
            ));
            getCardResponse.setTotalPage(admCardService.getCardsPagesByRequire(
                type,
                userId,
                minDate==null?0:minDate,
                maxDate==null?0:maxDate,
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
    @PostMapping("/api/adm/deleteCard/")
    @PreAuthorize("hasRole('common')")
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

    @GetMapping("/api/adm/getCardDetail/")
    @PreAuthorize("hasRole('super')")
    public DetailCardResponse getCards(
        @RequestParam String cardId) {
        return detailService.getCardDetail(cardId, "null");
    }
   

    @GetMapping("/api/adm/getUser/")
    @PreAuthorize("hasRole('super')")
    public GetUserResponse getUser(
        @RequestParam String nickname,
        @RequestParam String userId,
        @RequestParam String mobileNumber,
        @RequestParam int page) {
        if (nickname == "") {
            nickname = null;
        }
        if (userId == "") {
            userId = null;
        }
        if (mobileNumber == "") {
            mobileNumber = null;
        }
        return admCardService.getUser(
            nickname,
            userId,
            mobileNumber,
            page);
    }

    @PostMapping("/api/adm/deleteUser")
    @PreAuthorize("hasRole('super')")
    public DefaultResponse deleteUser(@RequestBody DeleteUserRequestBody deleteUserRequestBody) {
        return admCardService.deleteUser(deleteUserRequestBody.getUserIds());
    }

}
