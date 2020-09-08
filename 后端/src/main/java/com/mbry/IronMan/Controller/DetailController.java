package com.mbry.IronMan.Controller;

import com.mbry.IronMan.RequestBody.DetailRequestBody.OrderApplyRequest;
import com.mbry.IronMan.RequestBody.DetailRequestBody.OrderTeamApplyRequest;
import com.mbry.IronMan.RequestBody.DetailRequestBody.ProcessApplyRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.DetailCardResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.GetApplyResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/detail")
public class DetailController {
    @GetMapping("/getCardDetail/")
    public DetailCardResponse getCardDetail(@RequestParam String cardId) {
        DetailCardResponse detailCardResponse = new DetailCardResponse(null, "no result", "no message");
        int type = 2;
        String title = "hello";
        String cover = "http://baidu.com";
        double[] prices = {1,2};
        double[] squares = {3,4};
        String location = "你家";
        boolean hasHouseRes = false;
        String[] images = {"hello", "world"};
        String introduce = "wu";
        String postDate = "wu";
        String requirement = "wu";
        int unitType = 1;
        boolean status = false;
        int unionNum = 4;
        String ownerId = "";
        DetailCardResponse.Data data = detailCardResponse.new Data(cardId, type, title, cover, prices, squares, location, hasHouseRes, images, introduce, postDate, requirement, unitType, status, unionNum, ownerId);
        detailCardResponse.setData(data);
        return detailCardResponse;
    }
    
    @PostMapping("/orderApply/")
    public DefaultResponse orderApply(@RequestBody OrderApplyRequest orderApplyRequest) {
        return null;
    }

    @PostMapping("/orderTeamApply/")
    public DefaultResponse orderTeamApply(@RequestBody OrderTeamApplyRequest orderTeamApplyRequest) {
        return null;
    }
    
    @GetMapping("/getApply/")
    public GetApplyResponse getApply(@RequestParam String cardId) {
        return null;
    }

    @PostMapping("/processApply/")
    public DefaultResponse processApply(@RequestBody ProcessApplyRequest processApplyRequest) {
        return null;
    }
    
}