package org.freeride.shootbug.advice;

import lombok.extern.slf4j.Slf4j;
import org.freeride.shootbug.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/8/30 16:19
 */

@RestControllerAdvice
@Slf4j
public class ApiExceptionAdvice {

    private static final String GLOBAL_EXCEPTION_RESPONSE_TEXT = "服务器异常";

    private static final String AUTHENTICATION_EXCEPTION_RESPONSE_TEXT = "用户身份认证失败，请先登录";

    private static final String BAD_CREDENTIALS_EXCEPTION_RESPONSE_TEXT = "用户名或密码错误";

    private static final String AUTHORIZATION_EXCEPTION_RESPONSE_TEXT = "权限不足，不可操作";

    /**
     * 全局异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ApiResponse<Object> handleGlobalException(HttpServletRequest request, Exception e) {
        log.error("全局异常捕获：", e);
        return switch (e) {
            case ResponseStatusException rse -> ApiResponse.fail(rse.getMessage(), rse.getStatus());
            case BadCredentialsException bce -> ApiResponse.fail(BAD_CREDENTIALS_EXCEPTION_RESPONSE_TEXT, HttpStatus.UNAUTHORIZED);
            case AccessDeniedException ade -> ApiResponse.fail(AUTHORIZATION_EXCEPTION_RESPONSE_TEXT, HttpStatus.FORBIDDEN);
            case AuthenticationException ae -> ApiResponse.fail(AUTHENTICATION_EXCEPTION_RESPONSE_TEXT, HttpStatus.UNAUTHORIZED);
            default -> ApiResponse.fail(GLOBAL_EXCEPTION_RESPONSE_TEXT, HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }
}
