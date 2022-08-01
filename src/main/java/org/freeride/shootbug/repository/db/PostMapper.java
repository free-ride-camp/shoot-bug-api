package org.freeride.shootbug.repository.db;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.freeride.shootbug.entity.db.Post;
import org.freeride.shootbug.repository.db.handler.JsonTypeHandler;

@Mapper
public interface PostMapper {

    @Select("select * from post where id = #{id}")
    @Results({
            @Result(column = "creator_id", property = "creator", one = @One(fetchType = FetchType.LAZY, select = "org.freeride.shootbug.repository.db.UserMapper.findUserById")),
            @Result(column = "tags", property = "tags", typeHandler = JsonTypeHandler.class),
    })
    Post findPostById(@Param("id") Integer id);
}
