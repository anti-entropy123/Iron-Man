package com.mbry.IronMan.Service;

import java.util.ArrayList;

import com.mbry.IronMan.BusinessObject.Application.Application;
import com.mbry.IronMan.BusinessObject.Application.CardApplication;
import com.mbry.IronMan.BusinessObject.Card.Card;
import com.mbry.IronMan.Dao.ApplicationDao;
import com.mbry.IronMan.Dao.CardDao;
import com.mbry.IronMan.ResponseBody.OrdResponseBody.GetCompleteOrdResponse;
import com.mbry.IronMan.global.Global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private CardDao cardDao;

    @Autowired
    private ApplicationDao applicationDao;


    public GetCompleteOrdResponse getCompleteOrd(String userId, int page){
        // 查询出发送给此用户的, 已完成的请求, 即为该用户所发布的完成的订单.
        Card[] _cards = cardDao.queryCardsByUserId(userId);
        ArrayList<Card> cards = new ArrayList<>();
        for(Card card: _cards){
            if(card.isStatus()){
                // 如果已完成的话;
                cards.add(card);
            }
        }
        CardApplication[] apps = applicationDao.queryCardAppByApplicantUserId(userId);
        for(CardApplication app: apps){
            cards.add(
                cardDao.queryCardByCardId(app.getCardId())
            );
        }
        int pageNum = Global.pageSize;
        cards.subList(page*pageNum, (page+1)*pageNum);
        return new GetCompleteOrdResponse(cards.subList(page*pageNum, (page+1)*pageNum).toArray(new Card[0]), 1, "");
    }

    public GetIncompleteOrdResponse getIncompleteOrd(String userId, int page){
        // 查询出发送给此用户的, 已完成的请求, 即为该用户所发布的完成的订单.
        Card[] _cards = cardDao.queryCardsByUserId(userId);
        ArrayList<Card> cards = new ArrayList<>();
        for(Card card: _cards){
            if(!card.isStatus()){
                // 如果未完成的话
                cards.add(card);
            }
        }
        CardApplication[] apps = applicationDao.queryCardAppByApplicantUserId(userId);
        for(CardApplication app: apps){
            
            cards.add(
                cardDao.queryCardByCardId(app.getCardId())
            );
        }
        int pageNum = Global.pageSize;
        cards.subList(page*pageNum, (page+1)*pageNum);
        return new GetCompleteOrdResponse(cards.subList(page*pageNum, (page+1)*pageNum).toArray(new Card[0]), 1, "");
    }
}