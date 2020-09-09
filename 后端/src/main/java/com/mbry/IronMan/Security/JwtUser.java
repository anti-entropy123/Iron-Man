package com.mbry.IronMan.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = -6370169303352749569L;
    private final String openId;
    private final String username;
    private Collection<? extends GrantedAuthority> anthorities;

    public JwtUser(
            String openId,
            String username){
        this.openId = openId;
        this.username = username;
        List<String> temp = new ArrayList<>();
        temp.add("common");
        this.anthorities = temp.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.anthorities;
    }

    @JsonIgnore
    public String getOpenId(){
        return this.openId;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return "none";
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // 账户是否未过期
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        // * may need modify
        return true;
    }

    // 账户是否未锁定
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        // * may need modify
        return true;
    }

    // 密码是否未过期
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        // * may need modify
        return true;
    }

    // 账户是否激活
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        // * may need modify
        return true;
    }

    // @JsonIgnore
    // public String getLastPasswordResetDate() {
    //     return this.lastPasswordResetDate;
    // }

    // @JsonIgnore
    // public String getEmail(){
    //     return this.email;
    // }
}