package com.mbry.IronMan.Security;

import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.Mapper.AdmInfoMapper;
import com.mbry.IronMan.entity.AdmInfoEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AdmInfoMapper admInfoMapper;

    /**
     * 这里的 by username, 实际上是 by openId
     */
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userDao.queryUserByOpenId(id);
        if(user != null){
            return new JwtUser(id, user.getNickname(), "common");
        }
        AdmInfoEntity adm = admInfoMapper.queryAdm(id);
        if(adm != null){
            return new JwtUser(id, "管理员", "super");
        }
        return null;
    }
}