package cn.ne.aopauth.model.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
@Component
public class Organize implements Serializable {

    private Long id;
    private String orgName;
    private String orgPwd;
    private Integer orgCode;
    private String serctKey;
    private Integer effect; // 有效期
    private Date effectDate;
    private Integer isDelete;

}
