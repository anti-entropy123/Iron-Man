package com.mbry.IronMan.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mbry.IronMan.entity.TeamEntity;

@SpringBootTest
public class TeamMapperTests {

	@Autowired
	TeamMapper teamMapper;
	
	@Test
	public void getTeamsByCardId() {
		TeamEntity[] te = teamMapper.queryTeamsByCardId("1");
		System.out.println("telength" + te.length);
		System.out.println(te[1].getMaxNum());
	}
	
	@Test
	public void getTeamByTeamId() {
		TeamEntity te = teamMapper.queryTeamByTeamId("1");
		System.out.println(te.getMaxNum());
	}
	
	@Test
	public void insertTeam() {
		TeamEntity te = new TeamEntity(null,"4","4",4,"4");
		teamMapper.insertTeam(te);
	}
	
	@Test
	public void queryCaptainId() {
		System.out.println(teamMapper.queryCaptainIdByTeamId("1"));
	}
}
