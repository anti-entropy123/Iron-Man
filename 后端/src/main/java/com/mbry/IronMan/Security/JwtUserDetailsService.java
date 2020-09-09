package com.mbry.IronMan.Security;

import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.Dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    //@Autowired
    //private UserDao userDao;

    /**
     * 这里的 by username, 实际上是 by openId
     */
    @Override
    public UserDetails loadUserByUsername(String openId) throws UsernameNotFoundException {
        //User user = userDao.queryUserByOpenId(openId);
        //if(user == null){
        if("user" == null){
            throw new UsernameNotFoundException(String.format("No user found with openId '%s'.", openId));
        }else{
            return new JwtUser(openId, "user.getNickname()");
        }
    }
}