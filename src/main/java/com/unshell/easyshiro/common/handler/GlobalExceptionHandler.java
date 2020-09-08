package com.unshell.easyshiro.common.handler;

import com.unshell.easyshiro.common.entity.EasyResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public EasyResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new EasyResponse().code(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public EasyResponse handleRuntimeException(RuntimeException e) {
        log.error("系统错误", e);
        return new EasyResponse().code(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public EasyResponse handleUnauthorizedException(UnauthorizedException e) {
        log.error("无操作权限：" + e.getMessage());
        return new EasyResponse().code(HttpStatus.UNAUTHORIZED).message("用户无操作权限");
    }
}