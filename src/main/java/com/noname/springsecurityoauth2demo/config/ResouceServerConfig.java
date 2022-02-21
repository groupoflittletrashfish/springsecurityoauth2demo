package com.noname.springsecurityoauth2demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/18 11:04
 * @description ：资源服务器配置
 * @modified By：
 * @version:
 */
@Configuration
@EnableResourceServer
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //所有请求都将做权限认证
                .anyRequest().authenticated()
                .and()
                //指定路径放行
                .requestMatchers().antMatchers("/user/**");
    }
}
