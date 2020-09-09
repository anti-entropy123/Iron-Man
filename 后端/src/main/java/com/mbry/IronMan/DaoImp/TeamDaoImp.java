package com.mbry.IronMan.DaoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbry.IronMan.BusinessObject.Team;
import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.Dao.TeamDao;
import com.mbry.IronMan.Mapper.TeamMapper;
import com.mbry.IronMan.Mapper.TeamMemberMapper;
import com.mbry.IronMan.Mapper.UserMapper;
import com.mbry.IronMan.entity.TeamEntity;
import com.mbry.IronMan.entity.UserEntity;

@Service
public class TeamDaoImp implements TeamDao {
	
	@Autowired
	TeamMapper teamMapper;
	@Autowired
	TeamMemberMapper teamMemberMapper;
	@Autowired
	UserMapper userMapper;

	@Override
	public Team[] getTeamsByCardId(String cardId) {
		TeamEntity[] teamEntitys = teamMapper.queryTeamsByCardId(cardId);
		List<Team> teams = new ArrayList<Team>();
		for (int i = 0; i < teamEntitys.length; i++) {
			teams.add(this.setTeamByTeamEntity(teamEntitys[i]));
		}
		return teams.toArray(new Team[teams.size()]);
	}

	@Override
	public boolean createTeam(Team team) {
		try {
			TeamEntity teamEntity = new TeamEntity();
			teamEntity.setTeamId(team.getTeamId());
			teamEntity.setCaptainId(team.getCaptainId());
			teamEntity.setCardId(team.getCardId());
			teamEntity.setMaxNum(team.getMaxNum());
			teamEntity.setDate(team.getDate());
			for (int i = 0; i < team.getMembers().length; i++) {
				teamMemberMapper.insertTeamMember(team.getTeamId(), team.getMembers()[i].getUserId());
			}
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addUserToTeam(String userId, String teamId) {
		try {
			teamMemberMapper.insertTeamMember(teamId, userId);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUserFromTeam(String userId, String teamId) {
		try {
			teamMemberMapper.deleteTeamMember(teamId, userId);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String queryCardIdFromTeamId(String teamId) {
		return teamMapper.queryTeamByTeamId(teamId).getCardId();
	}

	@Override
	public String queryCaptainIdFromTeamId(String teamId) {
		return teamMapper.queryCaptainIdByTeamId(teamId);
	}

	@Override
	public Team queryTeamByCaptainIdAndCardId(String captainId, String cardId) {
		TeamEntity teamEntity = teamMapper.queryTeamByCapAndCard(captainId, cardId);
		return this.setTeamByTeamEntity(teamEntity);
	}

	/**
	 * 将一个TeamEntity转化为Team
	 * @param teamEntity
	 * @return
	 */
	private Team setTeamByTeamEntity(TeamEntity teamEntity) {
		String[] members = teamMemberMapper.queryUserByTeamId(teamEntity.getTeamId());
		List<User> users = new ArrayList<User>();
		for (int j = 0; j < members.length; j++) {
			UserEntity userEntity = userMapper.queryUserByIdForTeam(members[j]);
			User user = new User();
			user.setUserId(userEntity.getUserId());
			user.setNickName(userEntity.getNickName());
			user.setAvatar(userEntity.getAvatar());
			users.add(user);
		}
		Team team = new Team(
				teamEntity.getTeamId(),
				teamEntity.getCaptainId(),
				teamEntity.getCardId(),
				teamEntity.getMaxNum(),
				teamEntity.getDate(),
				users.toArray(new User[users.size()]));
		return team;
	}

}
