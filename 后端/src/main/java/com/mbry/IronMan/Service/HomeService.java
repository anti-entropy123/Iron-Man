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
        response.setresult(1);
        response.setMessage("");
        CardResponse.Data data = response.new Data(cards);
        response.setData(data);
        response.setresult(1);
        response.setresult("");
        return 
    }
}