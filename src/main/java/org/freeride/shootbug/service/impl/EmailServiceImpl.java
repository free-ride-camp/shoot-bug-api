package org.freeride.shootbug.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.freeride.shootbug.service.EmailService;
import org.freeride.shootbug.util.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/8/4 17:27
 */

@Component
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private SimpleMailMessage templateMail;

    @Value("${application.mail.templates.verification-code}")
    private String verificationCodeTemplate;

    @Override
    @Async
    public void sendVerificationCode(String to, String verificationCode) {
        SimpleMailMessage mail = new SimpleMailMessage(templateMail);
        mail.setText(String.format(verificationCodeTemplate, verificationCode, Constant.VERIFICATION_CODE_TTL_SECONDS));
        mail.setTo(to);
        try {
            mailSender.send(mail);
        } catch (MailException me) {
            log.error(String.format("发送email至%s时出现异常：", to), me);
        }
    }
}
