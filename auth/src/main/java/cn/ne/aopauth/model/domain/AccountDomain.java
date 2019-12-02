package cn.ne.aopauth.model.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class AccountDomain implements Serializable {

    private String username;
    private String password;

}
