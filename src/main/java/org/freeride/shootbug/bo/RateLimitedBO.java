package org.freeride.shootbug.bo;

import lombok.Builder;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/2 14:21
 */

@Data
@Builder
public class RateLimitedBO {
    private int requestNum;
    private String key;
    private long period;
    private TimeUnit unit;
}
