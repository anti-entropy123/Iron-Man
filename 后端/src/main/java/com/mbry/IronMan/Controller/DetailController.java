package com.mbry.IronMan.Controller;

import com.mbry.IronMan.RequestBody.DetailRequestBody.OrderApplyRequest;
import com.mbry.IronMan.RequestBody.DetailRequestBody.OrderTeamApplyRequest;
import com.mbry.IronMan.RequestBody.DetailRequestBody.ProcessApplyRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.DetailCardResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.GetApplyResponse;
import com.mbry.IronMan.Service.DetailService;
import com.mbry.IronMan.Utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 9月12号测试通过
 */
@RestController
@RequestMapping("/api/detail")
public class DetailController {
    @Autowired
    private DetailService detailService;
    
    @Value("jwt.tokenHead")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 9/11 通过测试
     * @param cardId
     * @return
     */
    @GetMapping("/getCardDetail/")
    @PreAuthorize("hasRole('common')")
    public DetailCardResponse getCardDetail(@RequestHeader HttpHeaders headers, @RequestParam String cardId){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        
        return detailService.getCardDetail(cardId, jwtTokenUtil.getOpenIdFromToken(token));
    }
    
    /**
     * 9月12日 通过测试
     * @param orderApplyRequest
     * @return
     */
    @PostMapping("/orderApply/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse orderApply(@RequestBody OrderApplyRequest orderApplyRequest) {
        return detailService.orderApply(
            orderApplyRequest.getUserId(),
            orderApplyRequest.getCardId());
    }

    /**
     * 9月12日通过测试
     * @param orderTeamApplyRequest
     * @return
     */
    @PostMapping("/orderTeamApply/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse orderTeamApply(
            @RequestBody OrderTeamApplyRequest orderTeamApplyRequest,
            @RequestHeader HttpHeaders headers) {
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        return detailService.orderTeamApply(
            jwtTokenUtil.getOpenIdFromToken(token),
            orderTeamApplyRequest.getCardId(), 
            orderTeamApplyRequest.getTeamId());
    }
    
    /**
     * 9月12通过测试
     * @param cardId
     * @return
     */
    @GetMapping("/owner/getApply/")
    @PreAuthorize("hasRole('common')")
    public GetApplyResponse getApply(@RequestParam String cardId) {
        return detailService.getApply(cardId);
    }

    /**
     * 9月12日 通过测试
     * @param processApplyRequest
     * @return
     */
    @PostMapping("/processApply/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse processApply(@RequestBody ProcessApplyRequest processApplyRequest) {
        return detailService.processApply(processApplyRequest.getApplyId());
    }
    
}