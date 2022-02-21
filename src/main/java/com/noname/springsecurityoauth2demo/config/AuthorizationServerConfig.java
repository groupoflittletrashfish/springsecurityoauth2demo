package com.noname.springsecurityoauth2demo.config;

import com.noname.springsecurityoauth2demo.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/18 10:32
 * @description ：授权服务器配置
 * @modified By：
 * @version:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserService userService;
//    @Autowired
//    @Qualifier("redisTokenStore")
//    private TokenStore tokenStore;

    @Resource(name = "jwtTokenStore")
    private TokenStore tokenStore;
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Resource
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(delegates);


        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userService)
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(enhancerChain);
    }

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //※※※※※※※※   获取密钥需要身份认证，使用单点登录时必须配置     ※※※※※※※※※
        security.tokenKeyAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("admin")
                .secret(passwordEncoder.encode("112233"))
                .accessTokenValiditySeconds(3600)
                //※※※※※※※※※  还可以一下自动刷新令牌的有效时间    ※※※※※※※※※※
                .refreshTokenValiditySeconds(864000)
                //※※※※※※※※※  登录跳转成功后跳转到客户端的登录页   ※※※※※※※※※※
                .redirectUris("http://localhost:8083/login")
                //※※※※※※※※※  开启自动授权，就再也不需要认为去点接受授权了    ※※※※※※
                .autoApprove(true)
                .scopes("all")
                //※※※※※※※※※  authorization_code好像必须得加，不加就会报错   ※※※※※※※※※
                .authorizedGrantTypes("password", "refresh_token","authorization_code");
    }
}
