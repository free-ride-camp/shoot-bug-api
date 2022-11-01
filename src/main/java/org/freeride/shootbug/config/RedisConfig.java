package org.freeride.shootbug.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/2 15:13
 */

@Configuration
public class RedisConfig {

    @Bean
    public RedisScript<Boolean> bucketRateLimiterScript() {
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>();
        script.setResultType(Boolean.class);
        script.setLocation(new ClassPathResource("redis/script/bucket_rate_limiter.lua"));
        return script;
    }
}
