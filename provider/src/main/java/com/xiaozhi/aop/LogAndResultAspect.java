package com.xiaozhi.aop;

import com.xiaozhi.exception.ServiceException;
import com.xiaozhi.result.ResultCode;
import com.xiaozhi.result.resultImpl.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

/**
 * Created by 小智 on 2017/4/24 0024.
 */
@Aspect
@Slf4j
public class LogAndResultAspect{
    public void before(){
        System.out.println("-------------------before调用前--------------------");
    }

    public Object around(ProceedingJoinPoint point){
        System.out.println("-------------------around调用前--------------------");
        String className = point.getTarget().getClass().getSimpleName();
        if (className.endsWith("Impl")) {
            className = className.substring(0, className.length() - 4);
        }
        String methodName = point.getSignature().getName();
        String serviceMethod = className + "." + methodName;

        Object result;
        long begin = System.currentTimeMillis();
        try {
            log.info("==== Received start serviceMethod[{}]. params[{}].", serviceMethod,
                    Arrays.toString(point.getArgs()));
            result = point.proceed();
            log.info("==== Received end serviceMethod[{}], params[{}], result[{}], cost[{}].", serviceMethod,
                    Arrays.toString(point.getArgs()), result, System.currentTimeMillis() - begin);
            return result;
        } catch (Throwable e) {
            if (e instanceof IllegalArgumentException) {
                result = new ServiceResult<>(ResultCode.PARAMETER_ILLEGAL);
                log.info(
                        "==== Received end IllegalArgumentException. serviceMethod[{}], params[{}], result[{}],cost[{}].",
                        serviceMethod,
                        Arrays.toString(point.getArgs()), result, System.currentTimeMillis() - begin, e);
            } else if (e instanceof ServiceException) {
                result = new ServiceResult<>((ServiceException) e);
                log.warn("==== Received end ServiceException. serviceMethod[{}], params[{}], result[{}],cost[{}].",
                        serviceMethod,
                        Arrays.toString(point.getArgs()), result, System.currentTimeMillis() - begin, e);
            } else {
                result = new ServiceResult<>(ResultCode.FIELD);
                log.error("==== Received end SYSTEM_ERROR. serviceMethod[{}], params[{}], result[{}],cost[{}].",
                        serviceMethod,
                        Arrays.toString(point.getArgs()), result, System.currentTimeMillis() - begin, e);
            }
        }

        return result;
    }

}
