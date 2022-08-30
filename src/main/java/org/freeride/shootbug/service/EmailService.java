package org.freeride.shootbug.service;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/8/4 17:01
 */

public interface EmailService {
    /**
     * 发送验证码到指定邮箱
     * @param to
     * @param verificationCode
     */
    void sendVerificationCode(String to, String verificationCode);
}
