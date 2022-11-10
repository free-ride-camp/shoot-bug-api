package org.freeride.shootbug;

import org.freeride.shootbug.dao.db.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ShootBugApiApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void testUserMapper() {
        System.out.println(userMapper.findUserById(1));
    }

}
