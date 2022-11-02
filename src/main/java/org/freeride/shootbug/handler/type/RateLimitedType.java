package org.freeride.shootbug.handler.type;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/2 14:31
 */

public enum RateLimitedType {
    /**
     * 应用内的所有同类请求都计入限流
     */
    APPLICATION,
    /**
     * 单个IP的同类请求计入限流
     */
    IP,
    /**
     * 单个用户的同类请求计入限流（需要登录的接口，才能使用）
     */
    USER
}
