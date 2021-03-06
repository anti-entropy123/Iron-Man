package com.mbry.IronMan.Service;

import java.util.ArrayList;

import com.mbry.IronMan.BusinessObject.Log;
import com.mbry.IronMan.BusinessObject.Team;
import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.BusinessObject.Application.TeamApplication;
import com.mbry.IronMan.Dao.ApplicationDao;
import com.mbry.IronMan.Dao.LogDao;
import com.mbry.IronMan.Dao.TeamDao;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.RequestBody.WxMessageRequestBody.WxMessageRequestBody;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.TeamResponseBody.GetTeamResponse;
import com.mbry.IronMan.Utils.DateUtil;
import com.mbry.IronMan.Utils.WxMessageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TeamDao teamDao;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private WxMessageUtil wxMessageUtil;

    public GetTeamResponse getTeam(String cardId, String userId) {
        Team[] _teams = teamDao.getTeamsByCardId(cardId);
        Team myTeam = null;
        ArrayList<Team> teams = new ArrayList<>();
        for(Team team: _teams){
            if(userId.equals(team.getCaptainId())){
                myTeam = team;
            }else{
                teams.add(team);
            }
        }
        if(myTeam == null){
            myTeam = new Team();
            myTeam.setTeamId("");
        }
        return new GetTeamResponse(myTeam, teams.toArray(new Team[0]), 1, "");
    }

    public DefaultResponse createTeam(String userId, String cardId, int maxNum){
        User member = userDao.queryUserByOpenId(userId);
        Team team = new Team(null, userId, cardId, maxNum, dateUtil.getDate(), new User[]{member});
        teamDao.createTeam(team);
        return new DefaultResponse(1, "");
    }

    public DefaultResponse joinTeam(String teamId, String userId){
        Team team = teamDao.queryTeamByTeamId(teamId);
        if(team.isMaxMember()){
            return new DefaultResponse(0, "该合租队伍已满");
        }    
        String targetUserId = team.getCaptainId();
        String applyId = applicationDao.createApplication(new TeamApplication(
            null, 
            userId, 
            team.getCaptainId(),
            false, 
            dateUtil.getDate(), 
            teamId));
        logDao.addLog(new Log(-1, 1, team.getCardId(), applyId, userId, targetUserId, false));
        // todo 发送微信通知
        wxMessageUtil.sendMessage(
            new WxMessageRequestBody().new Data(),
            targetUserId
        );
        return new DefaultResponse(1, "");
    }

    public DefaultResponse leaveTeam(String teamId, String userId){
        String cardId = teamDao.queryCardIdFromTeamId(teamId);
        if(teamDao.queryCaptainIdFromTeamId(teamId).equals(userId)){
            // 如果是队长, 那么队伍解散
            User[] members = teamDao.queryTeamByTeamId(teamId).getMembers();
            teamDao.deleteTeamByTeamId(teamId);
            for(User member: members){
                // todo 发送微信模板消息
                wxMessageUtil.sendMessage(
                    new WxMessageRequestBody().new Data(),
                    member.getUserId()
                );
                logDao.addLog(new Log(-1, 4, cardId, "", userId, member.getUserId(), false));
            }
        }else{
            // 如果不是队长, 则自己退出队伍
            String targetUserId = teamDao.queryCaptainIdFromTeamId(teamId);
            teamDao.deleteUserFromTeam(userId, teamId);
            // todo 发送微信模板消息
            wxMessageUtil.sendMessage(
                new WxMessageRequestBody().new Data(),
                targetUserId     
            );
            logDao.addLog(new Log(-1, 3, teamDao.queryCardIdFromTeamId(teamId), "", userId, targetUserId, false));
        }
        return new DefaultResponse(1, "");
    }
}