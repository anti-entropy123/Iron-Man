package com.mbry.IronMan.Mapper;

import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mbry.IronMan.entity.UserEntity;

@SpringBootTest
public class UserMapperTests {

	@Autowired
	UserMapper userMapper;
	
	@Test
	public void test() {
		UserEntity u = userMapper.queryUserByIdForTeam("1");
		System.out.println(u.getAvatar());
		System.out.println(u.getIntroduction());
		System.out.println(u.getMobile());
		System.out.println(u.getNickName());
		System.out.println(u.getUserId());
		System.out.println(u.getSex());	
	}
	
	@Test
	public void queryUserById() {
		UserEntity u = userMapper.queryUserById("1");
		System.out.println(u.getAvatar());
		System.out.println("introduction" + u.getIntroduction());
		System.out.println(u.getMobile());
		System.out.println(u.getNickName());
		System.out.println(u.getUserId());
		System.out.println(u.getSex());
	}
	
	@Test
	public void queryUsers() {
		UserEntity[] u = userMapper.queryUsers(0, 2, null, null, null);
		System.out.println(u.length);
		System.out.println(u[0].getAvatar());
		System.out.println(u[0].getIntroduction());
		System.out.println(u[0].getMobile());
		System.out.println(u[0].getNickName());
		System.out.println(u[0].getUserId());
		System.out.println(u[0].getSex());
	}
	
	@Test
	public void insertUser() {
		UserEntity u = new UserEntity("2","2","2",false,"2","2");
		userMapper.insertUser(u);
	}
	
	@Test
	public void updateUser() {
		UserEntity u = new UserEntity("3","2","2",false,"2","2");
		userMapper.updateUser(u);
	}
	
	@Test
	public void updateUserMobile() {
		userMapper.updateUserMobile("1", "23");
	}

}
