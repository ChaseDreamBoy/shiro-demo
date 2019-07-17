package com.xh.exception;

import com.xh.enums.ExceptionEnums;
import com.xh.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(AuthorizationException.class)
    public Result handleRRException(AuthorizationException e) {
        return Result.customResultEnum(ExceptionEnums.PERMISSION_DENIED);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(UnknownAccountException.class)
    public Result handleUnknownAccountException(UnknownAccountException e) {
        return new Result(ExceptionEnums.UNKNOWN_ACCOUNT.getCode(), e.getMessage(), "");
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    public Result handleIncorrectCredentialsException(IncorrectCredentialsException e) {
        return Result.customResultEnum(ExceptionEnums.PASSWORD_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        return Result.customResultEnum(ExceptionEnums.PATH_NOT_FOUNT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result handlerUnAuthorizedException(UnauthorizedException e) {
        log.error("Intercept to uniform anomaly UnAuthorizedException,", e);
        return Result.customResultEnum(ExceptionEnums.PERMISSION_DENIED);
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return Result.customResultEnum(ExceptionEnums.CUSTOM_ERROR);
    }

}
