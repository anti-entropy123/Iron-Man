package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.RequestBody.PersonRequestBody.UpdateInfoRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.PersonResponseBody.GetInfoResponse;
import com.mbry.IronMan.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 9月12号 通过测试
 */
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    UserService userService;

    /**
     * 9月12日 测试通过
     * @param userId
     * @return
     */
    @GetMapping(value = "/info/")
    @PreAuthorize("hasRole('common')")
    public GetInfoResponse getInfo(@RequestParam String userId) {
        return userService.getUserInfo(userId);
    }
    
    /**
     * 9月12日 测试通过
     * @param updateInfoRequest
     * @return
     */
    @PostMapping(value="/update/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse updateInfo(@RequestBody UpdateInfoRequest updateInfoRequest) {
        DefaultResponse defaultResponse = new DefaultResponse();
        if (userService.updateUserInfo(updateInfoRequest)) {
            defaultResponse.setResult(1);
            defaultResponse.setMessage("mbrynb");
        } else {
            defaultResponse.setResult(0);
            defaultResponse.setMessage("server error");
        } 
        return defaultResponse;
    }
    
}