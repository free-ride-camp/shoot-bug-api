package org.freeride.shootbug.entity.redis;

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
    private Integer userId;

    private String code;

    public VerificationCode(Integer userId, String code) {
        this.userId = userId;
        this.code = code;
    }
}