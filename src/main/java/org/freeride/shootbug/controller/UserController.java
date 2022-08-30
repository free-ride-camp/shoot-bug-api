package org.freeride.shootbug.controller;

import org.freeride.shootbug.dto.request.LoginRequest;
import org.freeride.shootbug.dto.request.RegisterRequest;
import org.freeride.shootbug.service.UserService;
import org.freeride.shootbug.util.TokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        //todo globalExceptionHandler
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getPrincipal(), loginRequest.getPassword()));
        } catch (AuthenticationException ae) {
            //todo 校验失败的情况
            throw new RuntimeException();
        }
        UserDetails details = userDetailsService.loadUserByUsername(loginRequest.getPrincipal());
        return TokenUtil.generateJwt(details);
    }

    //todo 注册接口
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return null;
    }

    @PostMapping("/send-verification-code")
    public void sendVerificationCode(@RequestParam Integer userId) {
        userService.sendVerificationCode(userId);
    }
}
