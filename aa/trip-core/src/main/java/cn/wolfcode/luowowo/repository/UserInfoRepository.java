package cn.wolfcode.luowowo.repository;


import cn.wolfcode.luowowo.domain.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户持久层接口
 */
@Repository
public interface UserInfoRepository  extends MongoRepository<UserInfo, String>{

    /**
     * 通过手机号码查询用户信息
     * @param phone
     * @return
     */
    UserInfo findByPhone(String phone);

    /**
     * 通过手机与密码查询
     * @param username
     * @param password
     * @return
     */
    UserInfo findByPhoneAndPassword(String username, String password);

    /**
     * 通过城市查询用户
     * @param city
     * @return
     */
    List<UserInfo> findByCity(String city);
}
