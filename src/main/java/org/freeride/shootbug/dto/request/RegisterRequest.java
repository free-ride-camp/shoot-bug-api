package org.freeride.shootbug.dto.request;

import lombok.Data;
import org.freeride.shootbug.entity.db.type.GenderEnum;

import java.util.List;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/7/29 17:03
 */

@Data
public class RegisterRequest {
    private String email;
    private String phone;
    private String verificationCode;
    private String nickname;
    private String password;
    private GenderEnum gender;
    private Integer age;
    private String city;
    private List<String> fields;
}
