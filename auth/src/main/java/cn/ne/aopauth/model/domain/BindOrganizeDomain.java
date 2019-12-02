package cn.ne.aopauth.model.domain;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@Component
public class BindOrganizeDomain implements Serializable {

    private Integer orgCode;
    private List<String> authUrl;

}
