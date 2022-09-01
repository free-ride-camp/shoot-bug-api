package org.freeride.shootbug.repository.db;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.freeride.shootbug.entity.db.type.RoleEnum;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/1 11:23
 */

@Mapper
public interface RoleMapper {
    @Select("select id from role where name=#{name}")
    Integer getIdByRoleName(RoleEnum role);
}
