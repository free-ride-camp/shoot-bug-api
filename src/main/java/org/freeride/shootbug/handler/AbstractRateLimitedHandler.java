package org.freeride.shootbug.handler;

import org.freeride.shootbug.bo.RateLimitedBO;
import org.freeride.shootbug.handler.type.RateLimitedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/2 14:28
 */

public abstract class AbstractRateLimitedHandler {

    private StringRedisTemplate stringRedisTemplate;
    private RedisScript<Boolean> bucketRateLimiterScript;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public void setScript(RedisScript<Boolean> bucketRateLimiterScript) {
        this.bucketRateLimiterScript = bucketRateLimiterScript;
    }

    public abstract boolean supports(RateLimitedType type);

    public abstract boolean isRequestLimited(RateLimitedBO rateLimitedInfo);

    protected boolean doCheck(String key, int limitedNum, long periodDuration, TimeUnit unit) {
        long durationMillis = TimeUnit.MILLISECONDS.convert(periodDuration, unit);
        return stringRedisTemplate.execute(bucketRateLimiterScript, List.of(key), String.valueOf(ZonedDateTime.now().toInstant().toEpochMilli()), String.valueOf(limitedNum), String.valueOf(durationMillis));
    }
}
