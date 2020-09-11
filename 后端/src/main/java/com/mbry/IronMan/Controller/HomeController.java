package com.mbry.IronMan.Controller;

import com.mbry.IronMan.ResponseBody.HomeResponseBody.CardResponse;
import com.mbry.IronMan.Service.HomeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping("/getCards/")
    @PreAuthorize("hasRole('common')")
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
}