package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.ResponseBody.OrdResponseBody.GetCompleteOrdRequest;
import com.mbry.IronMan.ResponseBody.OrdResponseBody.GetIncompleteOrdRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController("/api")
public class OrdController {
    @GetMapping(value="/complete/all/")
    public GetCompleteOrdRequest getCompleteOrd(
            @RequestParam String userId,
            @RequestParam int page) {
        return null;
    }

    @GetMapping(value="/incomplete/all/")
    public GetIncompleteOrdRequest getMethodName(
        @RequestParam String userId,
        @RequestParam int page) {
        return null;
    }
}