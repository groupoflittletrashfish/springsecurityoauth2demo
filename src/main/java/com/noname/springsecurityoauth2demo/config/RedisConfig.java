package com.noname.springsecurityoauth2demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/18 15:36
 * @description ：
 * @modified By：
 * @version:
 */
@Configuration
public class RedisConfig {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

//    @Bean
    public TokenStore redisTokenStore() {
        //TokenStore和RedisTokenStore都是Security Oauth包下的，应该都是已经集成好的。将连接直接丢给这个类，它会自动将token保存到Redis中
        return new RedisTokenStore(redisConnectionFactory);
    }
}
