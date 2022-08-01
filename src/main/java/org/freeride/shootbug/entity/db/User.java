package org.freeride.shootbug.entity.db;

import lombok.Data;
import org.freeride.shootbug.entity.db.type.GenderEnum;

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
}
