package cn.wolfcode.luowowo.cloud.repository;

import cn.wolfcode.luowowo.cloud.domain.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户持久化接口
 */
@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo,String> {
    /**
     * 通过手机号查询用户信息
     * @param phone
     * @return
     */
    UserInfo findByPhone(String phone);

}
