package cn.ne.aopauth.model.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Component
public class AccountDomain implements Serializable {

    private String username;
    private String password;

}
