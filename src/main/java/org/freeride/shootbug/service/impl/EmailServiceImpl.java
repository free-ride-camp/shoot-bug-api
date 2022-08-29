package org.freeride.shootbug.service.impl;

import org.freeride.shootbug.service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/8/4 17:27
 */

@Component
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleTextMail(String mailMessage) {
    }
}
