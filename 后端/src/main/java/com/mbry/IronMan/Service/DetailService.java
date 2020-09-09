package com.mbry.IronMan.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mbry.IronMan.BusinessObject.Log;
import com.mbry.IronMan.BusinessObject.Application.CardApplication;
import com.mbry.IronMan.BusinessObject.Application.TeamApplication;
import com.mbry.IronMan.BusinessObject.Card.AskRentCard;
import com.mbry.IronMan.BusinessObject.Card.AskSellCard;
import com.mbry.IronMan.BusinessObject.Card.Card;
import com.mbry.IronMan.BusinessObject.Card.RentCard;
import com.mbry.IronMan.BusinessObject.Card.RoomMateCard;
import com.mbry.IronMan.BusinessObject.Card.SellCard;
import com.mbry.IronMan.Dao.ApplicationDao;
import com.mbry.IronMan.Dao.CardDao;
import com.mbry.IronMan.Dao.LogDao;
import com.mbry.IronMan.Dao.TeamDao;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.DetailCardResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.GetApplyResponse;
import com.mbry.IronMan.Utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailService {
    @Autowired
    private CardDao cardDao;

    @Autowired 
    private ApplicationDao applicationDao;

    @Autowired
    private UserDao userDao;

    @Autowired 
    LogDao logDao;

    @Autowired
    DateUtil dateUtil;

    @Autowired
    TeamDao teamDao;

    private static final Map<Class<? extends Card>, Integer> transCardToType;
    static{
        transCardToType = new HashMap<>();
        transCardToType.put(RentCard.class, 1);
        transCardToType.put(SellCard.class, 2);
        transCardToType.put(AskRentCard.class, 3);
        transCardToType.put(AskSellCard.class, 4);
        transCardToType.put(RoomMateCard.class, 5);
    };

    public DetailCardResponse getCardDetail(String cardId){
        Card card = cardDao.queryCardByCardId(cardId);
        int type = transCardToType.get(card.getClass());
        double[] prices = (type==1 || type == 2)? new double[1]:new double[2];
        double[] squares = (type==1 || type == 2)? new double[1]:new double[2];
        String requirement = "";
        int unionNum = -1;
        if(type == 1 || type == 2){
            prices[0] = ((type == 1)?((RentCard)card).getPrice():((SellCard)card).getPrice());
            squares[0] = ((type == 1)?((RentCard)card).getSquare():((SellCard)card).getSquare());
            if(type == 1){
                requirement = ((RentCard)card).getRequirement();
                unionNum = ((RentCard)card).getUnionNum();
            }
        }else{
            if(type == 3){
                AskRentCard arc = (AskRentCard)card;
                prices[0] = arc.getMinPrice();
                prices[1] = arc.getMaxPrice();
                squares[0] = arc.getMinSquare();
                squares[1] = arc.getMaxSquare();
                requirement = arc.getRequirement();
                unionNum = arc.getUnionNum();
            }else if(type == 4){
                AskSellCard asc = (AskSellCard)card;
                prices[0] = asc.getMinPrice();
                prices[1] = asc.getMaxPrice();
                squares[0] = asc.getMinSquare();
                squares[1] = asc.getMaxSquare();
            }else{
                RoomMateCard rmc = (RoomMateCard)card;
                prices[0] = rmc.getMinPrice();
                prices[1] = rmc.getMaxPrice();
                squares[0] = rmc.getMinSquare();
                squares[1] = rmc.getMaxSquare();
                requirement = rmc.getRequirement();
                unionNum = rmc.getUnionNum();
            }
        }

        DetailCardResponse response = new DetailCardResponse();
        DetailCardResponse.Data data = response.new Data(
                                                cardId,
                                                type,
                                                card.getTitle(),
                                                prices,
                                                squares,
                                                card.getLocation(),
                                                type==5?((RoomMateCard)card).isHasHouseResource():false,
                                                card.getImages(),
                                                card.getIntroduction(),
                                                card.getDate(),
                                                requirement,
                                                card.getUnitType(),
                                                card.isStatus(),
                                                unionNum,
                                                card.getUserId()
                                                );
        response.setData(data);
        response.setResult(1);
        response.setMessage("");
        return response; 
    }

    public DefaultResponse orderApply(String userId, String cardId){
        String targetUserId = userDao.queryUserByCardId(cardId).getUserId();
        String date = dateUtil.getDate();
        CardApplication app = new CardApplication(null, userId, targetUserId, false, date, cardId);
        String applyId = applicationDao.createApplication(app);
        
        logDao.addLog(new Log(-1, 0, cardId, applyId, userId, targetUserId, Boolean.valueOf(false)));
        
        // todo 在这里发送微信模板消息

        return new DefaultResponse(1, "");
    }

    public DefaultResponse orderTeamApply(String userId, String teamId){
        String date = dateUtil.getDate();
        TeamApplication app = new TeamApplication(null, userId, false, date, teamId);
        String applyId = applicationDao.createApplication(app);
        String targetUserId = teamDao.queryCaptainIdFromTeamId(teamId);

        logDao.addLog(new Log(-1, 1, teamDao.queryCardIdFromTeamId(teamId), applyId, userId, targetUserId, false));

        // todo 在这里发送微信模板消息

        return new DefaultResponse(1, "");
    }

    public GetApplyResponse getApply(String cardId){
        GetApplyResponse response = new GetApplyResponse();
        GetApplyResponse.Data data = response.new Data();
        List<GetApplyResponse.Data.Person> persons = new ArrayList<GetApplyResponse.Data.Person>();
        List<GetApplyResponse.Data.Team> teams = new ArrayList<GetApplyResponse.Data.Team>();

        // todo 没写完呢
        return response;
    }
}