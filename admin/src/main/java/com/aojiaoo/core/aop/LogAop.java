package com.aojiaoo.core.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;

public class LogAop {

    /**
     * 前置通知
     * @param joinPoint
     */
    public void beforMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("ValidationAspect this method "+methodName+" begin. param<"+ args+">");
    }
    /**
     * 后置通知（无论方法是否发生异常都会执行,所以访问不到方法的返回值）
     * @param joinPoint
     */
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("ValidationAspect this method "+methodName+" end.");
    }
    /**
     * 返回通知（在方法正常结束执行的代码）
     * 返回通知可以访问到方法的返回值！
     * @param joinPoint joinPoint
     * @param result result
     */
    public void afterReturnMethod(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("ValidationAspect this method "+methodName+" end.result<"+result+">");
    }
    /**
     * 异常通知（方法发生异常执行的代码）
     * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
     * @param joinPoint
     * @param ex
     */
    public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("ValidationAspect this method "+methodName+" end.ex message<"+ex+">");
    }
}