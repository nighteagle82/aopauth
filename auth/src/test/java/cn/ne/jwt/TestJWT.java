package cn.ne.jwt;

import cn.ne.aopauth.model.domain.AccountDomain;
import cn.ne.aopauth.utils.jwt.JWTParams;
import cn.ne.aopauth.utils.jwt.JWTUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TestJWT {

    private static final String signKey = "123456";


    @Test
    public void testCreatJwt() {


        Map<String, Object> jwtTypMap = new HashMap<>();
        jwtTypMap.put("alg", "HMAC256");
        jwtTypMap.put("typ", "JWT");

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 30);       // 30分钟

        AccountDomain account = new AccountDomain().setUsername("ne").setPassword("123456");

        String token = JWT.create()
                .withHeader(jwtTypMap)                                          // 配置头
                .withExpiresAt(instance.getTime())                              // 配置token超时时间
                .withClaim("account", JSON.toJSONString(account))       // 配置截体
                .sign(Algorithm.HMAC256(signKey));// 配置签名算法
        System.out.println(token);
    }

    //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTgwMzcwOTksImFjY291bnQiOiJ7XCJwYXNzd29yZFwiOlwiMTIzNDU2XCIsXCJ1c2VybmFtZVwiOlwibmVcIn0ifQ.X-MxvjuQJGNRTeu0VZ8tjGZ5Vg2kAwDWZ2iZqPmRwSM

    @Test
    public void testGetAccount() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTgwMzcwOTksImFjY291bnQiOiJ7XCJwYXNzd29yZFwiOlwiMTIzNDU2XCIsXCJ1c2VybmFtZVwiOlwibmVcIn0ifQ.X-MxvjuQJGNRTeu0VZ8tjGZ5Vg2kAwDWZ2iZqPmRwSM\n";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(signKey)).build(); // 创建验证对象
        DecodedJWT verify = jwtVerifier.verify(token.trim());

        System.out.println("头部 >>> " + verify.getHeader());
        System.out.println("载体 >>> " + verify.getPayload());
        System.out.println("签名 >>> " + verify.getSignature());
        System.out.println("token >>> " + verify.getToken());
        System.out.println("算法 >>> " + verify.getAlgorithm());
        System.out.println("JWT格式 >>> " + verify.getType());

        String expiresAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(verify.getExpiresAt());
        System.out.println("过期时间 >>> " + expiresAt);

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now);
        System.out.println(!expiresAt.equalsIgnoreCase(now) ? "有效" : "过期");

        String accountJSON = verify.getClaim("account").asString();
        AccountDomain accountDomain = JSONObject.parseObject(accountJSON, AccountDomain.class);
        System.out.println(accountDomain);


        // 无用
//        System.out.println(verify.getAudience());
//        System.out.println(verify.getContentType());
//        System.out.println(verify.getId());
//        System.out.println(verify.getIssuedAt());
//        System.out.println(verify.getIssuer());
//        System.out.println(verify.getKeyId());
//        System.out.println(verify.getNotBefore());
//        System.out.println(verify.getSubject());

    }


    @Test
    public void testCreatJwtWithUtil() {
        AccountDomain account = new AccountDomain().setUsername("ne").setPassword("123456");

        JWTParams params = new JWTParams();
        params.setTimeout(30);
        params.setPayloadName("account");
        params.setPayloadJSON(JSON.toJSONString(account));
        params.setAlgorithm(Algorithm.HMAC256(params.getSignKey()));
        String token = JWTUtil.createToken(params);
        System.out.println(token);
    }

//    eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTgwODEwMjEsImFjY291bnQiOiJ7XCJwYXNzd29yZFwiOlwiMTIzNDU2XCIsXCJ1c2VybmFtZVwiOlwibmVcIn0ifQ.FrRvJXkfosj-KBc-mTrW0Wfc-MbtBH4E6AyiT6QNXeU

    @Test
    public void testGetAccountWithUtil() {
        JWTParams params = new JWTParams();

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTgwODEwMjEsImFjY291bnQiOiJ7XCJwYXNzd29yZFwiOlwiMTIzNDU2XCIsXCJ1c2VybmFtZVwiOlwibmVcIn0ifQ.FrRvJXkfosj-KBc-mTrW0Wfc-MbtBH4E6AyiT6QNXeU";
        if (JWTUtil.verify(token.trim(), Algorithm.HMAC256(params.getSignKey()))) {
            DecodedJWT decodedJWT = JWTUtil.getDecodedJWT(token.trim(), Algorithm.HMAC256(params.getSignKey()));
            String account = decodedJWT.getClaim("account").asString();
            System.out.println(account);
        } else {
            System.out.println("Token错误!");
        }
    }


}