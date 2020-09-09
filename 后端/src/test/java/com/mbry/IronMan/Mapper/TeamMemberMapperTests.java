package com.mbry.IronMan.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeamMemberMapperTests {

	@Autowired
	TeamMemberMapper team;
	
	@Test
	public void insertTeamMember() {
		team.insertTeamMember("12", "12");
		team.insertTeamMember("22", "22");
		team.insertTeamMember("32", "32");
		team.insertTeamMember("42", "42");
		team.insertTeamMember("52", "52");
	}
	
	@Test
	public void deleteTeamMember() {
		team.deleteTeamMember("12", "12");
	}
	
	@Test
	public void queryUserByTeamId() {
		String[] s = team.queryUserByTeamId("22");
		System.out.println(s[0]);
	}
	
	@Test
	public void queryTeamByUserId() {
		String s = team.queryTeamByUserId("32");
		System.out.println(s);
	}
}
