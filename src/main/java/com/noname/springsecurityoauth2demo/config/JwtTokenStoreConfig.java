package com.noname.springsecurityoauth2demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/21 9:40
 * @description ：
 * @modified By：
 * @version:
 */

@Configuration
public class JwtTokenStoreConfig {


    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("test_key");
        return accessTokenConverter;
    }


    /**
     * ※※※※※※※※   将自定义的扩展器定义成一个bean作为单例     ※※※※※※※※※※
     * @return
     */
    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

}
