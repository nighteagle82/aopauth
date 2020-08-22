package cn.ne.aopauth.utils.jwt;

import cn.ne.aopauth.model.domain.AccountDomain;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private JWTUtil() {}

    /**
     * 生成token
     *
     * @param jwtParams 生成jwt所需的属性
     * @return 生成的token
     */
    public static String createToken(JWTParams jwtParams) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, jwtParams.getTimeout());

        Map<String, Object> jwtTypMap = new HashMap<>();
        jwtTypMap.put("alg", jwtParams.getAlg());
        jwtTypMap.put("typ", jwtParams.getTyp());

        return JWT.create()
                .withHeader(jwtTypMap)                                                  // 配置头
                .withExpiresAt(instance.getTime())                                      // 配置token超时时间
                .withClaim(jwtParams.getPayloadName(), jwtParams.getPayloadJSON())      // 配置截体
                .sign(jwtParams.getAlgorithm());          // 配置签名算法
    }

    public static boolean verify(String token, Algorithm algorithm) {
        try {
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (SignatureVerificationException e) {    // 验签异常
//            e.printStackTrace();
            return false;
        } catch (TokenExpiredException e) {   // Token过期
//            e.printStackTrace();
            return false;
        } catch (AlgorithmMismatchException e) { // 算法不一致
//            e.printStackTrace();
            return false;
        } catch (JWTVerificationException e) {  // JWT验证异常【抛出该异常即可】
//            e.printStackTrace();
            return false;
        } catch (Exception e) {  // 其他异常
//            e.printStackTrace();
            return false;
        }
    }

    public static DecodedJWT getDecodedJWT(String token,Algorithm algorithm) {
        return JWT.require(algorithm).build().verify(token);
    }
}
