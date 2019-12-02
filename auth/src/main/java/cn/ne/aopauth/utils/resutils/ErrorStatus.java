package cn.ne.aopauth.utils.resutils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ErrorStatus implements Serializable {

    private String errorCode;
    private String errorMsg;

}
