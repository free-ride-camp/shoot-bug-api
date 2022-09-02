package org.freeride.shootbug.service.impl;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import org.freeride.shootbug.dto.request.RegisterRequest;
import org.freeride.shootbug.entity.db.User;
import org.freeride.shootbug.entity.db.type.RoleEnum;
import org.freeride.shootbug.entity.redis.VerificationCode;
import org.freeride.shootbug.exception.ApiException;
import org.freeride.shootbug.repository.db.RoleMapper;
import org.freeride.shootbug.repository.db.UserMapper;
import org.freeride.shootbug.repository.db.UserRoleMapper;
import org.freeride.shootbug.repository.redis.VerificationCodeRepository;
import org.freeride.shootbug.service.EmailService;
import org.freeride.shootbug.service.UserService;
import org.freeride.shootbug.annotation.RateLimited;
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
    @RateLimited(value = "sendVerificationCode", period = 30, requestNum = 1, unit = TimeUnit.SECONDS)
    public String sendVerificationCode(String email) {
        //todo 通过专门的限流机制，防止短时间内发送email
        //限流策略：可以通过注解指定限流时间间隔和限流量
        //限流实现：spring data redis + aop，使用令牌桶算法
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
