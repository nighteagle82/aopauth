package cn.ne.aopauth.aop;

import cn.ne.aopauth.security.TokenHandler;
import cn.ne.aopauth.utils.resutils.ErrorStatus;
import cn.ne.aopauth.utils.resutils.ResponseUtil;
import cn.ne.aopauth.utils.resutils.SystemConstants;
import com.alibaba.fastjson.JSON;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class AopHandler {

    Logger logger = LoggerFactory.getLogger(AopHandler.class);

    @Autowired
    private TokenHandler tokenHandler;

    public String executAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Map<String, Object> msgMap = new HashMap<>();

        logger.info("目标方法名：{}", proceedingJoinPoint.getSignature().getName());

        //获取到请求的属性
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到请求对象
        HttpServletRequest request = attributes.getRequest();
        String requestURI = request.getRequestURI();

        String token = request.getHeader("access_token");
        boolean tokenCheck = tokenHandler.tokenCheck(token, requestURI);
        if (!tokenCheck) {  // 取反
            msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.FAILURE, new ErrorStatus(
                    SystemConstants.E001, SystemConstants.getMessage().get(SystemConstants.E001) + "Token无效,或无权访问。"
            ), null);
            return JSON.toJSONString(msgMap);
        }
        Object proceed = proceedingJoinPoint.proceed();
        return (String) proceed;
    }



}


// 在此列出以下常用的切面方法可供实际开发中参考
//System.out.println("取得被切入的方法名【全类名，包含返回值类型，且方法带小括号】：" + joinPoint.getSignature());
//System.out.println("取得被切入的方法名【仅方法名，不带方法小括号】：" + joinPoint.getSignature().getName());
//System.out.println("取得被切入的类名【全类名，包含class前缀】：" + joinPoint.getSignature().getDeclaringType());

//System.out.println("取得被切入的类名【全类名，可用于反射】：" + joinPoint.getSignature().getDeclaringTypeName());
//System.out.println("取得切入点对象实例【全类名对象实例，反射时可直接接收】" + joinPoint.getTarget());
