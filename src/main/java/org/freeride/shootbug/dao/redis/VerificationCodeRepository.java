package org.freeride.shootbug.dao.redis;

import org.freeride.shootbug.po.redis.VerificationCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/8/1 10:30
 */

@Repository
public interface VerificationCodeRepository extends CrudRepository<VerificationCode, String> {
}
