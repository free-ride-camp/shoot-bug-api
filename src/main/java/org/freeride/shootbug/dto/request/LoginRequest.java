package org.freeride.shootbug.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    //用户标识，可以是手机、邮箱
    private String principal;
    private String password;
}
