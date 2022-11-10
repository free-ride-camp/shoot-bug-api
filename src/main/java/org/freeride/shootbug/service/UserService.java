package org.freeride.shootbug.service;

import org.freeride.shootbug.dto.request.RegisterRequest;
import org.freeride.shootbug.po.db.User;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/7/29 17:42
 */

public interface UserService {
    User registerUser(RegisterRequest registerRequest);

    String sendVerificationCode(String email);
}
