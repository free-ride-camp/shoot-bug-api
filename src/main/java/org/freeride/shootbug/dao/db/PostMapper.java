package org.freeride.shootbug.dao.db;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.freeride.shootbug.po.db.Post;
import org.freeride.shootbug.po.db.type.PostStatusEnum;
import org.freeride.shootbug.dao.db.handler.JsonTypeHandler;

import java.util.List;

@Mapper
public interface PostMapper {

    @Select("select * from post where id = #{id}")
    @Results({
            @Result(column = "creator_id", property = "creator", one = @One(fetchType = FetchType.LAZY, select = "org.freeride.shootbug.dao.db.UserMapper.findUserById")),
            @Result(column = "tags", property = "tags", typeHandler = JsonTypeHandler.class),
    })
    Post findPostById(@Param("id") Integer id);

    @Select("<script>" +
            "select * from post " +
            "<where>" +
            "<if test='arg0 != null and arg0 != \"\"'>json_contains(tags, #{arg0}, '$')</if>" +
            "<if test='arg1 != null and arg1 != \"\"'>and (title like concat('%', #{arg1}, '%') or description like concat('%', #{arg1}, '%'))</if>" +
            "<if test='arg2 != null'>and status = #{arg2}</if>" +
            "<if test='arg3 != null'>and creator_id = #{arg3}</if>" +
            "</where>" +
            "</script>")
    @Results({
            @Result(column = "tags", property = "tags", typeHandler = JsonTypeHandler.class),
    })
    List<Post> findPosts(String tags, String searchText, PostStatusEnum postStatus, Integer userId);
}
