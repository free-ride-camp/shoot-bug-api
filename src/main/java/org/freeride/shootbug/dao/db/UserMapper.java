package org.freeride.shootbug.dao.db;

import org.apache.ibatis.annotations.*;
import org.freeride.shootbug.dao.db.handler.JsonTypeHandler;
import org.freeride.shootbug.po.db.User;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(column = "fields", property = "fields", typeHandler = JsonTypeHandler.class),
    })
    User findUserById(@Param("id") Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user values (null, #{nickname}, #{password}, #{email}, #{phone}, #{avatarUrl}, #{gender}, #{age}, #{city}, #{fields,typeHandler=org.freeride.shootbug.dao.db.handler.JsonTypeHandler})")
    int insertUser(User user);

    @Select("select id from user where email = #{email}")
    Integer findUserIdByEmail(String email);

}
