package com.aojiaoo.core.aop;

import com.aojiaoo.core.annotations.Log;
import com.aojiaoo.modules.sys.entity.OperateLog;
import com.aojiaoo.modules.sys.service.OperateLogService;
import com.aojiaoo.utils.GlobalProperties;
import com.aojiaoo.utils.WebUntil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class LogAop {


    @Autowired
    private OperateLogService logService;


    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        OperateLog operateLog = new OperateLog();
        Object object = null;
        // 获得被拦截的方法
        Method method = null;

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

        MethodSignature msig = (MethodSignature) pjp.getSignature();
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        method = target.getClass().getMethod(methodName, parameterTypes);
        // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
        if (method.isAnnotationPresent(Log.class)) {
            Log logAnnotation = method.getAnnotation(Log.class);
            operateLog.setOperateName(logAnnotation.name());
            operateLog.setOperateDesc(logAnnotation.desc());
        }

        operateLog.setMethodName(methodName);
        operateLog.setParameters(getParameterString(pjp.getArgs()));
        operateLog.setRequestType(request.getMethod());
        operateLog.setUrl(request.getRequestURI());
        operateLog.setIp(WebUntil.getIp(request));
        operateLog.setExecuteTime(WebUntil.getIp(request));

        try {
            object = pjp.proceed();
            operateLog.setIsSuccess(String.valueOf(GlobalProperties.SUCCESS));
        } catch (Throwable e) {
            e.printStackTrace();
            operateLog.setIsSuccess(String.valueOf(GlobalProperties.FAIL));
        } finally {
            long end = System.currentTimeMillis();
            operateLog.setExecuteTime(String.valueOf(end - start) + "ms");
            logService.save(operateLog);
        }
        return object;
    }

    private String getParameterString(Object[] parameters) {
        if (parameters == null) {
            return null;
        }

        Map<String, String> parametersMap = new LinkedHashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            parametersMap.put("arg" + i, parameters[i].toString());
        }
        return parametersMap.toString();
    }

}