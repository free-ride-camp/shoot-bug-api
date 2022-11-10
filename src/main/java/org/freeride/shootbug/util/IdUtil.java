package org.freeride.shootbug.util;

import java.util.UUID;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/11/10 14:59
 */

public class IdUtil {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
