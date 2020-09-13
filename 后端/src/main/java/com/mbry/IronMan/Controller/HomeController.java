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

    /**
     * 9.12 测试完成
     * @param type
     * @param page
     * @param location
     * @param minPrice
     * @param maxPrice
     * @param minSquare
     * @param maxSquare
     * @param unitType
     * @param hasHouseResource
     * @return
     */
    @GetMapping("/getCards/")
    @PreAuthorize("hasRole('common')")
    public CardResponse getCards(
            @RequestParam Integer type,
            @RequestParam Integer page,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double minSquare,
            @RequestParam(required = false) Double maxSquare,
            @RequestParam(required = false) Integer unitType,
            @RequestParam(required = false) Boolean hasHouseResource) {
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