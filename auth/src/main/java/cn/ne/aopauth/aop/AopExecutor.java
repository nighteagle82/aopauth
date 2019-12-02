package cn.ne.aopauth.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopExecutor {

    @Autowired
    private AopHandler aopHandler;

    // 定义切面路径
    private final static String EXECUTION_ALL_CONTROLLER = "@annotation(cn.ne.aopauth.annotation.MonitorRequest)";

    @Pointcut(EXECUTION_ALL_CONTROLLER)
    public void executeService() {}

    @Around(value = "executeService()")
    public String aroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        String result = null;
        try {
            result = this.aopHandler.executAroundAdvice(proceedingJoinPoint);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
