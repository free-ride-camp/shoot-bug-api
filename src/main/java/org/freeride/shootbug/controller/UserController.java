package org.freeride.shootbug.controller;

import org.freeride.shootbug.dto.request.LoginRequest;
import org.freeride.shootbug.dto.request.RegisterRequest;
import org.freeride.shootbug.util.TokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getPrincipal(), loginRequest.getPassword()));
        UserDetails details = userDetailsService.loadUserByUsername(loginRequest.getPrincipal());
        return TokenUtil.generateJwt(details);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return null;
    }

    @GetMapping("/resource")
    public String resource() {
        // TODO: 2022/7/24 测试完成去掉
        return "hello";
    }
}
