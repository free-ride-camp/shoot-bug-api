package org.freeride.shootbug.service.impl;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import org.freeride.shootbug.dto.request.RegisterRequest;
import org.freeride.shootbug.entity.db.User;
import org.freeride.shootbug.entity.redis.VerificationCode;
import org.freeride.shootbug.repository.db.UserMapper;
import org.freeride.shootbug.repository.redis.VerificationCodeRepository;
import org.freeride.shootbug.service.EmailService;
import org.freeride.shootbug.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.Optional;

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
    private EmailService emailService;

    @Override
    public User registerUser(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public String sendVerificationCode(Integer userId) {
        if (verificationCodeRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "请稍后获取验证码");
        }
        String code = RandomStringUtils.randomNumeric(4);
        VerificationCode verificationCode = new VerificationCode(userId, code);
        verificationCodeRepository.save(verificationCode);
        String email = Optional.ofNullable(userMapper.findUserById(userId)).map(User::getEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("用户%s的email不存在", userId)));
        emailService.sendVerificationCode(email, code);
        return code;
    }
}
