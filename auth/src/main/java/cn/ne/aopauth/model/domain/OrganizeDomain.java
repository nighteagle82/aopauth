package cn.ne.aopauth.model.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class OrganizeDomain implements Serializable {

    private String orgName;
    private String orgPwd;
    private Integer orgCode;
    private String serctKey;
//    private Integer effect; // 有效期

}
