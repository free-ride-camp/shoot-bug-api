package org.freeride.shootbug.handler;

import org.freeride.shootbug.bo.RateLimitedBO;
import org.freeride.shootbug.handler.type.RateLimitedType;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/2 14:28
 */

public interface RateLimitedHandler {
    boolean supports(RateLimitedType type);

    boolean isRequestLimited(RateLimitedBO rateLimitedInfo);
}
