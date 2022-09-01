package org.freeride.shootbug.dto.request;

import lombok.Data;
import org.freeride.shootbug.entity.db.type.GenderEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/7/29 17:03
 */

@Data
public class RegisterRequest {
    @NotBlank(message = "电子邮箱必填")
    @Email(message = "电子邮箱格式错误")
    private String email;
    private String phone;
    @NotBlank(message = "验证码必填")
    private String verificationCode;
    @NotBlank(message = "昵称必填")
    @Length(min = 1, max = 15, message = "昵称必须由1-15个字符组成")
    private String nickname;
    @NotBlank(message = "密码必填")
    @Length(min = 8, max = 15, message = "密码长度必须在8-15个字符之间")
    private String password;
    @NotNull(message = "性别必填")
    private GenderEnum gender;
    @Min(value = 0, message = "年龄不可小于0")
    private Integer age;
    private String city;
    private List<String> fields;
}
