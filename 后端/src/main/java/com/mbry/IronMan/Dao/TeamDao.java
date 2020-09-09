package com.mbry.IronMan.Dao;

import org.springframework.stereotype.Service;
import com.mbry.IronMan.BusinessObject.Team;

@Service
public interface TeamDao {
    /**
     * 根据(出租)帖子id查此页面所有的队伍
     * @param cardId
     * @return 
     */
    public Team[] getTeamsByCardId(String cardId);

    /**
     * 
     * @param userId
     * @param cardId
     * @return
     */
    public boolean createTeam(Team team);

    /**
     * 
     * @param userId
     * @param teamId
     * @return
     */
    public boolean addUserToTeam(String userId, String teamId);

    /**
     * 
     * @param userId
     * @param teamId
     * @return
     */
    public boolean deleteUserFromTeam(String userId, String teamId);

    /**
     * 根据teamId查询该team所属的card的id
     * @param teamId
     * @return
     */
    public String queryCardIdFromTeamId(String teamId);

    /**
     * 根据teamId查询该team的队长的id
     * @param teamIds
     * @return
     */
    public String queryCaptainIdFromTeamId(String teamId);

    /**
     * 查询队长id和cardId与参数相符的队伍
     * @param captainId 队长的userId
     * @param cardId    cardId
     * @return          若没有相应的team, 则返回null
     */
    public Team queryTeamByCaptainIdAndCardId(String captainId, String cardId);    
}