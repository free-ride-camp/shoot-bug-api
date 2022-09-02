package org.freeride.shootbug.handler.impl;

import org.freeride.shootbug.bo.RateLimitedBO;
import org.freeride.shootbug.handler.RateLimitedHandler;
import org.freeride.shootbug.handler.type.RateLimitedType;
import org.springframework.stereotype.Component;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/2 14:36
 */

@Component
public class ApplicationRateLimitedHandler implements RateLimitedHandler {

    @Override
    public boolean supports(RateLimitedType type) {
        return RateLimitedType.APPLICATION.equals(type);
    }

    @Override
    public boolean isRequestLimited(RateLimitedBO rateLimitedInfo) {
        return false;
    }
}
