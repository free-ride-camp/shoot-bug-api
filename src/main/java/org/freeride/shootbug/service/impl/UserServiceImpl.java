package org.freeride.shootbug.service.impl;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import org.freeride.shootbug.dto.request.RegisterRequest;
import org.freeride.shootbug.entity.db.User;
import org.freeride.shootbug.entity.redis.VerificationCode;
import org.freeride.shootbug.repository.db.UserMapper;
import org.freeride.shootbug.repository.redis.VerificationCodeRepository;
import org.freeride.shootbug.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/7/29 17:43
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private VerificationCodeRepository verificationCodeRepository;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public String sendVerificationCode(Integer userId) {
        String code = RandomStringUtils.randomNumeric(4);
        VerificationCode verificationCode = new VerificationCode(userId, code);
        verificationCodeRepository.save(verificationCode);
        //todo 将验证码发送到邮箱
        return code;
    }
}
