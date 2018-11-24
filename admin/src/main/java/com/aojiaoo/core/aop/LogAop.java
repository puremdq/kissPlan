package com.aojiaoo.core.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.aojiaoo.modules.sys.entity.OperateLog;
import com.aojiaoo.modules.sys.service.OperateLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class LogAop {


    @Autowired
    private OperateLogService logService;

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    public void beforMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("ValidationAspect this method " + methodName + " begin. param<" + args + ">");
    }

    /**
     * 后置通知（无论方法是否发生异常都会执行,所以访问不到方法的返回值）
     *
     * @param joinPoint
     */
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("ValidationAspect this method " + methodName + " end.");
    }

    /**
     * 返回通知（在方法正常结束执行的代码）
     * 返回通知可以访问到方法的返回值！
     *
     * @param joinPoint joinPoint
     * @param result    result
     */
    public void afterReturnMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("ValidationAspect this method " + methodName + " end.result<" + result + ">");
    }

    /**
     * 异常通知（方法发生异常执行的代码）
     * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
     *
     * @param joinPoint
     * @param ex
     */
    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("ValidationAspect this method " + methodName + " end.ex message<" + ex + ">");
    }


    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        OperateLog sysLog = new OperateLog();

        //常见日志实体对象
        //获取登录用户账户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //方法通知前获取时间,为什么要记录这个时间呢？当然是用来计算模块执行时间的
        long start = System.currentTimeMillis();
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        object = pjp.proceed();

//        if (null != method) {
//            // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
//            if (method.isAnnotationPresent(SystemLog.class)) {
//                SystemLog systemlog = method.getAnnotation(SystemLog.class);
//                log.setMODULE(systemlog.module());
//                log.setMETHOD(systemlog.methods());
//                try {
//                    object = pjp.proceed();
//                    long end = System.currentTimeMillis();
//                    //将计算好的时间保存在实体中
//                    log.setRSPONSE_DATA(""+(end-start));
//                    log.setCOMMITE("执行成功！");
//                    //保存进数据库
//                    logservice.saveLog(log);
//                } catch (Throwable e) {
//                    // TODO Auto-generated catch block
//                    long end = System.currentTimeMillis();
//                    log.setRSPONSE_DATA(""+(end-start));
//                    log.setCOMMITE("执行失败");
//                    logservice.saveLog(log);
//                }
//            } else {//没有包含注解
//                object = pjp.proceed();
//            }
//        } else { //不需要拦截直接执行
//            object = pjp.proceed();
//        }
        return object;
    }

}