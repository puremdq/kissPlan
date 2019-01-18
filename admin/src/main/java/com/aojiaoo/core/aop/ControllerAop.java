package com.aojiaoo.core.aop;

import com.aojiaoo.core.annotations.Log;
import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.core.common.ResponseCode;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.modules.sys.entity.OperateLog;
import com.aojiaoo.modules.sys.service.OperateLogService;
import com.aojiaoo.utils.ClassUtils;
import com.aojiaoo.utils.WebUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

public class ControllerAop {

    /**
     * 拦截 @RequestMapping 注解的 controller
     */
    @Autowired
    private OperateLogService logService;


    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        OperateLog operateLog = new OperateLog();
        Object object = null;

        //方法通知前获取时间,为什么要记录这个时间呢？当然是用来计算模块执行时间的
        long start = System.currentTimeMillis();
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的放参数类型

        MethodSignature msig = (MethodSignature) pjp.getSignature();
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Method method = target.getClass().getMethod(methodName, parameterTypes);
        // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
        if (method.isAnnotationPresent(Log.class)) {
            Log logAnnotation = method.getAnnotation(Log.class);
            operateLog.setOperateName(logAnnotation.name());
            operateLog.setOperateDesc(logAnnotation.desc());
        }

        operateLog.setMethodName(methodName);
        operateLog.setParameters(ClassUtils.getParameterStringMap(pjp.getArgs()).toString());
        operateLog.setRequestType(request.getMethod());
        operateLog.setUrl(request.getRequestURI());
        operateLog.setIp(WebUtils.getIp(request));
        operateLog.setExecuteTime(WebUtils.getIp(request));


        //校验参数  如果错误抛出异常
        BindingResult bindingResult = assertHasNoError(pjp.getArgs());
        if (bindingResult != null) {
            ServerResponse serverResponse = ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
            long end = System.currentTimeMillis();
            operateLog.setExecuteTime((end - start) + "ms");
            operateLog.setOperateDesc("非法的参数:" + bindingResult.getAllErrors().get(0).getDefaultMessage());
            operateLog.setIsSuccess(String.valueOf(GlobalProperties.FAIL));
            logService.save(operateLog);
            return serverResponse;
        }


        try {
            object = pjp.proceed();
            operateLog.setIsSuccess(String.valueOf(GlobalProperties.TRUE));
        } catch (Throwable e) {
            e.printStackTrace();
            operateLog.setIsSuccess(String.valueOf(GlobalProperties.FAIL));
        } finally {
            long end = System.currentTimeMillis();
            operateLog.setExecuteTime((end - start) + "ms");
            logService.save(operateLog);
        }
        return object;
    }


    private BindingResult assertHasNoError(Object[] args) {

        if (args == null || args.length < 1) {
            return null;
        }

        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    return (BindingResult) arg;
                }
            }
        }
        return null;
    }


}