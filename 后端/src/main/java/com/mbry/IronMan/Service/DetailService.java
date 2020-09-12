package com.mbry.IronMan.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mbry.IronMan.BusinessObject.Log;
import com.mbry.IronMan.BusinessObject.Team;
import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.BusinessObject.Application.Application;
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
import com.mbry.IronMan.Utils.WxMessageUtil;

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

    @Autowired
    WxMessageUtil wxMessageUtil;

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
        wxMessageUtil.sendMessage();
        return new DefaultResponse(1, "");
    }

    public DefaultResponse orderTeamApply(String userId, String cardId, String teamId){
        String date = dateUtil.getDate();
        CardApplication cardApplication = new CardApplication(
            null, 
            userId, 
            cardDao.queryCardByCardId(cardId).getUserId(), 
            false, 
            date, 
            cardId);
        String applyId = applicationDao.createApplication(cardApplication);
        String targetUserId = teamDao.queryCaptainIdFromTeamId(teamId);

        logDao.addLog(new Log(-1, 1, teamDao.queryCardIdFromTeamId(teamId), applyId, userId, targetUserId, false));

        // todo 在这里发送微信模板消息
        wxMessageUtil.sendMessage();
        return new DefaultResponse(1, "");
    }

    public GetApplyResponse getApply(String cardId){
        GetApplyResponse response = new GetApplyResponse();
        GetApplyResponse.Data data = response.new Data();
        List<GetApplyResponse.Data.Person> persons = new ArrayList<GetApplyResponse.Data.Person>();
        List<GetApplyResponse.Data.Team> teams = new ArrayList<GetApplyResponse.Data.Team>();
        Application[] apps = applicationDao.queryCardApplicationsByCardId(cardId);
        
        for(Application app: apps){
            if(!(app instanceof CardApplication)){
                // 理论上这里只能是card application, 如果不是, 则先跳过
                continue;
            }
            if(app.isStatus()){
                // 如果该申请被处理过了, 则不需要再返回给前端.
                continue;
            }
            CardApplication cardApp = (CardApplication)app;
            String applicant = cardApp.getApplicantId();
            Team team = teamDao.queryTeamByCaptainIdAndCardId(applicant, cardId);
            if(team == null){
                // 没有匹配的队伍, 说明是个人整租
                persons.add(data.new Person(
                        userDao.queryUserByOpenId(applicant),
                        cardApp.getApplicantId()));
            }else{
                ArrayList<GetApplyResponse.Data.Team.Member> paramsMembers = new ArrayList<>();
                GetApplyResponse.Data.Team paramsTeam = data.new Team(team.getTeamId(), null, cardApp.getApplicationId());
                // 有匹配的队伍, 说明是多人整租
                for(User user: team.getMembers()){
                    paramsMembers.add(paramsTeam.new Member(user.getUserId(), user.getAvatar()));
                }
                paramsTeam.setMembers(paramsMembers.toArray(new GetApplyResponse.Data.Team.Member[0]));
                teams.add(paramsTeam);
            }
        }
        data.setPerson(persons.toArray(new GetApplyResponse.Data.Person[0]));
        data.setTeams(teams.toArray(new GetApplyResponse.Data.Team[0]));
        response.setData(data);
        response.setResult(1);
        response.setMessage("");
        return response;
    }

    public DefaultResponse processApply(String applyId){
        Application app = applicationDao.queryApplicationByAppId(applyId);
        app.setStatus(true);
        String cardId = "";
        int type = -1;
        // 先改变申请状态
        if(app instanceof CardApplication){
            // 你的申请被card主处理了
            CardApplication ca = (CardApplication)app;
            applicationDao.processApplication(ca);
            cardId = ca.getCardId();
            type = 5;
            cardDao.setStatusTrue(cardId);
        }else if(app instanceof TeamApplication){
            // 你的入队申请被处理了
            TeamApplication ta = (TeamApplication)app;
            Team team = teamDao.queryTeamByTeamId(ta.getTeamId());
            if(team.isMaxMember()){
                return new DefaultResponse(0, "该合租队伍已满");
            }
            applicationDao.processApplication(ta);
            cardId = teamDao.queryCardIdFromTeamId(ta.getTeamId());
            type = 2;
            teamDao.addUserToTeam(ta.getApplicantId(), ta.getTeamId());
            // FIXME
        }
        // 改变log状态
        logDao.setTrueByApplyId(applyId);
        // 发送消息
        // todo 通过微信模板消息发送提醒
        wxMessageUtil.sendMessage();
        // over
        // 加入消息记录
        logDao.addLog(new Log(-1, type, cardId, applyId, app.getTargetId(), app.getApplicantId(), false));
        return new DefaultResponse(1, "");
    }
}