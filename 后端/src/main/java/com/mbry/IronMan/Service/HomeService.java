package com.mbry.IronMan.Service;

import com.mbry.IronMan.BusinessObject.Card.Card;
import com.mbry.IronMan.Dao.CardDao;
import com.mbry.IronMan.ResponseBody.HomeResponseBody.CardResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    @Autowired
    private CardDao cardDao;
    
    public CardResponse getALLCards(int page){
        Card[] cards = cardDao.queryALLCards(page);
        CardResponse response = new CardResponse();
        
        CardResponse.Data data = response.new Data(cards);
        response.setData(data);
        response.setResult(1);
        response.setMessage("");
        return response;
    }

    public CardResponse getCardsWithCondtion(
            int type,
            int page,
            String location,
            double minPrice,
            double maxPrice,
            double minSquare,
            double maxSquare,
            int unitType,
            boolean hasHouseResource){
        Card[] cards;
        Double[] prices = new Double[]{minPrice, maxPrice};
        Double[] squares = new Double[]{minSquare, maxSquare};
        switch(type){
            case 1: cards = cardDao.queryRentCards(
                                    page, 
                                    location, 
                                    prices, 
                                    squares, 
                                    unitType
                                );break;
            case 2: cards = cardDao.querySellCards(
                                    page, 
                                    location, 
                                    prices, 
                                    squares, 
                                    unitType
                                );break;
            case 3: cards = cardDao.queryAskRentCards(
                                    page, 
                                    location, 
                                    prices, 
                                    squares, 
                                    unitType
                                );break;
            case 4: cards = cardDao.queryAskSellCards(
                                    page, 
                                    location, 
                                    prices, 
                                    squares, 
                                    unitType
                                );break;
            case 5: cards = cardDao.queryAskRoomMateCards(
                                    page, 
                                    location, 
                                    prices, 
                                    squares, 
                                    unitType, 
                                    hasHouseResource
                                );break;       
            default: cards = new Card[0];
        }
        CardResponse cardResponse = new CardResponse();
        CardResponse.Data data = cardResponse.new Data(cards);
        cardResponse.setData(data);
        cardResponse.setResult(1);
        cardResponse.setMessage("");
        return cardResponse;
    }
}