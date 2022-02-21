package com.noname.oauth2client01demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/21 16:08
 * @description ：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取用户信息
     *
     * @param authentication
     * @return
     */
    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication;
    }

}
