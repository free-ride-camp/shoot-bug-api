package org.freeride.shootbug.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    //用户标识，可以是手机、邮箱
    @NotBlank(message = "请输入账号")
    private String principal;
    @NotBlank(message = "请输入密码")
    private String password;
}
