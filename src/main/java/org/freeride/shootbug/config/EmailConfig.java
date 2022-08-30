package org.freeride.shootbug.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/8/5 9:33
 */

@Configuration
public class EmailConfig {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${application.mail.subject}")
    private String emailSubject;

    @Bean
    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(emailSubject);
        simpleMailMessage.setFrom(emailFrom);
        return simpleMailMessage;
    }
}
