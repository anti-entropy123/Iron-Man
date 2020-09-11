package com.mbry.IronMan.dao;

import com.mbry.IronMan.BusinessObject.Team;
import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.Dao.TeamDao;
import com.mbry.IronMan.Mapper.TeamMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeamDaoTests {

    @Autowired
    TeamDao teamDao;

    @Test
    void getTeamsByCardId() {
        Team[] teams = teamDao.getTeamsByCardId("String cardId1");
        for (Team team: teams) {
            System.out.println(team.getCaptainId());
            if (team.getMembers().length > 0) {
                System.out.println(team.getMembers()[0].getUserId());
            }
        }

    }

    /**
     * 
     * @param userId
     * @param cardId
     * @return
     */
    @Test
    void createTeam() {
       
        User user1 = new User();
        user1.setUserId("team1_user1");
        User user2 = new User();
        user2.setUserId("team1_user2");

        User[] user = new User[] {user1, user2};
        Team team = new Team(
            null,
            "String captainId1",
            "String cardId1",
            2,
            "String date1",
            user
        );
        teamDao.createTeam(team);
    }

    @Test
    void addUserToTeam() {
        teamDao.addUserToTeam("userId", "teamId");
    }

    @Test
    void deleteUserFromTeam() {
        teamDao.deleteUserFromTeam("userId", "teamId");
    }

    /**
     * 根据teamId查询该team所属的card的id
     * @param teamId
     * @return
     */
    @Test
    void queryCardIdFromTeamId() {
        System.out.println(teamDao.queryCardIdFromTeamId("123124"));
    }

    @Test
    void queryCaptainIdFromTeamId() {
        System.out.println(teamDao.queryCardIdFromTeamId("f9bdbf69f1c611ea9e601cbfc09c197a"));
    }

    @Test
    void queryTeamByCaptainIdAndCardId() {
        Team team = teamDao.queryTeamByCaptainIdAndCardId("3", "1");
        System.out.println(team.getTeamId());
    }

    @Test
    void queryTeamByTeamId() {
        Team team = teamDao.queryTeamByTeamId("5de26385f40511ea80305dd7211e33a5");
        System.out.println(team.getCaptainId());
    }

    @Test
    void deleteTeamByTeamId() {
        teamDao.deleteTeamByTeamId("5de26385f40511ea80305dd7211e33a5");
    }
}