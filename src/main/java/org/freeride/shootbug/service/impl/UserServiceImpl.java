package org.freeride.shootbug.service.impl;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import org.freeride.shootbug.annotation.RateLimited;
import org.freeride.shootbug.dto.request.RegisterRequest;
import org.freeride.shootbug.po.db.User;
import org.freeride.shootbug.po.db.type.RoleEnum;
import org.freeride.shootbug.po.redis.VerificationCode;
import org.freeride.shootbug.exception.ApiException;
import org.freeride.shootbug.handler.type.RateLimitedType;
import org.freeride.shootbug.dao.db.RoleMapper;
import org.freeride.shootbug.dao.db.UserMapper;
import org.freeride.shootbug.dao.db.UserRoleMapper;
import org.freeride.shootbug.dao.redis.VerificationCodeRepository;
import org.freeride.shootbug.service.EmailService;
import org.freeride.shootbug.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private RoleMapper roleMapper;

    @Override
    @Transactional
    public User registerUser(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        VerificationCode verificationCode = verificationCodeRepository.findById(email).orElseThrow(() -> new ApiException("验证码已失效，请重新发送"));
        if (!verificationCode.getCode().equals(registerRequest.getVerificationCode())) {
            throw new ApiException("验证码已失效");
        }
        User user = this.saveNewUser(registerRequest);
        this.addUserRoleMapping(user);
        //注册流程完成，删除验证码
        verificationCodeRepository.deleteById(email);
        return user;
    }

    private void addUserRoleMapping(User user) {
        RoleEnum normalRole = RoleEnum.NORMAL;
        List<RoleEnum> roles = List.of(normalRole);
        user.setRoles(roles);
        Integer roleId = roleMapper.getIdByRoleName(normalRole);
        userRoleMapper.insertUserRole(user.getId(), roleId);
    }

    private User saveNewUser(RegisterRequest registerRequest) {
        User user = new User();
        BeanUtils.copyProperties(registerRequest, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //若email重复，终止注册流程
        try {
            userMapper.insertUser(user);
        } catch (DuplicateKeyException dke) {
            throw new ApiException(String.format("电子邮箱%s已被使用", registerRequest.getEmail()));
        }
        return user;
    }

    @Override
    @RateLimited(value = "sendVerificationCode", period = 30, requestNum = 1, unit = TimeUnit.SECONDS, type = RateLimitedType.IP)
    public String sendVerificationCode(String email) {
        if (verificationCodeRepository.existsById(email)) {
            throw new ApiException("请稍后获取验证码");
        }
        String code = RandomStringUtils.randomNumeric(4);
        VerificationCode verificationCode = new VerificationCode(email, code);
        verificationCodeRepository.save(verificationCode);
        emailService.sendVerificationCode(email, code);
        return code;
    }
}
