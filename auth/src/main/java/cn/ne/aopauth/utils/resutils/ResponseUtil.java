package cn.ne.aopauth.utils.resutils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseUtil {
    public static Map<String, Object> setResponseMap(String code, String msg, ErrorStatus status, Object data) {
        Map <String, Object> msgMap = new HashMap<>();
        msgMap.put("code", code);
        msgMap.put("msg", msg);
        msgMap.put("status", status);
        if (data != null){
            msgMap.put("data", data);
        }
        return msgMap;
    }


    public static List<ObjectError> setErrors(BindingResult result){
        List<ObjectError> errors = new ArrayList<>();
        if (result.hasErrors()){
            errors = result.getAllErrors();
            if (errors != null || errors.size() != 0){
                return errors;
            }
        }
        return null;
    }

}
