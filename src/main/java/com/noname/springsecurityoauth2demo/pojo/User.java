package com.noname.springsecurityoauth2demo.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/18 10:20
 * @description ：自定义的User,同样继承了UserDetails，与Security提供的User一样，只是稍微强化了一下。但是默认生成的
 * 代码的几个函数返回值都是false,必须改成true,几个get函数都需要更改，还不如用他自带的，意义不大，完全跟着视频走
 * @modified By：
 * @version:
 */
public class User implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorityList;

    public User(String username, String password, List<GrantedAuthority> authorityList) {
        this.username = username;
        this.password = password;
        this.authorityList = authorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
