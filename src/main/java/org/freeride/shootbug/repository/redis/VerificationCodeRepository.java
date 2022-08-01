package org.freeride.shootbug.repository.redis;

import org.freeride.shootbug.entity.redis.VerificationCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/8/1 10:30
 */

@Repository
public interface VerificationCodeRepository extends CrudRepository<VerificationCode, String> {
}
