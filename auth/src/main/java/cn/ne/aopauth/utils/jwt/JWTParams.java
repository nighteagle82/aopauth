package cn.ne.aopauth.utils.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JWTParams implements Serializable {
    private String signKey = "123456";

    private String alg="HMAC256";   //  算法类型，默认HMAC256
    private String typ = "JWT";     //  JWT类型，默认JWT
    private String payloadName;    //  载体名称
    private String payloadJSON;    //  载体内容
    private Integer timeout;       //  超时时间
    private Algorithm algorithm;   //  数据类型

    public void setPayloadName(String payloadName) {
        this.payloadName = payloadName;
    }

    public void setPayloadJSON(String payloadJSON) {
        this.payloadJSON = payloadJSON;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String getPayloadName() {
        return payloadName;
    }

    public String getPayloadJSON() {
        return payloadJSON;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getSignKey() {
        return signKey;
    }
}
