package org.freeride.shootbug.handler.impl;

import org.freeride.shootbug.bo.RateLimitedBO;
import org.freeride.shootbug.handler.AbstractRateLimitedHandler;
import org.freeride.shootbug.handler.type.RateLimitedType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/11/1 15:23
 */

@Component
public class IpAbstractRateLimitedHandler extends AbstractRateLimitedHandler {
    @Override
    public boolean supports(RateLimitedType type) {
        return RateLimitedType.IP.equals(type);
    }

    @Override
    public boolean isRequestLimited(RateLimitedBO rateLimitedInfo) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        String key = String.format("%s:ip:%s", rateLimitedInfo.getKey(), ip);
        return super.doCheck(key, rateLimitedInfo.getRequestNum(), rateLimitedInfo.getPeriod(), rateLimitedInfo.getUnit());
    }
}
