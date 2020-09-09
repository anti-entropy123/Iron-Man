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
}