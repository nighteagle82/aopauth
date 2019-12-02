package cn.ne.aopauth.model.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
@Component
public class BindOrganize implements Serializable {

    private Long id;
    private Integer orgCode;
    private String authUrl;
    private Date createDate;
    private Integer isDelete;

}
