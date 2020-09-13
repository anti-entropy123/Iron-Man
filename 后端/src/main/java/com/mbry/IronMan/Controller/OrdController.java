package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.ResponseBody.OrdResponseBody.GetCompleteOrdResponse;
import com.mbry.IronMan.ResponseBody.OrdResponseBody.GetIncompleteOrdResponse;
import com.mbry.IronMan.Service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class OrdController {
    @Autowired
    private OrderService orderService;

    /**
     * 9月12号 测试通过
     * @param userId
     * @param page
     * @return
     */
    @GetMapping(value="/complete/all/")
    @PreAuthorize("hasRole('common')")
    public GetCompleteOrdResponse getCompleteOrd(
            @RequestParam String userId,
            @RequestParam int page) {
        return orderService.getCompleteOrd(userId, page);
    }

    /**
     * 9月12号 测试通过
     * @param userId
     * @param page
     * @return
     */
    @GetMapping(value="/incomplete/all/")
    @PreAuthorize("hasRole('common')")
    public GetIncompleteOrdResponse getIncompleteOrd(
            @RequestParam String userId,
            @RequestParam int page) {
        return orderService.getIncompleteOrd(userId, page);
    }
}