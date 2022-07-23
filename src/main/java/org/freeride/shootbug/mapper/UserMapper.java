package org.freeride.shootbug.mapper;

import org.apache.ibatis.annotations.*;
import org.freeride.shootbug.entity.User;
import org.freeride.shootbug.mapper.handler.JsonTypeHandler;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(column = "fields", property = "fields", typeHandler = JsonTypeHandler.class),
    })
    User findUserById(@Param("id") Integer id);

}
