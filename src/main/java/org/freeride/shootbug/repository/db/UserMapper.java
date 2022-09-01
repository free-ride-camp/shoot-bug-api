package org.freeride.shootbug.repository.db;

import org.apache.ibatis.annotations.*;
import org.freeride.shootbug.entity.db.User;
import org.freeride.shootbug.repository.db.handler.JsonTypeHandler;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(column = "fields", property = "fields", typeHandler = JsonTypeHandler.class),
    })
    User findUserById(@Param("id") Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user values (null, #{nickname}, #{password}, #{email}, #{phone}, #{avatarUrl}, #{gender}, #{age}, #{city}, #{fields,typeHandler=org.freeride.shootbug.repository.db.handler.JsonTypeHandler})")
    int insertUser(User user);

}
