package org.freeride.shootbug.po.db;

import lombok.Data;
import org.freeride.shootbug.po.db.type.GenderEnum;
import org.freeride.shootbug.po.db.type.RoleEnum;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String nickname;
    private String password;
    private String email;
    private String phone;
    private String avatarUrl;
    private GenderEnum gender;
    private Integer age;
    private String city;
    private List<String> fields;

    private transient List<RoleEnum> roles;
}
