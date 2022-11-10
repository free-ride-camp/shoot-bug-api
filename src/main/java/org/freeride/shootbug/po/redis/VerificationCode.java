package org.freeride.shootbug.po.redis;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/7/29 18:00
 */

@Getter
@RedisHash(value = "VerificationCode", timeToLive = 300)
public class VerificationCode {

    @Id
    private final String email;

    private final String code;

    public VerificationCode(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
