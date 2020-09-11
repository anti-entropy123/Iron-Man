package com.mbry.IronMan.DaoImp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.Mapper.ApplicationMapper;
import com.mbry.IronMan.Mapper.CardMapper;
import com.mbry.IronMan.Mapper.TeamApplicationMapper;
import com.mbry.IronMan.Mapper.TeamMapper;
import com.mbry.IronMan.Mapper.TeamMemberMapper;
import com.mbry.IronMan.Mapper.UserMapper;
import com.mbry.IronMan.entity.UserEntity;
import com.mbry.IronMan.global.Global;

@Service
public class UserDaoImp implements UserDao {

	@Autowired
	TeamMapper teamMapper;
	@Autowired
	TeamMemberMapper teamMemberMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	ApplicationMapper applicationMapper;
	@Autowired
	TeamApplicationMapper teamApplicationMapper;
	@Autowired
	CardMapper cardMapper;
	
	@Override
	public User queryUserByOpenId(String openId) {
		UserEntity userEntity = userMapper.queryUserById(openId);
		if (userEntity == null) {
			return null;
		}
		return this.getUserFromEntity(userEntity);
	}

	@Override
	public User queryCaptainByTeamId(String teamId) {
		String captainId = teamMapper.queryCaptainIdByTeamId(teamId);
		if (captainId == null) {
			return null;
		}
		UserEntity userEntity = userMapper.queryUserById(captainId);
		if (userEntity == null) {
			return null;
		}
		return this.getUserFromEntity(userEntity);
	}

	@Override
	public User[] queryUsersByTeamId(String teamId) {
		String captainId = teamMapper.queryCaptainIdByTeamId(teamId);
		String[] userIds = teamMemberMapper.queryUserByTeamId(teamId);
		List<String> userIdList = new ArrayList<String>();
		Collections.addAll(userIdList, userIds);
		userIdList.remove(captainId);
		userIdList.add(0, captainId);
		List<User> users = new ArrayList<User>();
		for(String s: userIdList) {
			UserEntity userEntity = userMapper.queryUserById(s);
			if (userEntity != null) {
				users.add(this.getUserFromEntity(userEntity));
			}
		}
		return users.toArray(new User[users.size()]);
	}

	@Override
	public User queryUserByApplyId(String applicationId) {
		String userId = applicationMapper.queryApplicantIdById(applicationId);
		if (userId == null) {
			userId = teamApplicationMapper.queryApplicantIdById(applicationId);
		}
		UserEntity userEntity = userMapper.queryUserById(userId);
		if (userEntity == null) {
			return null;
		}
		return this.getUserFromEntity(userEntity);
	}

	@Override
	public User queryUserByCardId(String cardId) {
		String userId = cardMapper.queryUserIdById(cardId);
		UserEntity userEntity = userMapper.queryUserById(userId);
		if (userEntity == null) {
			return null;
		}
		return this.getUserFromEntity(userEntity);
	}

	@Override
	public boolean registerUser(User user) {
		try {
			userMapper.insertUser(this.getEntityFromBO(user));
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUserDataByUserId(User user) {
		try {
			userMapper.updateUser(this.getEntityFromBO(user));
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean bindMobileNumberByUserId(String userId, String mobilePhone) {
		try {
			userMapper.updateUserMobile(userId, mobilePhone);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User[] queryUsers(int page, String name, String userId, String mobileNumber) {
		int startIndex = (page - 1) * Global.pageSize;
		UserEntity[] userEntitys = userMapper.queryUsers(startIndex,  Global.pageSize, name, userId, mobileNumber);
		List<User> users = new ArrayList<User>();
		for (UserEntity userEntity: userEntitys) {
			users.add(this.getUserFromEntity(userEntity));
		}
		return users.toArray(new User[users.size()]);
	}
	
	/**
	 * 
	 * @param userEntity
	 * @return
	 */
	private User getUserFromEntity(UserEntity userEntity) {
		User user = new User();
		user.setUserId(userEntity.getUserId());
		user.setNickName(userEntity.getNickName());
		user.setAvatar(userEntity.getAvatar());
		user.setSex(userEntity.getSex());
		user.setMobile(userEntity.getMobile());
		user.setIntroduction(userEntity.getIntroduction());
		return user;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	private UserEntity getEntityFromBO(User user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(user.getUserId());
		userEntity.setNickName(user.getNickname());
		userEntity.setAvatar(user.getAvatar());
		userEntity.setSex(user.getSex());
		userEntity.setMobile(user.getMobile());
		userEntity.setIntroduction(user.getIntroduction());
		return userEntity;
	}

}
