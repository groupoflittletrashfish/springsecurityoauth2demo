package com.noname.springsecurityoauth2demo.service;

import com.noname.springsecurityoauth2demo.pojo.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/18 10:13
 * @description ：
 * @modified By：
 * @version:
 */
@Service
public class UserService implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("123456");
        return new User("admin", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
