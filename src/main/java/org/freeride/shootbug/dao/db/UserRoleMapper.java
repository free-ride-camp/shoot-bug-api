package org.freeride.shootbug.dao.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/9/1 11:16
 */

@Mapper
public interface UserRoleMapper {

    @Insert("insert into user_role values (#{arg0}, #{arg1})")
    int insertUserRole(int userId, int roleId);
}
