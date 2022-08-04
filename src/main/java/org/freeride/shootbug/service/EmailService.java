package org.freeride.shootbug.service;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/8/4 17:01
 */

public interface EmailService {
    /**
     * 发送文字email
     * @param mailMessage
     */
    void sendSimpleTextMail(String mailMessage);
}
