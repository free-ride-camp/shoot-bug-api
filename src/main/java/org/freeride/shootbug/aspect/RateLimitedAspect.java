package org.freeride.shootbug.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.freeride.shootbug.annotation.RateLimited;
import org.freeride.shootbug.bo.RateLimitedBO;
import org.freeride.shootbug.exception.ApiException;
import org.freeride.shootbug.manager.RateLimitedHandlerManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/2 14:06
 */

@Aspect
@Component
public class RateLimitedAspect {

    @Resource
    private RateLimitedHandlerManager rateLimitedHandlerManager;

    @Before("@annotation(rateLimited)")
    public void beforeLimitedMethod(JoinPoint joinPoint, RateLimited rateLimited) {
        RateLimitedBO rateLimitedInfo = RateLimitedBO.builder().key(rateLimited.value()).unit(rateLimited.unit()).requestNum(rateLimited.requestNum()).period(rateLimited.period()).build();
        boolean isLimited = rateLimitedHandlerManager.getHandlerByType(rateLimited.type()).isRequestLimited(rateLimitedInfo);
        if (isLimited) {
            throw new ApiException(HttpStatus.TOO_MANY_REQUESTS, "请稍后再操作");
        }
    }
}
