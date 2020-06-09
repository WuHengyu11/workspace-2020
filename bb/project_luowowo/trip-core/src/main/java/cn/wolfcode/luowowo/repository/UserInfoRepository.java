package cn.wolfcode.luowowo.repository;

import cn.wolfcode.luowowo.domain.UserInfo;
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

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    UserInfo findByPhoneAndPassword(String username, String password);
}
