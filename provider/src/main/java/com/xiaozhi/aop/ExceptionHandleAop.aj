//package com.xiaozhi.aop;
//
//import com.xiaozhi.exception.ServiceException;
//import com.xiaozhi.result.resultImpl.ServiceResult;
//import com.xiaozhi.result.BaseResult;
//import com.xiaozhi.result.ResultCode;
//import com.xiaozhi.utils.JsonUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.IllegalFormatException;
//
///**
// * Created by 小智 on 2017/4/22 0022.
// *
// * Service层异常统一捕获和日志记录
// */
//@Aspect
//@Component
//@Slf4j
//public class ExceptionHandleAop{
//    private SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
//
//    // 切入点表达式按需配置
//    @Pointcut("execution(* com.xiaozhi.serviceImpl.*.*(..)))")
//    private static void myPointcut(){
//    }
//
//    //日志记录
//    @Before("execution(* com.xiaozhi.serviceImpl.*.*(..)))")
//    public void before(JoinPoint joinPoint){
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        StringBuilder logData = new StringBuilder();
//        logData.append(dateFormat.format(new Date()))
//                .append(" ").append(className)
//                .append("@")
//                .append(methodName)
//                .append(" , params: ");
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args) {
//            logData.append(JsonUtils.object2json(arg) + ", ");
//        }
//        System.out.println("-------------------before调用前--------------------");
//        log.info(logData.toString());
//    }
//
//    //异常处理
//    @AfterThrowing(value = "ExceptionHandleAop.myPointcut()", throwing = "e")
//    public BaseResult afterThrowing(JoinPoint joinPoint, Throwable e){
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        StringBuilder logData = new StringBuilder();
//        logData.append(dateFormat.format(new Date()))
//                .append(" ").append(className)
//                .append("@")
//                .append(methodName)
//                .append(" , params: ");
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args) {
//            logData.append(JsonUtils.object2json(arg) + ", ");
//        }
//        if (e instanceof IllegalFormatException || e instanceof IllegalArgumentException) {
//            log.warn("参数不合法" + logData, e);
//            return new ServiceResult(ResultCode.PARAMETER_ILLEGAL);
//        } else if (e instanceof NullPointerException) {
//            log.error("空指针异常" + logData, e);
//            return new ServiceResult(ResultCode.PARAMETER_ILLEGAL);
//        } else if (e instanceof ServiceException) {
//            log.error("数据库查询失败" + logData, e);
//            return new ServiceResult(ResultCode.getResultCode(((ServiceException) e).getErrorCode()));
//        } else {
//            log.error("数据库查询失败" + logData, e);
//            return new ServiceResult(ResultCode.FIELD);
//        }
//    }
//
//    //
//    @Around(value = "ExceptionHandleAop.myPointcut()")
//    public Object around(ProceedingJoinPoint point) throws Throwable{
//        System.out.println("-------------------around调用前--------------------");
//        String className = point.getTarget().getClass().getSimpleName();
//        if (className.endsWith("Impl")) {
//            className = className.substring(0, className.length() - 4);
//        }
//        String methodName = point.getSignature().getName();
//        String serviceMethod = className + "." + methodName;
//
//        Object result;
//        long begin = System.currentTimeMillis();
//        try {
//            log.info("==== Received start serviceMethod[{}]. params[{}].", serviceMethod,
//                    Arrays.toString(point.getArgs()));
//            result = point.proceed();
//            log.info("==== Received end serviceMethod[{}], params[{}], result[{}], cost[{}].", serviceMethod,
//                    Arrays.toString(point.getArgs()), result, System.currentTimeMillis() - begin);
//            return result;
//        } catch (Throwable e) {
//            if (e instanceof IllegalArgumentException) {
//                result = new ServiceResult<>(ResultCode.PARAMETER_ILLEGAL);
//                log.info(
//                        "==== Received end IllegalArgumentException. serviceMethod[{}], params[{}], result[{}],cost[{}].",
//                        serviceMethod,
//                        Arrays.toString(point.getArgs()), result, System.currentTimeMillis() - begin, e);
//            } else if (e instanceof ServiceException) {
//                result = new ServiceResult<>(e);
//                log.warn("==== Received end BizException. serviceMethod[{}], params[{}], result[{}],cost[{}].",
//                        serviceMethod,
//                        Arrays.toString(point.getArgs()), result, System.currentTimeMillis() - begin, e);
//            } else {
//                result = new ServiceResult<>(ResultCode.FIELD);
//                log.error("==== Received end SYSTEM_ERROR. serviceMethod[{}], params[{}], result[{}],cost[{}].",
//                        serviceMethod,
//                        Arrays.toString(point.getArgs()), result, System.currentTimeMillis() - begin, e);
//            }
//        }
//
//        return result;
//    }
//
//}
