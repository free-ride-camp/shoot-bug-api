package org.freeride.shootbug.handler.impl;

import org.freeride.shootbug.bo.RateLimitedBO;
import org.freeride.shootbug.handler.AbstractRateLimitedHandler;
import org.freeride.shootbug.handler.type.RateLimitedType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/11/2 15:03
 */

@Component
public class UserRateLimitedHandler extends AbstractRateLimitedHandler {

    @Override
    public boolean supports(RateLimitedType type) {
        return RateLimitedType.USER.equals(type);
    }

    @Override
    public boolean isRequestLimited(RateLimitedBO rateLimitedInfo) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.doCheck(String.format("%s:user:%s", rateLimitedInfo.getKey(), principal.toString()), rateLimitedInfo.getRequestNum(), rateLimitedInfo.getPeriod(), rateLimitedInfo.getUnit());
    }
}
