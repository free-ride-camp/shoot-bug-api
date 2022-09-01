package org.freeride.shootbug.controller;

import org.freeride.shootbug.dto.request.LoginRequest;
import org.freeride.shootbug.dto.request.RegisterRequest;
import org.freeride.shootbug.entity.db.User;
import org.freeride.shootbug.entity.db.type.RoleEnum;
import org.freeride.shootbug.service.UserService;
import org.freeride.shootbug.util.TokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
public class UserController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getPrincipal(), loginRequest.getPassword()));
        UserDetails details = userDetailsService.loadUserByUsername(loginRequest.getPrincipal());
        List<RoleEnum> roles = details.getAuthorities().stream().map(GrantedAuthority::getAuthority).map(authority -> RoleEnum.valueOf(authority)).collect(Collectors.toList());
        return TokenUtil.generateJwt(details.getUsername(), roles);
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest registerRequest) {
        User user = userService.registerUser(registerRequest);
        return TokenUtil.generateJwt(user.getEmail(), user.getRoles());
    }

    @GetMapping("/send-verification-code")
    public void sendVerificationCode(@RequestParam @NotBlank(message = "电子邮箱必填") @Email(message = "电子邮箱格式错误") String email) {
        userService.sendVerificationCode(email);
    }
}
