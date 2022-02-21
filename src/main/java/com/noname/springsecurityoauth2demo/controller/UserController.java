package com.noname.springsecurityoauth2demo.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author ：liwuming
 * @date ：Created in 2022/2/18 11:21
 * @description ：
 * @modified By：
 * @version:
 */

@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 获取当前用户
     *
     * @param authentication
     * @return
     */
    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) {
        /*
            Postman中的的 Authorization其实就是Header,只不过是有固定写法，可以在postman中的Header中查看到
            如 Authorization 选择 Bearer Token类型，那么实际在Header的固定写法是 Authorization:Bearer XXXXXXXX。注意有空格
            所以JWT是可以通过头部信息的Authorization中获取的，然后将字符串分割开来获取正确的值
         */
        String head = request.getHeader("Authorization");
        String token = head.substring(head.indexOf("bearer") + 7);
        //私钥一定要和授权服务器配置中的相同
        return Jwts.parser().setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token).getBody();
    }
}
