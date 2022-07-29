package org.freeride.shootbug.entity.redis;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/7/29 18:00
 */

@RedisHash(value = "VerifyCode")
public class VerifyCode {

    private String code;
    @TimeToLive
    private Long expiration;

    public VerifyCode(String code) {
        this.code = code;
        //todo
    }
}
