package com.mbry.IronMan.Controller;

import com.mbry.IronMan.ResponseBody.HomeResponseBody.CardResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController("/api/home")
public class HomeController {
    @GetMapping("/getCards/")
    public CardResponse getMethodName(
            @RequestParam int type,
            @RequestParam int page,
            @RequestParam String location,
            @RequestParam double minPrice,
            @RequestParam double maxPrice,
            @RequestParam double minSquare,
            @RequestParam double maxSquare,
            @RequestParam int unitType,
            @RequestParam double hasHouseResource) {
        return null;
    }
    
    
}