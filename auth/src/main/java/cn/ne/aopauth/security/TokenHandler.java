package cn.ne.aopauth.security;


import cn.ne.aopauth.model.domain.OrganizeDomain;
import cn.ne.aopauth.service.auth.IAuthService;
import cn.ne.aopauth.utils.MD5;
import cn.ne.aopauth.utils.redis.RedisHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;


/***
 *  本包中需要实现的功能是整个工程的核心处在。
 *  出于对正规项目的保密，所以将核心功能简化为了两个基础的方法用于演示整个流程。
 *  整体的功能以及流程，与我们线上的正式版完全相同。
 *  【老实说Spring Security确实也挺繁杂，所以为简化起见，在DEMO中剔除了所有关于SpringSecurity的依赖与覆写。】
 *  只要理解了本DEMO的原理，更多的细节处可以自行发挥想像。
 */


public class TokenHandler {

    @Autowired
    private RedisHandler redisHandler;

    @Autowired
    private IAuthService authService;

    public String createToken(OrganizeDomain organizeDomain) {
        String accessToken = null;
        if (organizeDomain != null) {
            // 验证code与Serct是否存在
            if (this.authService.isCodeAndSerctExists(organizeDomain) > 0) {
                // 两次MD5生成accessToken
                accessToken = MD5.toMD5(MD5.toMD5(organizeDomain.getOrgCode() + organizeDomain.getSerctKey(), 32), 32);
                // 保存至redis
                long timeout = 1000 * 60 * 60;     // 1000毫秒 * 60秒 * 60分钟 = 1小时
                if (this.redisHandler.exists(accessToken)){
                    if (this.redisHandler.getExpire(accessToken)<= (1000 * 60 * 5)){
                        this.redisHandler.setExpire(accessToken,timeout, TimeUnit.MILLISECONDS);
                    }
                } else {
                    this.redisHandler.set(accessToken, JSON.toJSONString(organizeDomain), timeout);
                }
            } else {
                accessToken = "error";
            }
        }
        return accessToken;
    }


    public boolean tokenCheck(String token, String requestURI) {
        if (!StringUtils.isEmpty(token) && !StringUtils.isEmpty(requestURI)) {
            String tokenJSON = (String) this.redisHandler.get(token);
            if (!StringUtils.isEmpty(tokenJSON)) {
                JSONObject json = JSONObject.parseObject(tokenJSON);
                Integer orgCode = json.getInteger("orgCode");
                if (orgCode != null && orgCode != 0) {
                    List<String> authUrls = this.authService.getBindOrganizeList(orgCode);
                    for (String authUrl : authUrls) {
                        if (requestURI.equalsIgnoreCase(authUrl)) {
                            return true;
                        }
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }
}

