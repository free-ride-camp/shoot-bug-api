package org.freeride.shootbug.annotation;

import org.freeride.shootbug.handler.type.RateLimitedType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/2 13:44
 */

@Target(ElementType.METHOD)
@Inherited
public @interface RateLimited {

    /**
     * 限流方法的唯一标识
     *
     * @return
     */
    String value();

    /**
     * 限流时间
     *
     * @return
     */
    long period();

    /**
     * 限流时间单位
     *
     * @return
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;

    /**
     * 限流请求数量
     *
     * @return
     */
    int requestNum();

    /**
     * 限流类型
     *
     * @return
     */
    RateLimitedType type() default RateLimitedType.APPLICATION;
}
