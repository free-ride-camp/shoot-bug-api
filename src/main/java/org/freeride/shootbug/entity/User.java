package org.freeride.shootbug.entity;

import lombok.Data;
import org.freeride.shootbug.entity.type.GenderEnum;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String avatarUrl;
    private GenderEnum gender;
    private Integer age;
    private String city;
    private List<String> fields;
    //todo 经验值目前不需要
}
