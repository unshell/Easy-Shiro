package com.unshell.easyshiro.common.aspect;

import com.unshell.easyshiro.common.annotation.ControllerEndpoint;
import com.unshell.easyshiro.common.utils.EasyUtil;
import com.unshell.easyshiro.monitor.service.ILogService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class ControllerEndpointAspect extends BaseAspectSupport {
    private final ILogService logService;

    @Pointcut("@annotation(com.unshell.easyshiro.common.annotation.ControllerEndpoint)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result;
        Method targetMethod = resolveMethod(point);
        ControllerEndpoint annotation = targetMethod.getAnnotation(ControllerEndpoint.class);
        String operation = annotation.operation();
        String operator = EasyUtil.getCurrentLoginName();
        long start = System.currentTimeMillis();
        try {
            result = point.proceed();
            if (StringUtils.isNotBlank(operation)) {
                RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) attributes;
                String ip = StringUtils.EMPTY;
                if (servletRequestAttributes != null) {
                    ip = servletRequestAttributes.getRequest().getRemoteAddr();
                }
                logService.saveLog(point, targetMethod, ip, operator, operation, annotation.isPrivacy(), start);
            }
            return result;
        } catch (Throwable throwable) {
            String message = annotation.message();
            String exceptionMessage = throwable.getMessage();
            String error = EasyUtil.containChinese(exceptionMessage) ? message + "，" + exceptionMessage : message;
            throw new RuntimeException(error);
        }
    }
}